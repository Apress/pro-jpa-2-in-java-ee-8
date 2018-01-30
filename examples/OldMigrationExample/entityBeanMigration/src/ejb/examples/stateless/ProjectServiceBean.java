package examples.stateless;						 

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import examples.model.ApplicationException;
import examples.model.Employee;
import examples.model.Project;

@Stateless
public class ProjectServiceBean implements ProjectService {
    //@PersistenceContext(name="EmployeeService")
	@PersistenceContext(name="ProjectService", unitName="EmployeeService")
    private EntityManager em;

    public void addEmployeeToProject(int projectId, int empId) 
        throws ApplicationException {
        Project project = em.find(Project.class, projectId);
        if (project == null)
            throw new ApplicationException("Unknown project id: " + projectId);
        Employee emp = em.find(Employee.class, empId);
        if (emp == null)
            throw new ApplicationException("Unknown employee id: " + empId);
        project.getEmployees().add(emp);
        emp.getProjects().add(project);
    }
    
    // ...
}
