package examples.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Project {
    @Id private int id;
    private String name;
    
    @OneToMany(mappedBy="project")
    private Collection<ProjectAssignment> assignments;

    public Project() {
        assignments = new ArrayList<ProjectAssignment>();
    }
    
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
    
    public Collection<ProjectAssignment> getAssignments() {
        return assignments;
    }
    
    public String toString() {
        return getClass().getName().substring(getClass().getName().lastIndexOf('.')+1) + 
                " no: " + getId() + 
                ", name: " + getName();
    }
}
