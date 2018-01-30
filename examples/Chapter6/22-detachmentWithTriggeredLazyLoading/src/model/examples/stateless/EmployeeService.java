package examples.stateless;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import examples.model.Employee;

@Stateless
public class EmployeeService {
    @PersistenceContext(unitName="EmployeeService")
    private EntityManager em;
    
    public List<Employee> findAll() {
        List<Employee> emps = em.createQuery("SELECT e FROM Employee e", Employee.class)
                                .getResultList();
        for (Employee emp : emps) {
            if (emp.getDepartment() != null) {
                emp.getDepartment().getName();
            }
        }
        return emps;
    }
}
