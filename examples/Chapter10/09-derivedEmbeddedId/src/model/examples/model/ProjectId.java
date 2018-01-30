package examples.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class ProjectId implements Serializable {
    @Column(name="P_NAME")
    private String name;

    @Embedded
    private DeptId dept;
    
    public DeptId getDept() {
        return dept;
    }

    public void setDept(DeptId dept) {
        this.dept = dept;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProjectId() {}
    
    public ProjectId(DeptId deptId, String name) {
        this.dept = deptId;
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

    public boolean equals(Object o) {
        return ((o instanceof ProjectId) && 
                name.equals(((ProjectId) o).getName()));

    }

    public int hashCode() {
        return name.hashCode();
    }

    public String toString() {
        return "ProjectId(" + getName() + "," + getDept() + ")";
    }
}