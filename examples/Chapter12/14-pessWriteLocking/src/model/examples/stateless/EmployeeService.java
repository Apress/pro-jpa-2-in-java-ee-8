package examples.stateless;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.OptimisticLockException;

import examples.model.ChangeCollisionException;
import examples.model.Employee;
import examples.model.EmployeeStatus;

@Stateless
public class EmployeeService {
    @PersistenceContext(unitName="EmployeeService")
    private EntityManager em;

    public Employee createEmployee(int id, String name, EmployeeStatus status, double vacation) {
        Employee emp = new Employee();
        emp.setId(id);
        emp.setName(name);
        emp.setStatus(status);
        emp.setVacationDays(vacation);
        em.persist(emp);
        return emp;
    }
    
    protected void flushChanges() {
        try { 
            em.flush(); 
        } catch (OptimisticLockException optLockEx) {
            throw new ChangeCollisionException();
        }
    } 
    
    public List<Employee> findAllEmployees() {
        return em.createQuery("SELECT e FROM Employee e", Employee.class)
                 .getResultList();
    }
}

