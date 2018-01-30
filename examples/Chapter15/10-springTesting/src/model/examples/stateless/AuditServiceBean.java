package examples.stateless;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import examples.model.Employee;
import examples.model.LogRecord;

@Stateless
public class AuditServiceBean implements AuditService {
    private EntityManager entityManager;
    
    @PersistenceContext(unitName="hr")
    public void setEntityManager(EntityManager em) {
        this.entityManager = em;
    }
    
    public void logTransaction(int empNo, String action) {
        // verify employee number is valid
        if (entityManager.find(Employee.class, empNo) == null) {
            throw new IllegalArgumentException("Unknown employee id");
        }
        System.out.println("Message: " + action);
        LogRecord lr = new LogRecord(empNo, action);
        entityManager.persist(lr);
    }
}
