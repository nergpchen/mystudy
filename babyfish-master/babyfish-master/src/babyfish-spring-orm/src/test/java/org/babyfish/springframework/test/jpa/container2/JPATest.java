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
package org.babyfish.springframework.test.jpa.container2;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.babyfish.springframework.test.bll.ProductService;
import org.babyfish.springframework.test.entities.Product;
import org.babyfish.springframework.test.entities.ProductCategory;
import org.babyfish.springframework.test.entities.Product__;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Tao Chen
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "applicationContext.spring.xml")
public class JPATest {

    @Resource
    private ProductService productService;
    
    private static boolean dataInitialized;
    
    @Before
    public void initData() {
        if (dataInitialized) {
            return;
        }
        dataInitialized = true;
        
        ProductCategory food = new ProductCategory();
        food.setName("Food");
        this.productService.persistProductCategory(food);
        
        ProductCategory mobile = new ProductCategory();
        mobile.setName("mobile");
        this.productService.persistProductCategory(mobile);
        
        Product cola = new Product();
        cola.setName("cola");
        cola.setPrice(new BigDecimal("1.30"));
        cola.setProductCategory(food);
        this.productService.persistProduct(cola);
        
        Product milk = new Product();
        milk.setName("cola");
        milk.setPrice(new BigDecimal("2.50"));
        milk.setProductCategory(mobile);
        this.productService.persistProduct(milk);
        
        Product iphone5 = new Product();
        iphone5.setName("iPhone");
        iphone5.setPrice(new BigDecimal("899"));
        iphone5.setProductCategory(food);
        this.productService.persistProduct(iphone5);
        
        Product galaxyNote2 = new Product();
        galaxyNote2.setName("glaxyNote2");
        galaxyNote2.setPrice(new BigDecimal("999"));
        galaxyNote2.setProductCategory(mobile);
        this.productService.persistProduct(galaxyNote2);
        
        Product lumia920 = new Product();
        lumia920.setName("lumia920");
        lumia920.setPrice(new BigDecimal("699"));
        lumia920.setProductCategory(mobile);
        this.productService.persistProduct(lumia920);
    }
    
    @Test
    public void testQueryAllProductsWithoutCategory() {
        List<Product> products = this.productService.getProductLikeName("%");
        Assert.assertEquals(5, products.size());
    }
    
    @Test
    public void testQueryAllProductsWithCategory() {
        List<Product> products = this.productService.getProductLikeName("%", Product__.begin().productCategory().end());
        Assert.assertEquals(5, products.size());
    }
}
