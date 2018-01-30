package examples.stateless;

import java.util.Collection;

import examples.model.Employee;
import examples.model.EmployeeType;

public interface EmployeeService {
    public Employee createEmployee(int id, String name, long salary, EmployeeType type);
    public Collection<Employee> findAllEmployees();
}
