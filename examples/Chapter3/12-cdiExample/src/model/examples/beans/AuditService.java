package examples.beans;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AuditService {
    int auditCount = 1;
    
    public String audit() {
        return "Audit performed." + "   Audit # - " + auditCount++;
    }
}

