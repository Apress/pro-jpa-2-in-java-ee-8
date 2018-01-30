package examples.stateless;

import examples.model.ApplicationException;

public interface ProjectService {

    public void addEmployeeToProject(int projectId, int empId)
        throws ApplicationException;    
    // ...
}

