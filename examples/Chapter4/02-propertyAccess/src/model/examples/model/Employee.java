package examples.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee {
    private int id;
    private String name;
    private long wage;

    @Id
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
        return wage;
    }

    public void setSalary(long salary) {
        this.wage = salary;
    }

    public String toString() {
        return "Employee id: " + getId() + " name: " + getName() + " salary: " + getSalary();
    }
}
