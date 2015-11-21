/*
 * BabyFish, Object Model Framework for Java and JPA.
 * https://github.com/babyfish-ct/babyfish
 *
 * Copyright (c) 2008-2015, Tao Chen
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * Please visit "http://opensource.org/licenses/LGPL-3.0" to know more.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 */
package org.babyfish.hibernate.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.babyfish.association.AssociatedEndpoint;
import org.babyfish.collection.ArrayList;
import org.babyfish.hibernate.association.EntityIndexedReference;
import org.babyfish.hibernate.association.EntityKeyedReference;
import org.babyfish.hibernate.association.EntityReference;
import org.babyfish.lang.Arguments;
import org.babyfish.lang.UncheckedException;
import org.babyfish.model.ObjectModel;
import org.babyfish.model.ObjectModelFactory;
import org.babyfish.model.metadata.AssociationProperty;
import org.babyfish.model.metadata.Metadatas;
import org.babyfish.persistence.model.metadata.JPAMetadatas;
import org.babyfish.persistence.model.metadata.JPAObjectModelMetadata;
import org.babyfish.reference.Reference;
import org.hibernate.HibernateException;
import org.hibernate.event.spi.MergeEvent;
import org.hibernate.event.spi.MergeEventListener;

/**
 * @author Tao Chen
 */
public class ObjectModelMergeEventListener implements MergeEventListener {
    
    private static final long serialVersionUID = -2745763589533314530L;

    private static final Method ENTITY_REFERENCE_HIBERNATE_SET;
    
    private static final Method ENTITY_INDEXED_REFERENCE_HIBERNATE_SET;
    
    private static final Method ENTITY_KEYED_REFERENCE_HIBERNATE_SET;

    private MergeEventListener[] listeners;
    
    public ObjectModelMergeEventListener(Iterable<MergeEventListener> listeners) {
        Arguments.mustNotContainSpecialElements(
                "listeners", 
                Arguments.mustNotBeEmpty("listeners", Arguments.mustNotBeNull("listeners", listeners)), 
                ObjectModelMergeEventListener.class
        );
        List<MergeEventListener> list = new ArrayList<>();
        for (MergeEventListener listener : listeners) {
            list.add(listener);
        }
        this.listeners = list.toArray(new MergeEventListener[list.size()]);
    }

    @Override
    public void onMerge(MergeEvent event) throws HibernateException {
        for (MergeEventListener listener : this.listeners) {
            listener.onMerge(event);
        }
        this.afterMerge(event);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void onMerge(MergeEvent event, Map copiedAlready) throws HibernateException {
        for (MergeEventListener listener : this.listeners) {
            listener.onMerge(event, copiedAlready);
        }
        this.afterMerge(event);
    }
    
    protected void afterMerge(MergeEvent event) {
        Object original = event.getOriginal();
        Object result = event.getResult();
        
        //Merged transient or detached object with ObjectModel.
        if (original != result && Metadatas.has(original.getClass())) { 
            JPAObjectModelMetadata objectModelMetadata = JPAMetadatas.of(original.getClass());
            ObjectModel objectModel = (ObjectModel)objectModelMetadata.getFactory().get(original);
            for (AssociationProperty associationProperty : objectModelMetadata.getAssociationProperties().values()) {
                AssociationProperty oppositeProperty = associationProperty.getOppositeProperty();
                if (oppositeProperty == null || !oppositeProperty.isReference()) {
                    continue;
                }
                AssociatedEndpoint<?, ?> associatedEndpoint = objectModel.getAssociation(associationProperty.getId());
                if (associatedEndpoint.isDisabled() || !associatedEndpoint.isLoaded()) {
                    continue;
                }
                ObjectModelFactory<?> elementObjectModelFactory = oppositeProperty.getDeclaringObjectModelMetadata().getFactory();
                int oppositePropertyId = oppositeProperty.getId();
                if (associatedEndpoint instanceof Collection<?>) {
                    for (Object o : (Collection<?>)associatedEndpoint) {
                        if (o != null) {
                            ObjectModel elementObjectModel = (ObjectModel)elementObjectModelFactory.get(o);
                            replaceBackReferenceToResult(event, elementObjectModel.getAssociation(oppositePropertyId));
                        }
                    }
                } else if (associatedEndpoint instanceof Map<?, ?>) {
                    for (Object o : ((Map<?, ?>)associatedEndpoint).values()) {
                        if (o != null) {
                            ObjectModel elementObjectModel = (ObjectModel)elementObjectModelFactory.get(o);
                            replaceBackReferenceToResult(event, elementObjectModel.getAssociation(oppositePropertyId));
                        }
                    }
                } else {
                    Object o = ((Reference<?>)associatedEndpoint).get();
                    if (o != null) {
                        ObjectModel elementObjectModel = (ObjectModel)elementObjectModelFactory.get(o);
                        replaceBackReferenceToResult(event, elementObjectModel.getAssociation(oppositePropertyId));
                    }
                }
            }
        }
    }
    
    private static void replaceBackReferenceToResult(MergeEvent event, AssociatedEndpoint<?, ?> backEndpoint) {
        if (backEndpoint.isDisabled() || !backEndpoint.isLoaded()) {
            return;
        }
        
        Reference<?> reference = (Reference<?>)backEndpoint;
        if (reference.get(true) == event.getResult()) {
            return;
        }
        
        Method hibernateSetMethod;
        if (backEndpoint instanceof EntityKeyedReference<?, ?, ?>) {
            hibernateSetMethod = ENTITY_KEYED_REFERENCE_HIBERNATE_SET;
        } else if (backEndpoint instanceof EntityIndexedReference<?, ?>) {
            hibernateSetMethod = ENTITY_INDEXED_REFERENCE_HIBERNATE_SET;
        } else {
            hibernateSetMethod = ENTITY_REFERENCE_HIBERNATE_SET;
        }
        
        try {
            hibernateSetMethod.invoke(backEndpoint, event.getResult());
        } catch (IllegalAccessException ex) {
            throw new AssertionError("Internal bug", ex);
        } catch (InvocationTargetException ex) {
            UncheckedException.rethrow(ex.getTargetException());
        }
    }
    
    static {
        Method referenceMethod;
        Method indexedReferenceMethod;
        Method keyedReferenceMethod;
        
        try {
            referenceMethod = EntityReference.class.getDeclaredMethod("hibernateSet", Object.class);
        } catch (NoSuchMethodException ex) {
            throw new AssertionError("Internal bug", ex);
        }
        referenceMethod.setAccessible(true);
        
        try {
            indexedReferenceMethod = EntityIndexedReference.class.getDeclaredMethod("hibernateSet", Object.class);
        } catch (NoSuchMethodException ex) {
            throw new AssertionError("Internal bug", ex);
        }
        indexedReferenceMethod.setAccessible(true);
        
        try {
            keyedReferenceMethod = EntityKeyedReference.class.getDeclaredMethod("hibernateSet", Object.class);
        } catch (NoSuchMethodException ex) {
            throw new AssertionError("Internal bug", ex);
        }
        keyedReferenceMethod.setAccessible(true);
        
        ENTITY_REFERENCE_HIBERNATE_SET = referenceMethod;
        ENTITY_INDEXED_REFERENCE_HIBERNATE_SET = indexedReferenceMethod;
        ENTITY_KEYED_REFERENCE_HIBERNATE_SET = keyedReferenceMethod;
    }
}
