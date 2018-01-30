package examples.model;

import java.util.HashSet;
import java.util.Set;

public class Department {

    private int id;
    private String name;
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
