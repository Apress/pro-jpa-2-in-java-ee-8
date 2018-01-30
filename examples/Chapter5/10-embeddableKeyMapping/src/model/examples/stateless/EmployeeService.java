package examples.stateless;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import examples.model.Department;
import examples.model.Employee;

@Stateless
public class EmployeeService {
    @PersistenceContext(unitName="EmployeeService")
    protected EntityManager em;

    public Employee createEmployee(String fname, String lname, long salary) {
        Employee emp = new Employee();
        emp.setFirstName(fname);
        emp.setLastName(lname);
        emp.setSalary(salary);        
        em.persist(emp);        
        return emp;
    }
    
    public Employee setEmployeeDepartment(int empId, int deptId) {
        Employee emp = em.find(Employee.class, empId);
        Department dept = em.find(Department.class, deptId);
        dept.addEmployee(emp);
        return emp;
    }

    public List<Employee> findAllEmployees() {
        TypedQuery query = em.createQuery("SELECT e FROM Employee e", Employee.class);
	List<Employee> aList = query.getResultList();
	return aList;
    }
}
