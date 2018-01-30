package examples.stateless;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import examples.model.Department;
import examples.model.Employee;

@Stateless
public class DepartmentService {
    @PersistenceContext(unitName="EmployeeService")
    protected EntityManager em;

    public Department createDepartment(String name) {
        Department dept = new Department();
        dept.setName(name);
        em.persist(dept);
        
        return dept;
    }

    public Department addEmployeeToDepartment(int deptId, int empId) {
        Department dept = em.find(Department.class, deptId);
        Employee emp = em.find(Employee.class, empId);
        if ((dept != null) && (emp != null)) {
            if (!dept.getEmployees().contains(emp))
                dept.getEmployees().add(emp);
        }
        return dept;
    }

    public List<Department> findAllDepartments() {
        return em.createQuery("SELECT d FROM Department d", Department.class)
                 .getResultList();
    }
}
