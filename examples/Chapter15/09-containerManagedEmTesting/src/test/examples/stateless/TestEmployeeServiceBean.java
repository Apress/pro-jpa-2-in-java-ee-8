package examples.stateless;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import junit.framework.TestCase;
import examples.model.Employee;
import examples.stateless.AuditServiceBean;
import examples.stateless.EmployeeServiceBean;

public class TestEmployeeServiceBean extends TestCase {
    
    private EntityManager em;
    private EntityManagerFactory emf;
    
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("hr");
        em = emf.createEntityManager();
    }

    public void testCreateEmployee() throws Exception {
        EmployeeServiceBean bean = new EmployeeServiceBean();
        AuditServiceBean auditBean = new AuditServiceBean();
        bean.setEntityManager(em);
        bean.setAuditService(auditBean);
        auditBean.setEntityManager(em);
        Employee emp = new Employee();
        emp.setId(99);
        emp.setName("Wayne");
        bean.createEmployee(emp);
        emp = em.find(Employee.class, 99);
        assertNotNull(emp);
        assertEquals(99, emp.getId());
        assertEquals("Wayne", emp.getName());
    }
}