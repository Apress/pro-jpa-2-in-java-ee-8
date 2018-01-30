package examples.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Employee {
    @Id
    private int id;
    private String name;
    
    @ManyToOne
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

    public void setName(String name) {
        this.name = name;
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
               ", dept: " + ((getDepartment() == null) ? null : getDepartment().getName());
    }
}
