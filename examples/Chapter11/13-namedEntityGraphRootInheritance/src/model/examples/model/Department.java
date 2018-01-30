package examples.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Department {
    @Id
    private int id;
    private String name;
    @OneToMany(mappedBy="department")
    private Set<Employee> employees = new HashSet<Employee>();

    public int getId() {
        return id;
    }
    
    public void setId(int deptNo) {
        this.id = deptNo;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String deptName) {
        this.name = deptName;
    }
    
    public Set<Employee> getEmployees() {
        return employees;
    }

    public String toString() {
        return "Department no: " + getId() + 
               ", name: " + getName();
    }
}
