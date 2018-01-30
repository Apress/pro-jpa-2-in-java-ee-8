package examples.stateless;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import examples.model.Employee;

@Stateless
public class EmployeeServiceBean implements EmployeeService {
    EntityManager entityManager;
    AuditService auditService;

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.entityManager = em;
    }

    @EJB
    public void setAuditService(AuditService audit) {
        this.auditService = audit;
    }

    public void createEmployee(Employee emp) {
        entityManager.persist(emp);
        auditService.logTransaction(emp.getId(), "created employee");
    }
}
