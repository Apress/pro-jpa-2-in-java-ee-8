package examples.stateless;

import java.util.Collection;

import examples.model.Employee;

public interface EmployeeService {
    public Employee createEmployee(int id, String name, long salary);
    public Collection<Employee> findAllEmployees();
}
