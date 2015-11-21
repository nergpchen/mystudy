package org.babyfishdemo.distinctlimitquery;

import java.util.Map;

import junit.framework.Assert;

import org.babyfish.collection.HashMap;
import org.babyfish.hibernate.jpa.HibernatePersistenceProvider;
import org.babyfish.persistence.QueryType;
import org.babyfish.persistence.XEntityManager;
import org.babyfish.persistence.XEntityManagerFactory;
import org.babyfish.persistence.XTypedQuery;
import org.babyfishdemo.distinctlimitquery.base.AbstractTest;
import org.babyfishdemo.distinctlimitquery.entities.Company;
import org.babyfishdemo.distinctlimitquery.entities.Employee;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Tao Chen
 */
public class UnlimitedCountOptimizationTest extends AbstractTest {

    protected static XEntityManagerFactory strictDbSchemaEntityManagerFactory;
    
    @BeforeClass
    public static void initEntityManagerFactory() {
        initEntityManagerFactory(false);
        Map<String, Object> properties = new HashMap<>();
        properties.put("babyfish.hibernate.strict_db_schema", "true");
        properties.put("hibernate.hbm2ddl.auto", "validate");
        strictDbSchemaEntityManagerFactory =
                new HibernatePersistenceProvider("persistence.xml")
                .createEntityManagerFactory(null, properties);
    }
    
    @AfterClass
    public static void disposeStrictDbSchemaEntityManagerFactory() {
        XEntityManagerFactory semf = strictDbSchemaEntityManagerFactory;
        if (semf != null) {
            strictDbSchemaEntityManagerFactory = null;
            semf.close();
        }
    }
    
    @Test
    public void testCountWhenAllCollectionJoinsAreLeft() {
        
        String hql = 
                "from Company c "
                + "left join fetch c.departments d "
                + "left join fetch d.employees e";
        
        try (XEntityManager em = entityManagerFactory.createEntityManager()) {
            XTypedQuery<Company> query =
                    em
                    .createQuery(hql, Company.class)
                    .setQueryType(QueryType.DISTINCT); //DISTINCT is default, so actually unnecessary statement
            Assert.assertEquals(2, query.getUnlimitedCount());
        }
        
        try (XEntityManager em = entityManagerFactory.createEntityManager()) {
            XTypedQuery<Company> query =
                    em
                    .createQuery(hql, Company.class)
                    .setQueryType(QueryType.RESULT);
            Assert.assertEquals(12, query.getUnlimitedCount());
        }
        
        Assert.assertEquals(2, this.preparedSqlList.size());
        Assert.assertEquals(
                "select count(company0_.COMPANY_ID) "
                + "from COMPANY company0_", 
                this.preparedSqlList.get(0)
        );
        Assert.assertEquals(
                "select count(company0_.COMPANY_ID) "
                + "from COMPANY company0_ "
                + "left outer join DEPARTMENT department1_ "
                +     "on company0_.COMPANY_ID=department1_.company_COMPANY_ID "
                + "left outer join EMPLOYEE employees2_ "
                +     "on department1_.DEPARTMENT_ID=employees2_.department_DEPARTMENT_ID", 
                this.preparedSqlList.get(1)
        );
    }
    
    @Test
    public void testCountWhenNotAllCollectionJoinsAreLeft() {
        
        String hql = 
                "from Company c "
                + "left join fetch c.departments d "
                + "inner join fetch d.employees";
        
        try (XEntityManager em = entityManagerFactory.createEntityManager()) {
            XTypedQuery<Company> query =
                    em
                    .createQuery(hql, Company.class)
                    .setQueryType(QueryType.DISTINCT); //DISTINCT is default, so actually unnecessary statement
            Assert.assertEquals(2, query.getUnlimitedCount());
        }
        
        try (XEntityManager em = entityManagerFactory.createEntityManager()) {
            XTypedQuery<Company> query =
                    em
                    .createQuery(hql, Company.class)
                    .setQueryType(QueryType.RESULT);
            Assert.assertEquals(12, query.getUnlimitedCount());
        }
        
        Assert.assertEquals(2, this.preparedSqlList.size());
        Assert.assertEquals(
                "select count(distinct company0_.COMPANY_ID) "
                + "from COMPANY company0_ "
                + "left outer join DEPARTMENT department1_ "
                +     "on company0_.COMPANY_ID=department1_.company_COMPANY_ID "
                + "inner join EMPLOYEE employees2_ "
                +     "on department1_.DEPARTMENT_ID=employees2_.department_DEPARTMENT_ID", 
                this.preparedSqlList.get(0)
        );
        Assert.assertEquals(
                "select count(company0_.COMPANY_ID) "
                + "from COMPANY company0_ "
                + "left outer join DEPARTMENT department1_ "
                +     "on company0_.COMPANY_ID=department1_.company_COMPANY_ID "
                + "inner join EMPLOYEE employees2_ "
                +     "on department1_.DEPARTMENT_ID=employees2_.department_DEPARTMENT_ID", 
                this.preparedSqlList.get(1)
        );
    }
    
    @Test
    public void testCountWhenCollectionLeftJoinIsUsedByWhereCluase() {
        String hql = 
                "from Company c "
                + "left join fetch c.departments d "
                + "left join fetch d.employees e "
                + "where e.name like '%v%'";
        
        try (XEntityManager em = entityManagerFactory.createEntityManager()) {
            XTypedQuery<Company> query =
                    em
                    .createQuery(hql, Company.class)
                    .setQueryType(QueryType.DISTINCT); //DISTINCT is default, so actually unnecessary statement
            Assert.assertEquals(1, query.getUnlimitedCount());
        }
        
        try (XEntityManager em = entityManagerFactory.createEntityManager()) {
            XTypedQuery<Company> query =
                    em
                    .createQuery(hql, Company.class)
                    .setQueryType(QueryType.RESULT);
            Assert.assertEquals(1, query.getUnlimitedCount());
        }
        
        Assert.assertEquals(2, this.preparedSqlList.size());
        Assert.assertEquals(
                "select count(distinct company0_.COMPANY_ID) "
                + "from COMPANY company0_ "
                + "left outer join DEPARTMENT department1_ "
                +     "on company0_.COMPANY_ID=department1_.company_COMPANY_ID "
                + "left outer join EMPLOYEE employees2_ "
                +     "on department1_.DEPARTMENT_ID=employees2_.department_DEPARTMENT_ID "
                + "where employees2_.NAME like '%v%'", 
                this.preparedSqlList.get(0)
        );
        Assert.assertEquals(
                "select count(company0_.COMPANY_ID) "
                + "from COMPANY company0_ "
                + "left outer join DEPARTMENT department1_ "
                +     "on company0_.COMPANY_ID=department1_.company_COMPANY_ID "
                + "left outer join EMPLOYEE employees2_ "
                +     "on department1_.DEPARTMENT_ID=employees2_.department_DEPARTMENT_ID "
                + "where employees2_.NAME like '%v%'", 
                this.preparedSqlList.get(1)
        );
    }
    
    @Test
    public void testCountWhenAllReferenceJoinsAreLeft() {
        
        String hql = 
                "from Employee e "
                + "left join fetch e.department d "
                + "left join fetch d.company";
        
        try (XEntityManager em = entityManagerFactory.createEntityManager()) {
            XTypedQuery<Employee> query =
                    em
                    .createQuery(hql, Employee.class)
                    .setQueryType(QueryType.DISTINCT); //DISTINCT is default, so actually unnecessary statement
            Assert.assertEquals(12, query.getUnlimitedCount());
        }
        
        try (XEntityManager em = entityManagerFactory.createEntityManager()) {
            XTypedQuery<Employee> query =
                    em
                    .createQuery(hql, Employee.class)
                    .setQueryType(QueryType.RESULT);
            Assert.assertEquals(12, query.getUnlimitedCount());
        }
        
        Assert.assertEquals(2, this.preparedSqlList.size());
        Assert.assertEquals(
                "select count(employee0_.EMPLOYEE_ID) "
                + "from EMPLOYEE employee0_", 
                this.preparedSqlList.get(0)
        );
        Assert.assertEquals(
                "select count(employee0_.EMPLOYEE_ID) "
                + "from EMPLOYEE employee0_", 
                this.preparedSqlList.get(1)
        );
    }
    
    @Test
    public void testCountWhenNotAllReferenceJoinsAreLeft() {
        
        String hql = 
                "from Employee e "
                + "left join fetch e.department d "
                + "inner join fetch d.company";
        
        try (XEntityManager em = entityManagerFactory.createEntityManager()) {
            XTypedQuery<Employee> query =
                    em
                    .createQuery(hql, Employee.class)
                    .setQueryType(QueryType.DISTINCT); //DISTINCT is default, so actually unnecessary statement
            Assert.assertEquals(12, query.getUnlimitedCount());
        }
        
        try (XEntityManager em = entityManagerFactory.createEntityManager()) {
            XTypedQuery<Employee> query =
                    em
                    .createQuery(hql, Employee.class)
                    .setQueryType(QueryType.RESULT);
            Assert.assertEquals(12, query.getUnlimitedCount());
        }
        
        Assert.assertEquals(2, this.preparedSqlList.size());
        Assert.assertEquals(
                "select count(employee0_.EMPLOYEE_ID) "
                + "from EMPLOYEE employee0_ "
                + "left outer join DEPARTMENT department1_ "
                +     "on employee0_.department_DEPARTMENT_ID=department1_.DEPARTMENT_ID "
                + "inner join COMPANY company2_ on "
                +     "department1_.company_COMPANY_ID=company2_.COMPANY_ID", 
                this.preparedSqlList.get(0)
        );
        Assert.assertEquals(
                "select count(employee0_.EMPLOYEE_ID) "
                + "from EMPLOYEE employee0_ "
                + "left outer join DEPARTMENT department1_ "
                +     "on employee0_.department_DEPARTMENT_ID=department1_.DEPARTMENT_ID "
                + "inner join COMPANY company2_ on "
                +     "department1_.company_COMPANY_ID=company2_.COMPANY_ID", 
                this.preparedSqlList.get(1)
        );
    }
    
    @Test
    public void testCountWhenReferenceJoinIsInnerButDbIsStrict() {
        
        String hql = 
                "from Employee e "
                + "left join fetch e.department d "
                + "inner join fetch d.company";
        
        try (XEntityManager em = strictDbSchemaEntityManagerFactory.createEntityManager()) {
            XTypedQuery<Employee> query =
                    em
                    .createQuery(hql, Employee.class)
                    .setQueryType(QueryType.DISTINCT); //DISTINCT is default, so actually unnecessary statement
            Assert.assertEquals(12, query.getUnlimitedCount());
        }
        
        try (XEntityManager em = strictDbSchemaEntityManagerFactory.createEntityManager()) {
            XTypedQuery<Employee> query =
                    em
                    .createQuery(hql, Employee.class)
                    .setQueryType(QueryType.RESULT);
            Assert.assertEquals(12, query.getUnlimitedCount());
        }
        
        Assert.assertEquals(2, this.preparedSqlList.size());
        Assert.assertEquals(
                "select count(employee0_.EMPLOYEE_ID) "
                + "from EMPLOYEE employee0_", 
                this.preparedSqlList.get(0)
        );
        Assert.assertEquals(
                "select count(employee0_.EMPLOYEE_ID) "
                + "from EMPLOYEE employee0_", 
                this.preparedSqlList.get(1)
        );
    }
    
    @Test
    public void testCountWhenReferenceLeftJoinIsUsedByWhereCluase() {
        
        String hql = 
                "from Employee e "
                + "left join fetch e.department d "
                + "left join fetch d.company c "
                + "where c.name like '%s%'";
        
        try (XEntityManager em = entityManagerFactory.createEntityManager()) {
            XTypedQuery<Employee> query =
                    em
                    .createQuery(hql, Employee.class)
                    .setQueryType(QueryType.DISTINCT); //DISTINCT is default, so actually unnecessary statement
            Assert.assertEquals(7, query.getUnlimitedCount());
        }
        
        try (XEntityManager em = entityManagerFactory.createEntityManager()) {
            XTypedQuery<Employee> query =
                    em
                    .createQuery(hql, Employee.class)
                    .setQueryType(QueryType.RESULT);
            Assert.assertEquals(7, query.getUnlimitedCount());
        }
        
        Assert.assertEquals(2, this.preparedSqlList.size());
        Assert.assertEquals(
                "select count(employee0_.EMPLOYEE_ID) "
                + "from EMPLOYEE employee0_ "
                + "left outer join DEPARTMENT department1_ "
                +     "on employee0_.department_DEPARTMENT_ID=department1_.DEPARTMENT_ID "
                + "left outer join COMPANY company2_ "
                +     "on department1_.company_COMPANY_ID=company2_.COMPANY_ID "
                + "where company2_.NAME like '%s%'", 
                this.preparedSqlList.get(0)
        );
        Assert.assertEquals(
                "select count(employee0_.EMPLOYEE_ID) "
                + "from EMPLOYEE employee0_ "
                + "left outer join DEPARTMENT department1_ "
                +     "on employee0_.department_DEPARTMENT_ID=department1_.DEPARTMENT_ID "
                + "left outer join COMPANY company2_ "
                +     "on department1_.company_COMPANY_ID=company2_.COMPANY_ID "
                + "where company2_.NAME like '%s%'",
                this.preparedSqlList.get(1)
        );
    }
}
