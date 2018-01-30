package examples.stateless;

import java.util.Collection;

import examples.model.Company;
import examples.model.Employee;

public interface EmployeeService {
    public Employee createEmployeeAndAddress(int id, String name, long salary,
                      String street, String city, String state, String zip);
    public Company createCompanyAndAddress(int id,
                      String street, String city, String state, String zip);
    public Collection<Employee> findAllEmployees();
    public Collection<Company> findAllCompanies();
}
