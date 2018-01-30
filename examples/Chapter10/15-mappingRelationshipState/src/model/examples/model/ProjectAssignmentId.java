package examples.model;

import java.io.Serializable;

public class ProjectAssignmentId implements Serializable {
    private int employee;
    private int project;
  
    public ProjectAssignmentId() {}
    public ProjectAssignmentId(int empId, int projectId) {
      this.employee = empId;
      this.project = projectId;
    }

    public int getEmployee() {
        return employee;
    }
    
    public int getProject() {
        return project;
    }

    public boolean equals(Object o) { 
        return ((o instanceof ProjectAssignmentId) && 
                employee == ((ProjectAssignmentId)o).getEmployee() &&
                project == ((ProjectAssignmentId) o).getProject());
    }

    public int hashCode() { 
        return employee + project; 
    }
}
