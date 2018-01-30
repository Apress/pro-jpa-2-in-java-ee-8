package examples.stateless;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class DepartmentService {
    @EJB AuditService audit;

    public String performAudit() {
        return audit.audit();
    }
}

