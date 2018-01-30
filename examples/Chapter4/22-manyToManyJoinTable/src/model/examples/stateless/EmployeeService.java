package examples.stateless;

import java.util.Collection;

import examples.model.Employee;

public interface EmployeeService {
    public Employee createEmployee(String name, long salary);
    public Employee addEmployeeProject(int empId, int projId);
    public Collection<Employee> findAllEmployees();
}
