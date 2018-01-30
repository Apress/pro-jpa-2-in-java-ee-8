package examples.stateless;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import examples.model.ContractEmployee;
import examples.model.Employee;
import examples.model.EmployeeType;
import examples.model.FullTimeEmployee;
import examples.model.PartTimeEmployee;

@Stateless
public class EmployeeService {
    @PersistenceContext(unitName="EmployeeService")
    protected EntityManager em;
    
    public EntityManager getEntityManager() {
        return em;
    }

    public Employee createEmployee(int id, String name, EmployeeType type) {
        Employee emp = null;
        if (type == EmployeeType.PART_TIME_EMPLOYEE) {
            emp = new PartTimeEmployee();
        } else if (type == EmployeeType.FULL_TIME_EMPLOYEE) {
            emp = new FullTimeEmployee();
        } else {
            emp = new ContractEmployee();
        }
        emp.setId(id);
        emp.setName(name);
        getEntityManager().persist(emp);
        return emp;
    }

    public void removeEmployee(int id) {
        Employee emp = findEmployee(id);
        if (emp != null) {
            getEntityManager().remove(emp);
        }
    }

    public Employee changeEmployeeName(int id, String newName) {
        Employee emp = findEmployee(id);
        if (emp != null) {
            emp.setName(newName);
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
