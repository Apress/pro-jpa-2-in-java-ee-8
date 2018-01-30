package examples.stateless;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.NoResultException;

import examples.model.Employee;

@Stateless
public class EmployeeService {
    @PersistenceContext(unitName="EmployeeService")
    protected EntityManager em;

    public Employee createEmployee(String country, int id, 
                                   String name, long salary) {
        Employee emp = new Employee(country, id);
        emp.setName(name);
        emp.setSalary(salary);
        em.persist(emp);
        
        return emp;
    }

    public Employee findEmployee(String country, int id) {
        if (country == null || country.isEmpty()) {
            return null;
        }
        try {
            return (Employee)
                em.createQuery("SELECT e " + 
                               "FROM Employee e " +
                               "WHERE e.id.country = ?1 AND e.id.id = ?2")
                  .setParameter(1, country)
                  .setParameter(2, id)
                  .getSingleResult();
        } catch (NoResultException nrEx) {
            return null;
        }
    }

    public List<Employee> findAllEmployees() {
        return em.createQuery("SELECT e FROM Employee e", Employee.class)
                 .getResultList();
    }
}
