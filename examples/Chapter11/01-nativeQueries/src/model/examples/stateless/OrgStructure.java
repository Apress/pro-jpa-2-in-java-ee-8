package examples.stateless;

import java.util.List;

public interface OrgStructure {
    public List findEmployeesReportingTo(int managerId);
    public List findAllEmployees();
}
