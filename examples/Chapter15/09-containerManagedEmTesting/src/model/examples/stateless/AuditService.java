package examples.stateless;

public interface AuditService {
    public void logTransaction(int empNo, String action);
}
