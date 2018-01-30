package examples.stateless;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import examples.model.Department;

@Stateless
public class DepartmentServiceBean implements DepartmentService {
    @PersistenceContext(unitName="EmployeeService")
    protected EntityManager em;

    public Department createDepartment(String name) {
        Department dept = new Department();
        dept.setName(name);
        em.persist(dept);
        
        return dept;
    }

    public Collection<Department> findAllDepartments() {
        Query query = em.createQuery("SELECT d FROM Department d");
        return (Collection<Department>) query.getResultList();
    }
}
