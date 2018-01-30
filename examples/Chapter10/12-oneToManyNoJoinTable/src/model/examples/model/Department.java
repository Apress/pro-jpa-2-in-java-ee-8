package examples.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Department {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany
    @JoinColumn(name="DEPT_ID")
    private Collection<Employee> employees;

    public Department() {
        employees = new ArrayList<Employee>();
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
    
    public void setName(String deptName) {
        this.name = deptName;
    }
    
    public Collection<Employee> getEmployees() {
        return employees;
    }

    public String toString() {
    	String emps = "";
    	for (Employee e : employees) 
            emps += "(" + e.getName() + ")";
        return "Department id: " + getId() + 
               ", name: " + getName() +
               ", employees: {" + emps + "}";
    }
}
