package examples.stateless;

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
import examples.model.ContactInfo;
import examples.model.Customer;
import examples.model.Phone;


@Stateless
public class EmployeeService {
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

        ContactInfo ci = new ContactInfo();
        ci.setResidence(addr);

        Phone phone = new Phone();
        phone.setNum("111-2222");
        phone.setType("home");
        ci.setPrimaryPhone(phone);
        Map<String, Phone> phones = new HashMap<String, Phone>();
        phones.put(phone.getType(), phone);
        ci.setPhones(phones);

        emp.setContactInfo(ci);
        
        em.persist(phone);
        em.persist(emp);
        
        return emp;
    }

    public Customer createCustomerAndAddress(int id,
            String street, String city, String state, String zip) {
        Customer cust = new Customer();
        cust.setId(id);

        Address addr = new Address();
        addr.setStreet(street);
        addr.setCity(city);
        addr.setState(state);
        addr.setZip(zip);

        ContactInfo ci = new ContactInfo();
        ci.setResidence(addr);

        Phone phone = new Phone();
        phone.setNum("123-4567");
        phone.setType("cell");
        ci.setPrimaryPhone(phone);
        Map<String, Phone> phones = new HashMap<String, Phone>();
        phones.put(phone.getType(), phone);
        ci.setPhones(phones);

        cust.setContactInfo(ci);

        em.persist(phone);
        em.persist(cust);
        
        return cust;
    }

    public List<Employee> findAllEmployees() {
        return em.createQuery("SELECT e FROM Employee e", Employee.class)
                 .getResultList();
    }
    
    public List<Customer> findAllCustomers() {
        return em.createQuery("SELECT c FROM Customer c", Customer.class)
                 .getResultList();
    }
}
