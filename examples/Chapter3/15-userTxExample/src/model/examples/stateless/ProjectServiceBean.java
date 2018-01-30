package examples.stateless;

import javax.ejb.Stateless;

@Stateless
public class ProjectServiceBean implements ProjectService {
    public void assignEmployeeToProject(int projectId, int empId) {
        // ...
        System.out.println("Adding Employee to Project...");
    }
    
    public void updateProjectStatistics() {
        // ...
        System.out.println("Updating Project Stats...");
    }
    
    // ...
}
