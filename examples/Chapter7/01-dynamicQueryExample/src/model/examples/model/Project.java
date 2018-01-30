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
    private Collection<Employee> employees;

    public Project() {
        employees = new ArrayList<Employee>();
    }

    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public Collection<Employee> getEmployees() {
        return employees;
    }
    
    public String toString() {
        return "Project id: " + getId() + ", name: " + getName();
    }
}
