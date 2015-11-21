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
package org.babyfish.persistence.criteria.spi;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;

import org.babyfish.persistence.criteria.XCriteriaBuilder;

/**
 * @author Tao Chen
 */
public class OrderImpl extends AbstractNode implements Order {

    private static final long serialVersionUID = -512378082629264291L;

    private Expression<?> expression;
    
    private boolean ascending;

    OrderImpl(
            XCriteriaBuilder cb, 
            Expression<?> expression, 
            boolean ascending) {
        super(cb);
        this.expression = expression;
        this.ascending = ascending;
    }

    @Override
    public Expression<?> getExpression() {
        return this.expression;
    }

    @Override
    public boolean isAscending() {
        return this.ascending;
    }

    @Override
    public Order reverse() {
        this.ascending = !this.ascending;
        return this;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitOrder(this);
    }
    
}
