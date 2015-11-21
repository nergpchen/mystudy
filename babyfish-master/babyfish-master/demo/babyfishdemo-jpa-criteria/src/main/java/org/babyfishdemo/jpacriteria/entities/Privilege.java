package org.babyfishdemo.jpacriteria.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.babyfish.persistence.instrument.JPAObjectModelInstrument;

/**
 * @author Tao Chen
 */
@JPAObjectModelInstrument
@Entity
@Table(name = "PRIVILEGE")
public class Privilege {

    @Id
    @Column(name = "PRIVILEGE_ID", nullable = false)
    private Long id;
    
    @Column(name = "NAME", length = 20, nullable = false)
    private String name;
    
    @ManyToMany
    @JoinTable(
            name = "PRIVILEGE_ROLE_MAPPING",
            joinColumns = @JoinColumn(name = "PRIVILEGE_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID")
    )
    private Set<Role> roles;
    
    @ManyToMany
    @JoinTable(
            name = "PRIVILEGE_EMPLOYEE_MAPPING",
            joinColumns = @JoinColumn(name = "PRIVILEGE_ID"),
            inverseJoinColumns = @JoinColumn(name = "Employee_ID")
    )
    private Set<Employee> employees;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
