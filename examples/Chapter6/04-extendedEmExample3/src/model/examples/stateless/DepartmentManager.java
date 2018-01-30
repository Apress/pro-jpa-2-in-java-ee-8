package examples.stateless;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import examples.model.Department;
import examples.model.Employee;

@Stateful
public class DepartmentManager {
    @PersistenceContext(unitName="EmployeeService",
                        type=PersistenceContextType.EXTENDED)
    EntityManager em;
    Department dept;


    public void init(int deptId) {
        dept = em.find(Department.class, deptId);
    }
    
    public Department getDepartment() {
        return dept;
    }

    public void setName(String name) {
        dept.setName(name);
    }
    
    public void addEmployee(int empId) {
        Employee emp = em.find(Employee.class, empId);
        dept.getEmployees().add(emp);
        emp.setDepartment(dept);
    }

    @Remove
    public void finished() {
    }
}

