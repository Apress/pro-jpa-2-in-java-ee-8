package examples.stateless;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class DepartmentService {
    private AuditService audit;

    @EJB
    public void setAuditService(AuditService audit) {
        this.audit = audit;
    }

    public String performAudit() {
        return audit.audit();
    }
    
    // ...
}

