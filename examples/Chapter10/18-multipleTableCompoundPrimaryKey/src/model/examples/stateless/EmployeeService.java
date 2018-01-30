package examples.stateless;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import examples.model.Employee;
import examples.model.EmployeeId;

@Stateless
public class EmployeeService {
    @PersistenceContext(unitName="EmployeeService")
    protected EntityManager em;

    public Employee createEmployee(String country, int id, 
            String name, long salary, String comments) {
        Employee emp = new Employee();
        emp.setCountry(country);
        emp.setId(id);
        emp.setName(name);
        emp.setSalary(salary);
        emp.setComments(comments.toCharArray());
        em.persist(emp);
        
        return emp;
    }

    public Employee findEmployee(String country, int id) {
        return em.find(Employee.class, new EmployeeId(country, id));
    }

    public List<Employee> findAllEmployees() {
        return em.createQuery("SELECT e FROM Employee e", Employee.class)
                 .getResultList();
    }
}
