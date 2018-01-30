package examples.stateless;

import java.util.List;
import java.util.Set;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.validation.Validator;
import javax.validation.ValidationException;
import javax.validation.ConstraintViolation;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import examples.model.Employee;

@Stateless
public class EmployeeService {
	
    @Resource
    Validator validator;
	
    @PersistenceContext(unitName="EmployeeService")
    protected EntityManager em;
    
    public EntityManager getEntityManager() {
        return em;
    }

    public void createEmployee(int id, String name, PrintWriter out) {
        Employee emp = new Employee();
        emp.setId(id);
        // Only set name if non-empty string
        if ((name != null) && (name.length() > 0)) {
            emp.setName(name);
        }    
        // Perform manual validation on the new employee
        Set<ConstraintViolation<Employee>> violations = validator.validate(emp);
        if (!violations.isEmpty()) {
            out.println("Validation failed. ConstraintViolations: " + violations + "<br/><hr/>");
        } else {
            getEntityManager().persist(emp);
        }
    }

    public void removeEmployee(int id) {
        Employee emp = findEmployee(id);
        if (emp != null) {
            getEntityManager().remove(emp);
        }
    }

    public Employee changeEmployeeName(int id, String newName) {
        Employee emp = findEmployee(id);
        // Let automatic validation occur here.
        if (emp != null) {
            if ((newName != null) && (newName.length() == 0)) {
                emp.setName(null);
            } else {
                emp.setName(newName);
            }
        }
        return emp;
    }

    public Employee findEmployee(int id) {
        return getEntityManager().find(Employee.class, id);
    }

    public List<Employee> findAllEmployees() {
        return getEntityManager().createQuery("SELECT e FROM Employee e", Employee.class)
                                 .getResultList();
    }
}
