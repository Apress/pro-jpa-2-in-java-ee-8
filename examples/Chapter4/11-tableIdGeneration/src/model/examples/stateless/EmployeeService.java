package examples.stateless;

import java.util.Collection;

import examples.model.Employee;

public interface EmployeeService {
    public Employee createEmployee(String name, long salary, 
            String street, String city, String state, String zip);
    public Collection<Employee> findAllEmployees();
}
