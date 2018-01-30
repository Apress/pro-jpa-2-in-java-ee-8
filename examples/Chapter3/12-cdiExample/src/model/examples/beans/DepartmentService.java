package examples.beans;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Dependent
public class DepartmentService {
    @Inject AuditService audit;
    
    @Inject @EmployeeEM
    private EntityManager empEM;

    public String performAudit() {
        return audit.audit();
    }
    
    // ...
}

