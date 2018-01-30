package examples.stateless;

import java.util.List;
import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import examples.model.Employee;
import examples.model.Project;
import examples.model.ProjectAssignment;

@Stateless
public class EmployeeService {
    @PersistenceContext(unitName="EmployeeService")
    protected EntityManager em;

    public Employee createEmployee(int id, 
            String name, long salary) {
        Employee emp = new Employee();
        emp.setId(id);
        emp.setName(name);
        emp.setSalary(salary);
        em.persist(emp);
        
        return emp;
    }

    public void addEmployeeProject(int id, int projId) {
        Employee emp = findEmployee(id);
        Project proj = em.find(Project.class, projId);
        ProjectAssignment assign = new ProjectAssignment(emp, proj);
        assign.setStartDate(new Date());
        em.persist(assign);
        emp.getAssignments().add(assign);
        proj.getAssignments().add(assign);
    }

    public Employee findEmployee(int id) {
        return em.find(Employee.class, id);
    }

    public List<Employee> findAllEmployees() {
        return em.createQuery("SELECT e FROM Employee e", Employee.class)
                 .getResultList();
    }

    public List<Project> findAllProjects() {
        return em.createQuery("SELECT p FROM Project p", Project.class)
                 .getResultList();
    }

    public List<ProjectAssignment> findAllAssignments() {
        return em.createQuery("SELECT a FROM ProjectAssignment a", ProjectAssignment.class)
                 .getResultList();
    }
}
