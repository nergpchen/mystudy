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
package org.babyfish.test.collection.equalitycomparator;

import org.babyfish.collection.ReplacementRule;
import org.babyfish.collection.TreeMap;
import org.babyfish.collection.XMap;

/**
 * @author Tao Chen
 */
public class LaxTreeMapTest extends AbstractLaxXMapTest {

    @Override
    protected XMap<Element, Element> createXMap() {
        return new TreeMap<Element, Element>(
                ReplacementRule.OLD_REFERENCE_WIN,
                Helper.ELEMENT_CODE_COMPARATOR,
                Helper.ELEMENT_CODE_EQUALITY_COMPARATOR);
    }

}
