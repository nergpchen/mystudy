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
package org.babyfish.modificationaware.event;

import java.io.Serializable;

import org.babyfish.lang.Arguments;
import org.babyfish.view.ViewInfo;

/**
 * @author Tao Chen
 */
public class Cause implements Serializable {
    
    private static final long serialVersionUID = 5066716069654053595L;

    private ViewInfo viewInfo;

    private ModificationEvent event;
    
    public Cause(ViewInfo viewInfo, ModificationEvent event) {
        Arguments.mustNotBeNull("viewInfo", viewInfo);
        Arguments.mustNotBeNull("event", event);
        this.viewInfo = viewInfo;
        this.event = event;
    }
    
    public ViewInfo getViewInfo() {
        return this.viewInfo;
    }
    
    public ModificationEvent getEvent() {
        return this.event;
    }
    
}
