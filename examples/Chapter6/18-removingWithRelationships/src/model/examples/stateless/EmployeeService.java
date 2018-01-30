package examples.stateless;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import examples.model.Employee;
import examples.model.ParkingSpace;

@Stateless
public class EmployeeService {
    @PersistenceContext(unitName="EmployeeService")
    EntityManager em;

    public void removeParkingSpace(int empId) {
        Employee emp = em.find(Employee.class, empId);
        ParkingSpace ps = emp.getParkingSpace();
        ps.setEmployee(null);
        emp.setParkingSpace(null);
        em.remove(ps);
    }

    public void removeParkingSpaceWithFailure(int empId) {
        // forgetting to null out the relationship will cause a
        // db constraint failure
        Employee emp = em.find(Employee.class, empId);
        em.remove(emp.getParkingSpace());
    }
    
    public List<Employee> findAllEmployees() {
        return em.createQuery("SELECT e FROM Employee e", Employee.class)
                 .getResultList();
    }
}
