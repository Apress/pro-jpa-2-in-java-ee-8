package examples.stateless;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import examples.model.Address;
import examples.model.Employee;

@Stateless
public class EmployeeServiceBean implements EmployeeService {
    @PersistenceContext(unitName="EmployeeService")
    protected EntityManager em;

    public Employee createEmployee(String name, long salary, 
            String street, String city, String state, String zip) {
        Employee emp = new Employee();
        emp.setName(name);
        emp.setSalary(salary);
        
        Address addr = new Address();
        addr.setStreet(street);
        addr.setCity(city);
        addr.setState(state);
        addr.setZip(zip);
        emp.setAddress(addr);

        em.persist(emp);
        em.persist(addr);
        
        return emp;
    }

    public Collection<Employee> findAllEmployees() {
        Query query = em.createQuery("SELECT e FROM Employee e");
        return (Collection<Employee>) query.getResultList();
    }
}
