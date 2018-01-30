package examples.stateless;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.SessionBean;
import javax.ejb.Stateless;

@Stateless
@EJB(name="audit", beanInterface=AuditService.class)
public class DepartmentService implements SessionBean {
    SessionContext context;
    AuditService audit;

    public void setSessionContext(SessionContext context) { 
        this.context = context; 
    }
    
    @PostConstruct
    public void init() {
        audit = (AuditService) context.lookup("audit");
    }

    public String performAudit() {
        return audit.audit();
    }
    
    // SessionBean methods 
    public void ejbRemove() {}
    public void ejbPassivate() {}
    public void ejbActivate() {}

    // ...
}

