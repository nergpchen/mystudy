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
package org.babyfish.test.association;

import junit.framework.Assert;

import org.babyfish.association.AssociatedEndpoint;
import org.babyfish.association.AssociatedEndpointType;
import org.babyfish.association.AssociatedReference;
import org.junit.Test;

/**
 * @author Tao Chen
 */
public class OneToOneTest {

    @Test
    public void testModifyCustomer() {
        
        Customer customer = new Customer();
        Address address = new Address();
        Customer newCustomer = new Customer();
        Address newAddress = new Address();
        
        Assert.assertNull(customer.getAddress());
        Assert.assertNull(address.getCustomer());
        Assert.assertNull(newCustomer.getAddress());
        Assert.assertNull(newAddress.getCustomer());
        
        customer.setAddress(address);
        Assert.assertEquals(address, customer.getAddress());
        Assert.assertEquals(customer, address.getCustomer());
        Assert.assertNull(newCustomer.getAddress());
        Assert.assertNull(newAddress.getCustomer());
        
        customer.setAddress(newAddress);
        Assert.assertEquals(newAddress, customer.getAddress());
        Assert.assertNull(address.getCustomer());
        Assert.assertNull(newCustomer.getAddress());
        Assert.assertEquals(customer, newAddress.getCustomer());
        
        newCustomer.setAddress(newAddress);
        Assert.assertNull(customer.getAddress());
        Assert.assertNull(address.getCustomer());
        Assert.assertEquals(newAddress, newCustomer.getAddress());
        Assert.assertEquals(newCustomer, newAddress.getCustomer());
        
        newCustomer.setAddress(null);
        Assert.assertNull(address.getCustomer());
        Assert.assertNull(newAddress.getCustomer());
        Assert.assertNull(newCustomer.getAddress());
        Assert.assertNull(newAddress.getCustomer());
    }
    
    @Test
    public void testModifyAddress() {
        
        Customer customer = new Customer();
        Address address = new Address();
        Customer newCustomer = new Customer();
        Address newAddress = new Address();
        
        Assert.assertNull(customer.getAddress());
        Assert.assertNull(address.getCustomer());
        Assert.assertNull(newCustomer.getAddress());
        Assert.assertNull(newAddress.getCustomer());
        
        address.setCustomer(customer);
        Assert.assertEquals(address, customer.getAddress());
        Assert.assertEquals(customer, address.getCustomer());
        Assert.assertNull(newCustomer.getAddress());
        Assert.assertNull(newAddress.getCustomer());
        
        newAddress.setCustomer(customer);
        Assert.assertEquals(newAddress, customer.getAddress());
        Assert.assertNull(address.getCustomer());
        Assert.assertNull(newCustomer.getAddress());
        Assert.assertEquals(customer, newAddress.getCustomer());
        
        newAddress.setCustomer(newCustomer);
        Assert.assertNull(customer.getAddress());
        Assert.assertNull(address.getCustomer());
        Assert.assertEquals(newAddress, newCustomer.getAddress());
        Assert.assertEquals(newCustomer, newAddress.getCustomer());
        
        newAddress.setCustomer(null);
        Assert.assertNull(address.getCustomer());
        Assert.assertNull(newAddress.getCustomer());
        Assert.assertNull(newCustomer.getAddress());
        Assert.assertNull(newAddress.getCustomer());
    }
    
    private static class Customer {
        
        private AssociatedReference<Customer, Address> addressReference =
            new AssociatedReference<Customer, Address>() {
            
                private static final long serialVersionUID = -7515503584004575682L;
                
                public Customer getOwner() {
                    return Customer.this;
                }
                
                @Override
                protected AssociatedEndpointType onGetOppositeEndpointType() {
                    return AssociatedEndpointType.REFERENCE;
                }

                @Override
                public AssociatedEndpoint<Address, Customer> onGetOppositeEndpoint(Address opposite) {
                    return opposite.customerReference;
                }
            };

        public Address getAddress() {
            return this.addressReference.get();
        }
        
        public void setAddress(Address address) {
            this.addressReference.set(address);
        }
        
    }
    
    private static class Address {
        
        private AssociatedReference<Address, Customer> customerReference =
            new AssociatedReference<Address, Customer>() {
            
                private static final long serialVersionUID = -2923921006135053008L;
                
                public Address getOwner() {
                    return Address.this;
                }
                
                @Override
                protected AssociatedEndpointType onGetOppositeEndpointType() {
                    return AssociatedEndpointType.REFERENCE;
                }

                @Override
                public AssociatedEndpoint<Customer, Address> onGetOppositeEndpoint(Customer opposite) {
                    return opposite.addressReference;
                }
            };

        public Customer getCustomer() {
            return this.customerReference.get();
        }
        
        public void setCustomer(Customer customer) {
            this.customerReference.set(customer);
        }
        
    }
    
}
