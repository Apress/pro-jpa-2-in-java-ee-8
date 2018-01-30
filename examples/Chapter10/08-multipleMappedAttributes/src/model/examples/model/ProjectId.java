package examples.model;


import java.io.Serializable;

public class ProjectId implements Serializable {
    private String name;
    private DeptId dept;

    public ProjectId() {}
    
    public ProjectId(DeptId deptId, String name) {
        this.dept = deptId;
        this.name = name;
    }

    public DeptId getDept() {
        return dept;
    }

    public void setDept(DeptId dept) {
        this.dept = dept;
    }

    public void setName(String name) {
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
}