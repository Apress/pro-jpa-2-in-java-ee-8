package examples.stateless;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import examples.model.Department;
import examples.model.Employee;

@Stateful
public class DepartmentManager {
    @PersistenceUnit(unitName="EmployeeService")
    private EntityManagerFactory emf;
    private EntityManager em;
    private Department dept;
    
    public void init(int deptId) {
        em = emf.createEntityManager();
        dept = em.find(Department.class, deptId);
    }
    
    public Department getDepartment() {
        return dept;
    }

    public void setName(String name) {
        em.joinTransaction();
        dept.setName(name);
    }
    
    public String getName() {
        return dept.getName();
    }
    
    public void addEmployee(int empId) {
        em.joinTransaction();
        Employee emp = em.find(Employee.class, empId);
        dept.getEmployees().add(emp);
        emp.setDepartment(dept);
    }

    @Remove
    public void finished() {
        em.close();
    }
}

