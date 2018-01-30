package examples.stateless;

import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import examples.model.Employee;
import examples.model.VacationEntry;

@Stateless
public class EmployeeService {
    @PersistenceContext(unitName="EmployeeService")
    protected EntityManager em;

    public Employee createEmployee(String name, long salary) {
        Employee emp = new Employee();
        emp.setName(name);
        emp.setSalary(salary);
        
        em.persist(emp);        
        return emp;
    }

    public Employee addEmployeeNickname(int empId, String nickname) {
        Employee emp = em.find(Employee.class, empId);
        if (emp != null) {
            emp.getNickNames().add(nickname);
        }
        return emp;
    }
    
    public Collection<Employee> findAllEmployees() {
        TypedQuery query = em.createQuery("SELECT e FROM Employee e", Employee.class);
        Collection<Employee> emps = query.getResultList();
        // trigger lazy collections
        for (Employee emp : emps) {
			emp.getVacationBookings().size();
			emp.getNickNames().size();
		}
		return emps;
    }
}
