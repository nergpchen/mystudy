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
package org.babyfish.collection.spi.wrapper;

import org.babyfish.collection.LinkedHashMap;
import org.babyfish.collection.OrderAdjustMode;
import org.babyfish.collection.UnifiedComparator;
import org.babyfish.collection.XMap;
import org.babyfish.collection.XOrderedMap;
import org.babyfish.collection.spi.base.NoEntryException;
import org.babyfish.collection.viewinfo.OrderedMapViewInfos;
import org.babyfish.collection.viewinfo.OrderedSetViewInfos;
import org.babyfish.view.View;
import org.babyfish.view.ViewInfo;

/**
 * @author Tao Chen
 */
public abstract class AbstractWrapperXOrderedMap<K, V> 
extends AbstractWrapperXMap<K, V> 
implements XOrderedMap<K, V> {
    
    protected AbstractWrapperXOrderedMap(XOrderedMap<K, V> base) {
        super(base);
    }

    protected AbstractWrapperXOrderedMap(
            AbstractWrapperXOrderedMap<K, V> parent, 
            ViewInfo viewInfo) {
        super(parent, viewInfo);
    }
    
    /**
     * This method should not be invoked by the customer immediately.
     * 
     * <p>
     * It is used to create the instance during the when 
     * {@link java.io.ObjectInputStream} reads this object from a stream.
     * Although the derived classes of this class may implement {@link java.io.Serializable},
     * but this abstract super class does not implement {@link java.io.Serializable}
     * because it have some derived class that implements {@link View} which can 
     * not be implement {@link java.io.Serializable}
     * </p>
     * 
     * <p>
     * If the derived class is still a class does not implement {@link java.io.Serializable},
     * please support a no arguments constructor and mark it with {@link Deprecated}  too, 
     * like this method.
     * </p>
     */
    @Deprecated
    protected AbstractWrapperXOrderedMap() {
        
    }

    @Override
    public XOrderedMapView<K, V> descendingMap() {
        return new DescendingMapImpl<K, V>(this);
    }

    @Override
    public XOrderedKeySetView<K> keySet() {
        return this.getKeySet();
    }

    @Override
    public XOrderedKeySetView<K> descendingKeySet() {
        return new DescendingKeySetImpl<K, V>(this);
    }

    @Override
    public boolean headAppend() {
        return this.<XOrderedMap<K, V>>getBase().headAppend();
    }

    @Override
    public OrderAdjustMode accessMode() {
        return this.<XOrderedMap<K, V>>getBase().accessMode();
    }

    @Override
    public OrderAdjustMode replaceMode() {
        return this.<XOrderedMap<K, V>>getBase().replaceMode();
    }

    @Override
    public V access(K key) {
        this.enable();
        return this.<XOrderedMap<K, V>>getBase().access(key);
    }

    @Override
    public K firstKey() {
        this.requiredEnabled();
        return this.<XOrderedMap<K, V>>getBase().firstKey();
    }

    @Override
    public K lastKey() {
        this.requiredEnabled();
        return this.<XOrderedMap<K, V>>getBase().lastKey();
    }

    @Override
    public XEntry<K, V> firstEntry() {
        try {
            return new FirstEntryImpl<K, V>(this);
        } catch (NoEntryException ex) {
            return null;
        }
    }

    @Override
    public XEntry<K, V> lastEntry() {
        try {
            return new LastEntryImpl<K, V>(this);
        } catch (NoEntryException ex) {
            return null;
        }
    }

    @Override
    public Entry<K, V> pollFirstEntry() {
        this.enable();
        return this.<XOrderedMap<K, V>>getBase().pollFirstEntry();
    }

    @Override
    public Entry<K, V> pollLastEntry() {
        this.enable();
        return this.<XOrderedMap<K, V>>getBase().pollLastEntry();
    }
    
    @Override
    protected RootData<K, V> createRootData() {
        return new RootData<K, V>();
    }

    @Deprecated
    @Override
    protected final XMap<K, V> createBaseView(
            XMap<K, V> parentBase,
            ViewInfo viewInfo) {
        return this.createBaseView((XOrderedMap<K, V>)parentBase, viewInfo);
    }
    
    protected XOrderedMap<K, V> createBaseView(
            XOrderedMap<K, V> parentBase,
            ViewInfo viewInfo) {
        throw new IllegalArgumentException(LAZY_COMMON_RESOURCE.get().illegalViewInfo());
    }
    
    @Override
    protected XOrderedKeySetView<K> createKeySet() {
        return new KeySetImpl<K, V>(this);
    }

    protected static class DescendingMapImpl<K, V> 
    extends AbstractWrapperXOrderedMap<K, V> 
    implements XOrderedMapView<K, V> {

        protected DescendingMapImpl(AbstractWrapperXOrderedMap<K, V> parent) {
            super(parent, OrderedMapViewInfos.descendingMap());
        }

        @Override
        protected XOrderedMap<K, V> createBaseView(
                XOrderedMap<K, V> parentBase, 
                ViewInfo viewInfo) {
            if (viewInfo instanceof OrderedMapViewInfos.DescendingMap) {
                return parentBase.descendingMap();
            }
            throw new IllegalArgumentException(LAZY_COMMON_RESOURCE.get().illegalViewInfo());
        }

        @Override
        public ViewInfo viewInfo() {
            return this.<XOrderedMapView<K, V>>getBase().viewInfo();
        }
        
    }
    
    protected static class AbstractKeySetImpl<K, V> 
    extends AbstractWrapperXMap.AbstractKeySetImpl<K, V> 
    implements XOrderedKeySetView<K> {

        public AbstractKeySetImpl(
                AbstractKeySetImpl<K, V> parent,
                ViewInfo viewInfo) {
            super(parent, viewInfo);
        }

        public AbstractKeySetImpl(
                AbstractWrapperXOrderedMap<K, V> parentMap,
                ViewInfo viewInfo) {
            super(parentMap, viewInfo);
        }

        @Override
        public XOrderedKeySetView<K> descendingSet() {
            return new DescendingSetImpl<K, V>(this);
        }

        @Override
        public XIterator<K> descendingIterator() {
            return new DescendingIteratorImpl<K, V>(this);
        }

        @Override
        public boolean headAppend() {
            return this.<XOrderedKeySetView<K>>getBase().headAppend();
        }

        @Override
        public OrderAdjustMode replaceMode() {
            return this.<XOrderedKeySetView<K>>getBase().replaceMode();
        }

        @Override
        public K first() {
            this.requiredEnabled();
            return this.<XOrderedKeySetView<K>>getBase().first();
        }

        @Override
        public K last() {
            this.requiredEnabled();
            return this.<XOrderedKeySetView<K>>getBase().last();
        }

        @Override
        public K pollFirst() {
            this.enable();
            return this.<XOrderedKeySetView<K>>getBase().pollFirst();
        }

        @Override
        public K pollLast() {
            this.enable();
            return this.<XOrderedKeySetView<K>>getBase().pollLast();
        }

        @Deprecated
        @Override
        protected final XKeySetView<K> createBaseView(
                XMap<K, V> baseMap, ViewInfo viewInfo) {
            return this.createBaseView((XOrderedMap<K, V>)baseMap, viewInfo);
        }
        
        protected XOrderedKeySetView<K> createBaseView(
                XOrderedMap<K, V> baseMap, ViewInfo viewInfo) {
            throw new IllegalArgumentException(LAZY_COMMON_RESOURCE.get().illegalViewInfo());
        }

        @Deprecated
        @Override
        protected final XKeySetView<K> createBaseView(
                XKeySetView<K> parentBase, ViewInfo viewInfo) {
            return this.createBaseView((XOrderedKeySetView<K>)parentBase, viewInfo);
        }
        
        protected XOrderedKeySetView<K> createBaseView(
                XOrderedKeySetView<K> parentBase, ViewInfo viewInfo) {
            throw new IllegalArgumentException(LAZY_COMMON_RESOURCE.get().illegalViewInfo());
        }
        
        protected static class DescendingSetImpl<K, V> extends AbstractKeySetImpl<K, V> {

            public DescendingSetImpl(AbstractKeySetImpl<K, V> parent) {
                super(parent, OrderedSetViewInfos.descendingSet());
            }

            @Override
            protected XOrderedKeySetView<K> createBaseView(
                    XOrderedKeySetView<K> parentBase,
                    ViewInfo viewInfo) {
                if (viewInfo instanceof OrderedSetViewInfos.DescendingSet) {
                    return parentBase.descendingSet();
                }
                throw new IllegalArgumentException(LAZY_COMMON_RESOURCE.get().illegalViewInfo());
            }
            
        }
        
        protected static abstract class AbstractIteratorImpl<K, V>
        extends AbstractWrapperXMap.AbstractKeySetImpl.AbstractIteratorImpl<K, V> {

            protected AbstractIteratorImpl(
                    AbstractKeySetImpl<K, V> parent,
                    ViewInfo viewInfo) {
                super(parent, viewInfo);
            }
            
            @Deprecated
            @Override
            protected final XIterator<K> createBaseView(
                    XKeySetView<K> baseKeySet,
                    ViewInfo viewInfo) {
                return this.createBaseView(
                        (XOrderedKeySetView<K>)baseKeySet, viewInfo);
            }
            
            protected abstract XIterator<K> createBaseView(
                    XOrderedKeySetView<K> baseKeySet,
                    ViewInfo viewInfo);
        }
        
        protected static class IteratorImpl<K, V> extends AbstractIteratorImpl<K, V> {

            protected IteratorImpl(AbstractKeySetImpl<K, V> parent) {
                super(parent, OrderedSetViewInfos.iterator());
            }

            @Override
            protected XIterator<K> createBaseView(
                    XOrderedKeySetView<K> baseKeySet,
                    ViewInfo viewInfo) {
                if (viewInfo instanceof OrderedSetViewInfos.Iterator) {
                    return baseKeySet.iterator();
                }
                throw new IllegalArgumentException(LAZY_COMMON_RESOURCE.get().illegalViewInfo());
            }   
            
        }
        
        protected static class DescendingIteratorImpl<K, V> extends AbstractIteratorImpl<K, V> {

            protected DescendingIteratorImpl(AbstractKeySetImpl<K, V> parent) {
                super(parent, OrderedSetViewInfos.descendingIterator());
            }

            @Override
            protected XIterator<K> createBaseView(
                    XOrderedKeySetView<K> baseKeySet,
                    ViewInfo viewInfo) {
                if (viewInfo instanceof OrderedSetViewInfos.DescendingIterator) {
                    return baseKeySet.descendingIterator();
                }
                throw new IllegalArgumentException(LAZY_COMMON_RESOURCE.get().illegalViewInfo());
            }   
            
        }
    }
    
    protected static class KeySetImpl<K, V> extends AbstractKeySetImpl<K, V> {

        protected KeySetImpl(
                AbstractWrapperXOrderedMap<K, V> parentMap) {
            super(parentMap, OrderedMapViewInfos.keySet());
        }

        @Override
        protected XOrderedKeySetView<K> createBaseView(
                XOrderedMap<K, V> baseMap, ViewInfo viewInfo) {
            if (viewInfo instanceof OrderedMapViewInfos.KeySet) {
                return baseMap.keySet();
            }
            throw new IllegalArgumentException(LAZY_COMMON_RESOURCE.get().illegalViewInfo());
        }
        
    }
    
    protected static class DescendingKeySetImpl<K, V> extends AbstractKeySetImpl<K, V> {
        protected DescendingKeySetImpl(AbstractWrapperXOrderedMap<K, V> parentMap) {
            super(parentMap, OrderedMapViewInfos.descendingKeySet());
        }

        @Override
        protected XOrderedKeySetView<K> createBaseView(
                XOrderedMap<K, V> baseMap, ViewInfo viewInfo) {
            if (viewInfo instanceof OrderedMapViewInfos.DescendingKeySet) {
                return baseMap.descendingKeySet();
            }
            throw new IllegalArgumentException(LAZY_COMMON_RESOURCE.get().illegalViewInfo());
        }
    }
    
    protected static abstract class AbstractEntryImpl<K, V> extends AbstractWrapperXMap.AbstractEntryImpl<K, V> {

        protected AbstractEntryImpl(
                AbstractWrapperXOrderedMap<K, V> parentMap,
                ViewInfo viewInfo) throws NoEntryException {
            super(parentMap, viewInfo);
        }

        @Deprecated
        @Override
        protected final XEntry<K, V> createBaseView(
                XMap<K, V> map, ViewInfo viewInfo) {
            return this.createBaseView((XOrderedMap<K, V>)map, viewInfo);
        }
        
        protected abstract XEntry<K, V> createBaseView(
                XOrderedMap<K, V> baseMap, ViewInfo viewInfo);
        
    }
    
    protected static class FirstEntryImpl<K, V> extends AbstractEntryImpl<K, V> {

        protected FirstEntryImpl(
                AbstractWrapperXOrderedMap<K, V> parentMap) throws NoEntryException {
            super(parentMap, OrderedMapViewInfos.firstEntry());
        }

        @Override
        protected XEntry<K, V> createBaseView(
                XOrderedMap<K, V> baseMap, ViewInfo viewInfo) {
            if (viewInfo instanceof OrderedMapViewInfos.FirstEntry) {
                return baseMap.firstEntry();
            }
            throw new IllegalArgumentException(LAZY_COMMON_RESOURCE.get().illegalViewInfo());
        }
        
    }
    
    protected static class LastEntryImpl<K, V> extends AbstractEntryImpl<K, V> {

        protected LastEntryImpl(
                AbstractWrapperXOrderedMap<K, V> parentMap) throws NoEntryException {
            super(parentMap, OrderedMapViewInfos.lastEntry());
        }

        @Override
        protected XEntry<K, V> createBaseView(
                XOrderedMap<K, V> baseMap, ViewInfo viewInfo) {
            if (viewInfo instanceof OrderedMapViewInfos.LastEntry) {
                return baseMap.lastEntry();
            }
            throw new IllegalArgumentException(LAZY_COMMON_RESOURCE.get().illegalViewInfo());
        }
        
    }

    protected static class RootData<K, V> extends AbstractWrapperXMap.RootData<K, V> {

        private static final long serialVersionUID = 1945826131203944203L;
        
        public RootData() {
            
        }

        @Deprecated
        @Override
        protected final void setBase(XMap<K, V> base) {
            this.setBase((XOrderedMap<K, V>)base);
        }
        
        protected void setBase(XOrderedMap<K, V> base) {
            super.setBase(base);
        }

        @Override
        protected XOrderedMap<K, V> createDefaultBase(
                UnifiedComparator<? super K> keyUnifiedComparator,
                UnifiedComparator<? super V> valueUnifiedComparator) {
            return new LinkedHashMap<K, V>(
                    keyUnifiedComparator.equalityComparator(true), 
                    valueUnifiedComparator);
        }
    }
}
