package examples.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;

@Entity
public class Employee {
    @Id
    private int id;
    private String name;
    private long salary;
    
    @ManyToOne(fetch=FetchType.LAZY)
    private Department department;
    

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }


    public long getSalary() {
        return salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department dept) {
        this.department = dept;
    }
    
    public String toString() {
        return "Employee " + getId() + 
               ": name: " + getName() +
               ", salary: " + getSalary();
    }
}
