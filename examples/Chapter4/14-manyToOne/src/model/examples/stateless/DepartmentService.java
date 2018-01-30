package examples.stateless;

import java.util.Collection;

import examples.model.Department;

public interface DepartmentService {
    public Department createDepartment(String name);
    public Collection<Department> findAllDepartments();
}
