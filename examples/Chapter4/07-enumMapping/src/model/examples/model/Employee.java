package examples.model;

import static javax.persistence.EnumType.STRING;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class Employee {
    @Id
    private int id;
    private String name;
    private long salary;
    private EmployeeType type;
    @Enumerated(STRING)
    private EmployeeType previousType;
    
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

    public EmployeeType getType() {
        return type;
    }

    public void setType(EmployeeType type) {
        this.previousType = this.type;
        if (this.previousType == null) {
            this.previousType = type;
        }
        this.type = type;
    }

    public EmployeeType getPreviousType() {
        return previousType;
    }
    
    public String toString() {
        return "Employee id: " + getId() + " name: " + getName() + 
        " salary: " + getSalary() + " type: " + getType();
    }
}
