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
package org.babyfish.persistence.model.metadata;

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.babyfish.model.metadata.spi.NonDeferrableOnly;
import org.babyfish.model.metadata.spi.NonDisabledOnly;
import org.babyfish.model.metadata.spi.OwnerReferenceOnly;
import org.babyfish.model.metadata.spi.ScalarOnly;

/**
 * @author Tao Chen
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@ScalarOnly(lowerTypes = Serializable.class)
@OwnerReferenceOnly
@NonDeferrableOnly
@NonDisabledOnly
public @interface EntityId {

}
