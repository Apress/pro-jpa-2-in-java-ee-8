package examples.stateless;

import java.util.Collection;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import examples.model.Address;
import examples.model.Employee;
import examples.model.Company;


@Stateless
public class EmployeeServiceBean implements EmployeeService {
    @PersistenceContext(unitName="EmployeeService")
    protected EntityManager em;

    public Employee createEmployeeAndAddress(int id, String name, long salary,
            String street, String city, String state, String zip) {
        Employee emp = new Employee();
        emp.setId(id);
        emp.setName(name);
        emp.setSalary(salary);
        Address addr = new Address();
        addr.setStreet(street);
        addr.setCity(city);
        addr.setState(state);
        addr.setZip(zip);
        emp.setAddress(addr);
        em.persist(emp);
        
        return emp;
    }

    public Company createCompanyAndAddress(int id,
            String street, String city, String state, String zip) {
        Company c = new Company();
        c.setId(id);
        Address addr = new Address();
        addr.setStreet(street);
        addr.setCity(city);
        addr.setState(state);
        addr.setZip(zip);
        c.setAddress(addr);

        em.persist(c);        
        return c;
    }

    public Collection<Employee> findAllEmployees() {
        Query query = em.createQuery("SELECT e FROM Employee e");
        return (Collection<Employee>) query.getResultList();
    }
    
    public Collection<Company> findAllCompanies() {
        Query query = em.createQuery("SELECT c FROM Company c");
        return (Collection<Company>) query.getResultList();
    }
}
