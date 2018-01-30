package examples.stateless;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import examples.model.Employee;
import examples.model.EmployeeId;
import examples.model.Project;

@Stateless
public class EmployeeService {
    @PersistenceContext(unitName="EmployeeService")
    protected EntityManager em;

    public Employee createEmployee(String country, int id, 
            String name, long salary) {
        Employee emp = new Employee();
        emp.setCountry(country);
        emp.setId(id);
        emp.setName(name);
        emp.setSalary(salary);
        em.persist(emp);
        
        return emp;
    }

    public void setEmployeeManager(String country, int id, String mgrCountry, int mgrId) {
        Employee emp = findEmployee(country, id);
        Employee mgr = findEmployee(mgrCountry, mgrId);
        if ((emp != null) && (mgr != null)) {
            if (mgr != emp.getManager()) { 
            	// Assume has no existing manager
            	// (otherwise we would have to remove the emp from previous manager directs)
                emp.setManager(mgr);
                mgr.getDirects().add(emp);
            }
        }
    }

    public void addEmployeeProject(String country, int id, int projId) {
        Employee emp = findEmployee(country, id);
        Project proj = em.find(Project.class, projId);
        if ((emp != null) && (proj != null)) {
            if (!emp.getProjects().contains(proj)) { 
                emp.getProjects().add(proj);
                proj.getEmployees().add(emp);
            }
        }
    }

    public Employee findEmployee(String country, int id) {
        return em.find(Employee.class, new EmployeeId(country, id));
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
