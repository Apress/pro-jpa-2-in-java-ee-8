package examples.stateless;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import junit.framework.TestCase;
import examples.model.Department;
import examples.model.Employee;
import examples.stateless.DepartmentServiceBean;

public class DepartmentServiceBeanTest extends TestCase {
    
    private EntityManager em;
    private EntityManagerFactory emf;
    
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("hr");
        em = emf.createEntityManager();
        createTestData();
    }

    private void createTestData() {
        Employee emp = new Employee(500, "Scott");
        em.persist(emp);
        emp = new Employee(600, "John");
        em.persist(emp);
        Department dept = new Department(700, "TEST");
        dept.getEmployees().add(emp);
        emp.setDepartment(dept);
        em.persist(dept);
    }
    
    public void testAssignEmployeeToDepartment() throws Exception {
        DepartmentServiceBean bean = new DepartmentServiceBean();
        bean.em = em;
        bean.tx = new EntityUserTransaction(em);
        List result = bean.assignEmployeeToDepartment(700, 500);
        assertEquals(2, result.size());
        assertEquals("John", ((Employee)result.get(0)).getName());
        assertEquals("Scott", ((Employee)result.get(1)).getName());
    }
}