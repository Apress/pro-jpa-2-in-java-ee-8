package examples.stateless;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import examples.model.Employee;
import examples.model.LogRecord;

@Stateless
public class AuditServiceBean implements AuditService {
    private EntityManager em;
    
    @PersistenceContext(unitName="hr")
    void setEntityManager(EntityManager em) {
        this.em = em;
    }
    
    public void logTransaction(int empNo, String action) {
        // verify employee number is valid
        if (em.find(Employee.class, empNo) == null) {
            throw new IllegalArgumentException("Unknown employee id");
        }
        System.out.println("Message: " + action);
        LogRecord lr = new LogRecord(empNo, action);
        em.persist(lr);
    }
}
