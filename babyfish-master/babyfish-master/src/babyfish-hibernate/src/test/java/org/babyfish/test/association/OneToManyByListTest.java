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

import java.util.List;

import junit.framework.Assert;

import org.babyfish.association.AssociatedEndpoint;
import org.babyfish.association.AssociatedEndpointType;
import org.babyfish.association.AssociatedIndexedReference;
import org.babyfish.association.AssociatedList;
import org.babyfish.collection.MAArrayList;
import org.babyfish.collection.MALinkedList;
import org.babyfish.collection.MAList;
import org.babyfish.collection.UnifiedComparator;
import org.babyfish.hibernate.collection.type.MAListType;
import org.babyfish.reference.IndexedReference;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Tao Chen
 */
public class OneToManyByListTest {
    
    private static final TestSite.EmployeeListFactory[] EMPLOYE_BAG_FACTORIES =
        new TestSite.EmployeeListFactory[] {
            new TestSite.EmployeeListFactory() {
                @Override
                public MAList<TestSite.Employee> createEmployeeList() {
                    return new MAArrayList<TestSite.Employee>();
                }
            },
            new TestSite.EmployeeListFactory() {
                @Override
                public MAList<TestSite.Employee> createEmployeeList() {
                    return new MALinkedList<TestSite.Employee>();
                }
            },
            new TestSite.EmployeeListFactory() {
                @SuppressWarnings("unchecked")
                @Override
                public MAList<TestSite.Employee> createEmployeeList() {
                    return (MAList<org.babyfish.test.association.OneToManyByListTest.TestSite.Employee>) new MAListType().wrap(null, new MAArrayList<TestSite.Employee>());
                }
            },
            new TestSite.EmployeeListFactory() {
                @SuppressWarnings("unchecked")
                @Override
                public MAList<TestSite.Employee> createEmployeeList() {
                    return (MAList<org.babyfish.test.association.OneToManyByListTest.TestSite.Employee>) new MAListType().wrap(null, new MALinkedList<TestSite.Employee>());
                }
            },
        };
    
    private TestSite[] testSites;
    
    @Before
    public void initialize() {
        TestSite[] sites = new TestSite[EMPLOYE_BAG_FACTORIES.length];
        int index = 0;
        for (TestSite.EmployeeListFactory employeeListFactory : EMPLOYE_BAG_FACTORIES) {
            sites[index++] = new TestSite(employeeListFactory);
        }
        this.testSites = sites;
    }
    
    @Test
    public void testModifyDepartment() {
        for (TestSite testSite : this.testSites) {
            testSite.testModifyDepartment();
        }
    }
    
    @Test
    public void testModifyIndexAndValueOfEmployee() {
        for (TestSite testSite : this.testSites) {
            testSite.testModifyIndexAndValueOfEmployee();
        }
    }
    
    @Test
    public void testModifyValueAndIndexOfEmployee() {
        for (TestSite testSite : this.testSites) {
            testSite.testModifyValueAndIndexOfEmployee();
        }
    }
    
    @Test
    public void testModifyEmployee() {
        for (TestSite testSite : this.testSites) {
            testSite.testModifyEmployee();
        }
    }
    
    @Test
    public void testChangeOrderByDepartment() {
        for (TestSite testSite : this.testSites) {
            testSite.testChangeOrderByDepartment();
        }
    }
    
    @Test
    public void testChangeOrderByEmployee() {
        for (TestSite testSite : this.testSites) {
            testSite.testChangeOrderByEmployee();
        }
    }
    
    private static class TestSite {
        
        private EmployeeListFactory employeeListFactory;
        
        TestSite(EmployeeListFactory employeeListFactory) {
            
            this.employeeListFactory = employeeListFactory;
        }

        interface EmployeeListFactory {
            MAList<Employee> createEmployeeList();
        }
        
        void testModifyDepartment() {
            
            Department department1 = this.new Department();
            Department department2 = this.new Department();
            Employee employee1 = new Employee();
            Employee employee2 = new Employee();
            assertList(department1.getEmployees());
            assertList(department2.getEmployees());
            Assert.assertNull(employee1.getDepartment());
            Assert.assertEquals(IndexedReference.INVALID_INDEX, employee1.getIndexInDepartment());
            Assert.assertNull(employee2.getDepartment());
            Assert.assertEquals(IndexedReference.INVALID_INDEX, employee2.getIndexInDepartment());
            
            department1.getEmployees().add(0, employee1);
            assertList(department1.getEmployees(), employee1);
            assertList(department2.getEmployees());
            Assert.assertEquals(department1, employee1.getDepartment());
            Assert.assertEquals(0, employee1.getIndexInDepartment());
            Assert.assertNull(employee2.getDepartment());
            Assert.assertEquals(IndexedReference.INVALID_INDEX, employee2.getIndexInDepartment());
            
            department1.getEmployees().add(0, employee2);
            assertList(department1.getEmployees(), employee2, employee1);
            assertList(department2.getEmployees());
            Assert.assertEquals(department1, employee1.getDepartment());
            Assert.assertEquals(1, employee1.getIndexInDepartment());
            Assert.assertEquals(department1, employee2.getDepartment());
            Assert.assertEquals(0, employee2.getIndexInDepartment());
            
            department2.getEmployees().add(0, employee1);
            assertList(department1.getEmployees(), employee2);
            assertList(department2.getEmployees(), employee1);
            Assert.assertEquals(department2, employee1.getDepartment());
            Assert.assertEquals(0, employee1.getIndexInDepartment());
            Assert.assertEquals(department1, employee2.getDepartment());
            Assert.assertEquals(0, employee2.getIndexInDepartment());
            
            department2.getEmployees().add(0, employee2);
            assertList(department1.getEmployees());
            assertList(department2.getEmployees(), employee2, employee1);
            Assert.assertEquals(department2, employee1.getDepartment());
            Assert.assertEquals(1, employee1.getIndexInDepartment());
            Assert.assertEquals(department2, employee2.getDepartment());
            Assert.assertEquals(0, employee2.getIndexInDepartment());
            
            department2.getEmployees().remove(0);
            assertList(department1.getEmployees());
            assertList(department2.getEmployees(), employee1);
            Assert.assertEquals(department2, employee1.getDepartment());
            Assert.assertEquals(0, employee1.getIndexInDepartment());
            Assert.assertNull(employee2.getDepartment());
            Assert.assertEquals(-1, employee2.getIndexInDepartment());
            
            department2.getEmployees().remove(0);
            assertList(department1.getEmployees());
            assertList(department2.getEmployees());
            Assert.assertNull(employee1.getDepartment());
            Assert.assertEquals(-1, employee1.getIndexInDepartment());
            Assert.assertNull(employee2.getDepartment());
            Assert.assertEquals(-1, employee2.getIndexInDepartment());
        }
        
        void testModifyIndexAndValueOfEmployee() {
            
            Department department1 = this.new Department();
            Department department2 = this.new Department();
            Employee employee1 = new Employee();
            Employee employee2 = new Employee();
            assertList(department1.getEmployees());
            assertList(department2.getEmployees());
            Assert.assertNull(employee1.getDepartment());
            Assert.assertEquals(IndexedReference.INVALID_INDEX, employee1.getIndexInDepartment());
            Assert.assertNull(employee2.getDepartment());
            Assert.assertEquals(IndexedReference.INVALID_INDEX, employee2.getIndexInDepartment());
            
            employee1.setIndexInDepartment(0);
            employee1.setDepartment(department1);
            assertList(department1.getEmployees(), employee1);
            assertList(department2.getEmployees());
            Assert.assertEquals(department1, employee1.getDepartment());
            Assert.assertEquals(0, employee1.getIndexInDepartment());
            Assert.assertNull(employee2.getDepartment());
            Assert.assertEquals(IndexedReference.INVALID_INDEX, employee2.getIndexInDepartment());
            
            employee2.setIndexInDepartment(0);
            employee2.setDepartment(department1);
            assertList(department1.getEmployees(), employee2, employee1);
            assertList(department2.getEmployees());
            Assert.assertEquals(department1, employee1.getDepartment());
            Assert.assertEquals(1, employee1.getIndexInDepartment());
            Assert.assertEquals(department1, employee2.getDepartment());
            Assert.assertEquals(0, employee2.getIndexInDepartment());
            
            employee1.setIndexInDepartment(0);
            employee1.setDepartment(department2);
            assertList(department1.getEmployees(), employee2);
            assertList(department2.getEmployees(), employee1);
            Assert.assertEquals(department2, employee1.getDepartment());
            Assert.assertEquals(0, employee1.getIndexInDepartment());
            Assert.assertEquals(department1, employee2.getDepartment());
            Assert.assertEquals(0, employee2.getIndexInDepartment());
            
            employee2.setIndexInDepartment(0);
            employee2.setDepartment(department2);
            assertList(department1.getEmployees());
            assertList(department2.getEmployees(), employee2, employee1);
            Assert.assertEquals(department2, employee1.getDepartment());
            Assert.assertEquals(1, employee1.getIndexInDepartment());
            Assert.assertEquals(department2, employee2.getDepartment());
            Assert.assertEquals(0, employee2.getIndexInDepartment());
            
            employee2.setIndexInDepartment(-1);
            assertList(department1.getEmployees());
            assertList(department2.getEmployees(), employee1);
            Assert.assertEquals(department2, employee1.getDepartment());
            Assert.assertEquals(0, employee1.getIndexInDepartment());
            Assert.assertNull(employee2.getDepartment());
            Assert.assertEquals(-1, employee2.getIndexInDepartment());
            
            employee1.setIndexInDepartment(-1);
            assertList(department1.getEmployees());
            assertList(department2.getEmployees());
            Assert.assertNull(employee1.getDepartment());
            Assert.assertEquals(-1, employee1.getIndexInDepartment());
            Assert.assertNull(employee2.getDepartment());
            Assert.assertEquals(-1, employee2.getIndexInDepartment());
        }
        
        void testModifyValueAndIndexOfEmployee() {
            
            Department department1 = this.new Department();
            Department department2 = this.new Department();
            Employee employee1 = new Employee();
            Employee employee2 = new Employee();
            assertList(department1.getEmployees());
            assertList(department2.getEmployees());
            Assert.assertNull(employee1.getDepartment());
            Assert.assertEquals(IndexedReference.INVALID_INDEX, employee1.getIndexInDepartment());
            Assert.assertNull(employee2.getDepartment());
            Assert.assertEquals(IndexedReference.INVALID_INDEX, employee2.getIndexInDepartment());
            
            employee1.setDepartment(department1);
            employee1.setIndexInDepartment(0);
            assertList(department1.getEmployees(), employee1);
            assertList(department2.getEmployees());
            Assert.assertEquals(department1, employee1.getDepartment());
            Assert.assertEquals(0, employee1.getIndexInDepartment());
            Assert.assertNull(employee2.getDepartment());
            Assert.assertEquals(IndexedReference.INVALID_INDEX, employee2.getIndexInDepartment());
            
            employee2.setDepartment(department1);
            employee2.setIndexInDepartment(0);
            assertList(department1.getEmployees(), employee2, employee1);
            assertList(department2.getEmployees());
            Assert.assertEquals(department1, employee1.getDepartment());
            Assert.assertEquals(1, employee1.getIndexInDepartment());
            Assert.assertEquals(department1, employee2.getDepartment());
            Assert.assertEquals(0, employee2.getIndexInDepartment());
            
            //Modify the index of employee1 to avoid IndexOutOfBoundsException
            employee1.setIndexInDepartment(-1);
            
            employee1.setDepartment(department2);
            employee1.setIndexInDepartment(0);
            assertList(department1.getEmployees(), employee2);
            assertList(department2.getEmployees(), employee1);
            Assert.assertEquals(department2, employee1.getDepartment());
            Assert.assertEquals(0, employee1.getIndexInDepartment());
            Assert.assertEquals(department1, employee2.getDepartment());
            Assert.assertEquals(0, employee2.getIndexInDepartment());
            
            employee2.setDepartment(department2);
            employee2.setIndexInDepartment(0);
            assertList(department1.getEmployees());
            assertList(department2.getEmployees(), employee2, employee1);
            Assert.assertEquals(department2, employee1.getDepartment());
            Assert.assertEquals(1, employee1.getIndexInDepartment());
            Assert.assertEquals(department2, employee2.getDepartment());
            Assert.assertEquals(0, employee2.getIndexInDepartment());
            
            assertList(department1.getEmployees());
            employee2.setDepartment(null);
            assertList(department2.getEmployees(), employee1);
            Assert.assertEquals(department2, employee1.getDepartment());
            Assert.assertEquals(0, employee1.getIndexInDepartment());
            Assert.assertNull(employee2.getDepartment());
            Assert.assertEquals(-1, employee2.getIndexInDepartment());
            
            assertList(department1.getEmployees());
            employee1.setDepartment(null);
            assertList(department2.getEmployees());
            Assert.assertNull(employee1.getDepartment());
            Assert.assertEquals(-1, employee1.getIndexInDepartment());
            Assert.assertNull(employee2.getDepartment());
            Assert.assertEquals(-1, employee2.getIndexInDepartment());
        }
        
        void testModifyEmployee() {
            
            Department department1 = this.new Department();
            Department department2 = this.new Department();
            Employee employee1 = new Employee();
            Employee employee2 = new Employee();
            assertList(department1.getEmployees());
            assertList(department2.getEmployees());
            Assert.assertNull(employee1.getDepartment());
            Assert.assertEquals(IndexedReference.INVALID_INDEX, employee1.getIndexInDepartment());
            Assert.assertNull(employee2.getDepartment());
            Assert.assertEquals(IndexedReference.INVALID_INDEX, employee2.getIndexInDepartment());
            
            employee1.setDepartment(0, department1);
            assertList(department1.getEmployees(), employee1);
            assertList(department2.getEmployees());
            Assert.assertEquals(department1, employee1.getDepartment());
            Assert.assertEquals(0, employee1.getIndexInDepartment());
            Assert.assertNull(employee2.getDepartment());
            Assert.assertEquals(IndexedReference.INVALID_INDEX, employee2.getIndexInDepartment());
            
            employee2.setDepartment(0, department1);
            assertList(department1.getEmployees(), employee2, employee1);
            assertList(department2.getEmployees());
            Assert.assertEquals(department1, employee1.getDepartment());
            Assert.assertEquals(1, employee1.getIndexInDepartment());
            Assert.assertEquals(department1, employee2.getDepartment());
            Assert.assertEquals(0, employee2.getIndexInDepartment());
            
            employee1.setDepartment(0, department2);
            assertList(department1.getEmployees(), employee2);
            assertList(department2.getEmployees(), employee1);
            Assert.assertEquals(department2, employee1.getDepartment());
            Assert.assertEquals(0, employee1.getIndexInDepartment());
            Assert.assertEquals(department1, employee2.getDepartment());
            Assert.assertEquals(0, employee2.getIndexInDepartment());
            
            employee2.setDepartment(0, department2);
            assertList(department1.getEmployees());
            assertList(department2.getEmployees(), employee2, employee1);
            Assert.assertEquals(department2, employee1.getDepartment());
            Assert.assertEquals(1, employee1.getIndexInDepartment());
            Assert.assertEquals(department2, employee2.getDepartment());
            Assert.assertEquals(0, employee2.getIndexInDepartment());
            
            employee2.setDepartment(-1, null);
            assertList(department1.getEmployees());
            assertList(department2.getEmployees(), employee1);
            Assert.assertEquals(department2, employee1.getDepartment());
            Assert.assertEquals(0, employee1.getIndexInDepartment());
            Assert.assertNull(employee2.getDepartment());
            Assert.assertEquals(-1, employee2.getIndexInDepartment());
            
            employee1.setDepartment(-1, null);
            assertList(department1.getEmployees());
            assertList(department2.getEmployees());
            Assert.assertNull(employee1.getDepartment());
            Assert.assertEquals(-1, employee1.getIndexInDepartment());
            Assert.assertNull(employee2.getDepartment());
            Assert.assertEquals(-1, employee2.getIndexInDepartment());
        }
        
        void testChangeOrderByDepartment() {
            
            Department department = this.new Department();
            Employee employee1 = new Employee();
            Employee employee2 = new Employee();
            Employee employee3 = new Employee();
            department.getEmployees().add(employee1);
            department.getEmployees().add(employee2);
            department.getEmployees().add(employee3);
            assertList(department.getEmployees(), employee1, employee2, employee3);
            Assert.assertEquals(0, employee1.getIndexInDepartment());
            Assert.assertEquals(1, employee2.getIndexInDepartment());
            Assert.assertEquals(2, employee3.getIndexInDepartment());
            
            department.getEmployees().add(0, employee2);
            assertList(department.getEmployees(), employee2, employee1, employee3);
            Assert.assertEquals(1, employee1.getIndexInDepartment());
            Assert.assertEquals(0, employee2.getIndexInDepartment());
            Assert.assertEquals(2, employee3.getIndexInDepartment());
            
            department.getEmployees().add(0, employee3);
            assertList(department.getEmployees(), employee3, employee2, employee1);
            Assert.assertEquals(2, employee1.getIndexInDepartment());
            Assert.assertEquals(1, employee2.getIndexInDepartment());
            Assert.assertEquals(0, employee3.getIndexInDepartment());
            
            department.getEmployees().add(1, employee1);
            assertList(department.getEmployees(), employee3, employee1, employee2);
            Assert.assertEquals(1, employee1.getIndexInDepartment());
            Assert.assertEquals(2, employee2.getIndexInDepartment());
            Assert.assertEquals(0, employee3.getIndexInDepartment());
            
            department.getEmployees().add(1, employee2);
            assertList(department.getEmployees(), employee3, employee2, employee1);
            Assert.assertEquals(2, employee1.getIndexInDepartment());
            Assert.assertEquals(1, employee2.getIndexInDepartment());
            Assert.assertEquals(0, employee3.getIndexInDepartment());
            
            department.getEmployees().add(3, employee3);
            assertList(department.getEmployees(), employee2, employee1, employee3);
            Assert.assertEquals(1, employee1.getIndexInDepartment());
            Assert.assertEquals(0, employee2.getIndexInDepartment());
            Assert.assertEquals(2, employee3.getIndexInDepartment());
            
            department.getEmployees().add(3, employee2);
            assertList(department.getEmployees(), employee1, employee3, employee2);
            Assert.assertEquals(0, employee1.getIndexInDepartment());
            Assert.assertEquals(2, employee2.getIndexInDepartment());
            Assert.assertEquals(1, employee3.getIndexInDepartment());
        }
        
        void testChangeOrderByEmployee() {
            
            Department department = this.new Department();
            Employee employee1 = new Employee();
            Employee employee2 = new Employee();
            Employee employee3 = new Employee();
            department.getEmployees().add(employee1);
            department.getEmployees().add(employee2);
            department.getEmployees().add(employee3);
            assertList(department.getEmployees(), employee1, employee2, employee3);
            Assert.assertEquals(0, employee1.getIndexInDepartment());
            Assert.assertEquals(1, employee2.getIndexInDepartment());
            Assert.assertEquals(2, employee3.getIndexInDepartment());
            
            employee2.setIndexInDepartment(0);
            assertList(department.getEmployees(), employee2, employee1, employee3);
            Assert.assertEquals(1, employee1.getIndexInDepartment());
            Assert.assertEquals(0, employee2.getIndexInDepartment());
            Assert.assertEquals(2, employee3.getIndexInDepartment());
            
            employee3.setIndexInDepartment(0);
            assertList(department.getEmployees(), employee3, employee2, employee1);
            Assert.assertEquals(2, employee1.getIndexInDepartment());
            Assert.assertEquals(1, employee2.getIndexInDepartment());
            Assert.assertEquals(0, employee3.getIndexInDepartment());
            
            employee1.setIndexInDepartment(1);
            assertList(department.getEmployees(), employee3, employee1, employee2);
            Assert.assertEquals(1, employee1.getIndexInDepartment());
            Assert.assertEquals(2, employee2.getIndexInDepartment());
            Assert.assertEquals(0, employee3.getIndexInDepartment());
            
            employee2.setIndexInDepartment(1);
            assertList(department.getEmployees(), employee3, employee2, employee1);
            Assert.assertEquals(2, employee1.getIndexInDepartment());
            Assert.assertEquals(1, employee2.getIndexInDepartment());
            Assert.assertEquals(0, employee3.getIndexInDepartment());
            
            employee3.setIndexInDepartment(2);
            assertList(department.getEmployees(), employee2, employee1, employee3);
            Assert.assertEquals(1, employee1.getIndexInDepartment());
            Assert.assertEquals(0, employee2.getIndexInDepartment());
            Assert.assertEquals(2, employee3.getIndexInDepartment());
            
            employee2.setIndexInDepartment(2);
            assertList(department.getEmployees(), employee1, employee3, employee2);
            Assert.assertEquals(0, employee1.getIndexInDepartment());
            Assert.assertEquals(2, employee2.getIndexInDepartment());
            Assert.assertEquals(1, employee3.getIndexInDepartment());
        }
        
        @SuppressWarnings("unchecked")
        private static <E> void assertList(List<E> list, E ... elements) {
            Assert.assertEquals(elements.length, list.size());
            for (int i = 0; i < elements.length; i++) {
                Assert.assertEquals(elements[i], list.get(i));
            }
        }
    
        class Department {
            
            private AssociatedList<Department, Employee> employees = 
                new AssociatedList<Department, Employee>() {
                
                    private static final long serialVersionUID = -4603128067694615895L;
                    
                    public Department getOwner() {
                        return Department.this;
                    }
                    
                    @Override
                    protected AssociatedEndpointType onGetOppositeEndpointType() {
                        return AssociatedEndpointType.INDEXED_REFERENCE;
                    }

                    @Override
                    public AssociatedEndpoint<Employee, Department> onGetOppositeEndpoint(Employee opposite) {
                        return opposite.departmentReference;
                    }
    
                    @Override
                    protected RootData<Employee> createRootData() {
                        return new RootData<Employee>() {
                            
                            private static final long serialVersionUID = -4458324837612547812L;

                            @Override
                            protected MAList<Employee> createDefaultBase(
                                    UnifiedComparator<? super Employee> unifiedComparator) {
                                return TestSite.this.employeeListFactory.createEmployeeList();
                            }
                        };
                    }
                };
                
            List<Employee> getEmployees() {
                return this.employees;
            }
            
        }
        
        static class Employee {
            
            private AssociatedIndexedReference<Employee, Department> departmentReference =
                new AssociatedIndexedReference<Employee, Department>() {
                
                    private static final long serialVersionUID = -4657564225237013393L;
                    
                    public Employee getOwner() {
                        return Employee.this;
                    }
                    
                    @Override
                    public AssociatedEndpoint<Department, Employee> onGetOppositeEndpoint(Department opposite) {
                        return opposite.employees;
                    }
                };
                
            Department getDepartment() {
                return this.departmentReference.get();
            }
            
            void setDepartment(Department department) {
                this.departmentReference.set(department);
            }
            
            int getIndexInDepartment() {
                return this.departmentReference.getIndex();
            }
            
            void setIndexInDepartment(int index) {
                this.departmentReference.setIndex(index);
            }
            
            void setDepartment(int index, Department department) {
                this.departmentReference.set(index, department);
            }
            
        }
        
    }
    
}
