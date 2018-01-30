package examples.stateless;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import examples.model.Employee;
import examples.model.Phone;

@Stateless
public class EmployeeService {
    @PersistenceContext(unitName="EmployeeService")
    EntityManager em;

    public void removeEmployee(int empId) {
        Employee emp = em.find(Employee.class, empId);
        em.remove(emp);

    }
    
    public List<Employee> findAllEmployees() {
        return em.createQuery("SELECT e FROM Employee e", Employee.class)
                 .getResultList();
    }
    
    public List<Phone> findAllPhones() {
        return em.createQuery("SELECT p FROM Phone p", Phone.class)
                 .getResultList();
    }
}
