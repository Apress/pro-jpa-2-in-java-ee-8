package examples.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Project {
    @Id
    protected int id;
    protected String name;
    @ManyToMany(mappedBy="projects")
    protected Collection<Employee> employees = new ArrayList<Employee>();
    
    public int getId() {
        return id;
    }
    
    public void setId(int projectNo) {
        this.id = projectNo;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String projectName) {
        this.name = projectName;
    }
    
    public Collection<Employee> getEmployees() {
        return employees;
    }
    
    public void addEmployee(Employee employee) {
        if (!getEmployees().contains(employee)) {
            getEmployees().add(employee);
        }
        if (!employee.getProjects().contains(this)) {
            employee.getProjects().add(this);
        }
    }
    
    public String toString() {
        return getClass().getSimpleName() + 
                " no: " + getId() + 
                ", name: " + getName();
    }
}
