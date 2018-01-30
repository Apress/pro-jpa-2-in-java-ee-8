package examples.stateless;

import java.util.Collection;

import examples.model.Employee;

public interface EmployeeService {
    public Employee createEmployee(String name, long salary);
    public Employee setEmployeeParkingSpace(int empId, int spaceId);
    public Collection<Employee> findAllEmployees();
}
