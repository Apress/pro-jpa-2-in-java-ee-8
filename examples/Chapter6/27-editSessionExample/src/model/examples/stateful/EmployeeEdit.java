package examples.stateful;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import examples.model.Employee;

@Stateful
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class EmployeeEdit {
    @PersistenceContext(type=PersistenceContextType.EXTENDED, 
                        unitName="EmployeeService")
    EntityManager em;
    Employee emp;
    
    public void begin(int id) {
        emp = em.find(Employee.class, id);
        if (emp == null) {
            throw new IllegalArgumentException("Unknown employee id: " + id);
        }
    }
    
    public Employee getEmployee() {
        return emp;
    }
    
    @Remove
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void save() {
        // Bug in EclipseLink - emp is not managed in extended pc outside of tx
        // Merge to make example work
        em.merge(emp);
    }
    
    @Remove
    public void cancel() {}
}