package examples.stateful;

import static javax.persistence.PersistenceContextType.EXTENDED;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import examples.model.Employee;
import examples.model.Project;

@Stateful
public class ProjectManager {
    @PersistenceContext(unitName="EmployeeService", type=EXTENDED)
    EntityManager em;
    
    TypedQuery<Employee> unassignedQuery;
    
    @PostConstruct
    public void init() {
        unassignedQuery = 
            em.createQuery("SELECT e " +
                           "FROM Employee e " +
                           "WHERE e.projects IS EMPTY", Employee.class);
    }

    public List<Employee> findEmployeesWithoutProjects() {
        return unassignedQuery.getResultList();
    }

    public List<Employee> findProjectEmployees(String projectName) {
        return em.createQuery("SELECT e " +
                              "FROM Project p JOIN p.employees e " +
                              "WHERE p.name = :project " +
                              "ORDER BY e.name", Employee.class)
                 .setParameter("project", projectName)
                 .getResultList();
    }

    public List<Employee> findAllEmployees() {
        return em.createQuery("SELECT e FROM Employee e", Employee.class)
                 .getResultList();
    }

    public List<Project> findAllProjects() {
        return em.createQuery("SELECT p FROM Project p", Project.class)
                 .getResultList();
    }
}
