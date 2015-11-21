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
package org.babyfish.persistence.criteria.expression;

import javax.persistence.criteria.Expression;

import org.babyfish.persistence.criteria.XCriteriaBuilder;
import org.babyfish.persistence.criteria.spi.AbstractFunction;

/**
 * @author Tao Chen
 */
public class SqrtFunction extends AbstractFunction<Double> {

    private static final long serialVersionUID = -8101606933290332728L;
    
    public static final String FUNCTION_NAME = "sqrt";

    public SqrtFunction(
            XCriteriaBuilder criteriaBuilder,
            Expression<? extends Number> argumentExpression) {
        super(criteriaBuilder, FUNCTION_NAME, argumentExpression);
    }

    @Override
    public Class<? extends Double> getJavaType() {
        return Double.class;
    }

}
