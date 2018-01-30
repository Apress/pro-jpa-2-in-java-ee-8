package examples.stateless;

import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import examples.model.Department;
import examples.model.Employee;
import examples.model.PhoneType;

@Stateless
public class EmployeeService {
    @PersistenceContext(unitName="EmployeeService")
    protected EntityManager em;

    public Employee createEmployee(String name, long salary) {
        Employee emp = new Employee();
        emp.setName(name);
        emp.setSalary(salary);        
        Map<PhoneType, String> phoneMap = new HashMap<PhoneType, String>();
        phoneMap.put(PhoneType.Home,"613-289-3214");
        phoneMap.put(PhoneType.Mobile,"613-760-2332");
        emp.setPhoneNumbers(phoneMap);
        em.persist(emp);        
        return emp;
    }
    
    public Employee setEmployeeDepartment(int empId, int deptId) {
        Employee emp = em.find(Employee.class, empId);
        Department dept = em.find(Department.class, deptId);
        dept.addEmployee(emp);
        emp.setDepartment(dept);
		// trigger indirection
	    emp.getPhoneNumbers().size();
        return emp;
    }

    public List<Employee> findAllEmployees() {
        TypedQuery query = em.createQuery("SELECT e FROM Employee e", Employee.class);
		List<Employee> aList = query.getResultList();
		// trigger indirection
		for(Employee anEmployee : aList) {
			anEmployee.getPhoneNumbers().size();
		}
		return aList;
    }
}
