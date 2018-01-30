package examples.stateless;

import javax.ejb.Stateless;

@Stateless
public class AuditService {
    public String audit() {
        return "Audit performed.";
    }
}

