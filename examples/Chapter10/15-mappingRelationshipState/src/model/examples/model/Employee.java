package examples.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Employee {
    @Id private int id;

    private String name;
    private long salary;

    @OneToMany(mappedBy="employee")
    private Collection<ProjectAssignment> assignments;

    public Employee() {
        assignments = new ArrayList<ProjectAssignment>();
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }
    
    public Collection<ProjectAssignment> getAssignments() {
        return assignments;
    }

    public String toString() {
        return "Employee id: " + getId() + " name: " + getName();
    }
}
