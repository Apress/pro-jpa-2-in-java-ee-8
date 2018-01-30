package examples.stateful;

import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.Remove;

import javax.enterprise.context.SessionScoped;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.SynchronizationType;

import examples.model.Employee;

@Stateful
@SessionScoped
public class EmployeeService {

    @PersistenceContext(type=PersistenceContextType.EXTENDED, 
    	                synchronization=SynchronizationType.UNSYNCHRONIZED,
                        unitName="EmployeeService")
    EntityManager em;

    Employee currentEmployee;
    
    public Employee getCurrentEmployee() { return currentEmployee; }
    
    public Employee loadEmployee(int id) {
        Employee emp = em.find(Employee.class, id);
        currentEmployee = emp;
        return emp;
    }

    public List findAll() {
        return em.createQuery("SELECT e FROM Employee e")
                 .getResultList();
    }

    // End of employee edit
    public void saveChangesToEmployee() {}
    
    public void cancel() {
        em.detach(currentEmployee);
    }

    // End conversation
    public void processAllChanges() {
        em.joinTransaction();
    }

    public void abandonAllChanges() {
        em.clear();
    }
}