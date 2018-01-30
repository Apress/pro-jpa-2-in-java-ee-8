package examples.model;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Department {
    @EmbeddedId 
    private DeptId id;
    
    private String name;
    
    @OneToMany(mappedBy="department")
    private List<Project> projects;

    public Department() {
        projects = new ArrayList<Project>();
    }
    

    public DeptId getId() {
        return id;
    }

    public void setId(DeptId dept) {
        this.id = dept;
    }

    public String getName() {
        return name;
    }
        
    public void setName(String deptName) {
        this.name = deptName;
    }
    
    public List<Project> getProjects() {
        return projects;
    }

    public String toString() {
        return "Department id: " + getId() + 
               ", name: " + getName();
    }
}
