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
package org.babyfish.persistence.path;

import org.babyfish.lang.Arguments;
import org.babyfish.lang.Func;

/**
 * @author Tao Chen
 */
public interface TypedFetchPath<R> extends TypedQueryPath<R>, FetchPath {
    
    public static class TypedBuilder<R, P extends TypedFetchPath<R>> {
        
        protected FetchPath.Builder builder;
    
        protected Func<FetchPath.Builder, P> pathCreator;

        public TypedBuilder(FetchPath.Builder builder, Func<FetchPath.Builder, P> pathCreator) {
            this.builder = Arguments.mustNotBeNull("builder", builder);
            this.pathCreator = Arguments.mustNotBeNull("pathCreator", pathCreator);
        }
        
        public P end() {
            return this.pathCreator.run(this.builder);
        }
    }
}
