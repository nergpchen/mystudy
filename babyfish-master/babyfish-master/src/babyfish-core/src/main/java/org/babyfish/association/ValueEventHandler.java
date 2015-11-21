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
package org.babyfish.association;

import java.util.Collection;
import java.util.Map;

import org.babyfish.modificationaware.event.PropertyVersion;
import org.babyfish.reference.Reference;
import org.babyfish.reference.event.ValueEvent;

/**
 * @author Tao Chen
 */
abstract class ValueEventHandler<O, T> extends AbandonableEventHandler<O, T> {
    
    /*
     * Keep the consistency with KeyedValueEventHandler, 
     * use preHandle to notify opposite end point.
     */
    @SuppressWarnings("unchecked")
    public void preHandle(ValueEvent<T> e) {
        
        if (this.getOppositeEndpointType() == null) {
            //For unidirectional reference
            return;
        }
        
        if (!this.isSuspended()) {
            this.setSuspended(true);
            try {
                T detachedValue = this.loaded(e.getValue(PropertyVersion.DETACH));
                T attachedValue = this.loaded(e.getValue(PropertyVersion.ATTACH));
                O owner = this.getOwner();
                if (detachedValue == attachedValue) {
                    return;
                }
                if (detachedValue != null) {
                    AssociatedEndpoint<T, O> oppositeEndpoint = this.getOppositeEndpoint(detachedValue);
                    if (!oppositeEndpoint.isSuspended()) {
                        boolean isOppositeEndpointDisabled = oppositeEndpoint.isDisabled();
                        try {
                            if (oppositeEndpoint instanceof Reference<?>) {
                                Reference<?> reference = (Reference<?>)oppositeEndpoint;
                                reference.set(null);
                            } else if (oppositeEndpoint instanceof Map<?, ?>) {
                                Map<?, ?> map = (Map<?, ?>)oppositeEndpoint;
                                map.values().remove(owner);
                            } else if (oppositeEndpoint instanceof Collection<?>) {
                                Collection<?> collection = (Collection<?>)oppositeEndpoint;
                                collection.remove(owner);
                            } else {
                                throw new AssertionError();
                            }
                        } finally {
                            if (isOppositeEndpointDisabled) {
                                oppositeEndpoint.disable();
                            }
                        }
                    }
                }
                if (attachedValue != null) {
                    AssociatedEndpoint<T, O> oppositeEndpoint = this.getOppositeEndpoint(attachedValue);
                    if (!oppositeEndpoint.isSuspended()) {
                        boolean isOppositeEndpointDisabled = oppositeEndpoint.isDisabled();
                        try {
                            if (oppositeEndpoint instanceof Reference<?>) {
                                Reference<O> reference = (Reference<O>)oppositeEndpoint;
                                reference.set(owner);
                            } else if (oppositeEndpoint instanceof Collection<?>) {
                                Collection<O> collection = (Collection<O>)oppositeEndpoint;
                                collection.add(owner);
                            } else {
                                /*
                                 * Reference work together with map does not support attach!!! 
                                 */
                                throw new AssertionError();
                            }
                        } finally {
                            if (isOppositeEndpointDisabled) {
                                oppositeEndpoint.disable();
                            }
                        }
                    }
                }
            } finally {
                this.setSuspended(false);
            }
        }
    }
}
