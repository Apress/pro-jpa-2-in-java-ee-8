package examples.stateless;

import java.util.Calendar;
import java.util.Collection;

import examples.model.Employee;

public interface EmployeeService {
    public Employee createEmployee(int id, String name, long salary, Calendar dob);
    public Collection<Employee> findAllEmployees();
}
