package examples.stateless;

import javax.persistence.EntityManager;

import junit.framework.TestCase;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import examples.model.Employee;

public class TestEmployeeServiceBean extends TestCase {
    XmlBeanFactory factory;
    
    public void setUp() {
        ClassPathResource resource = 
            new ClassPathResource("test-employee-service-bean.xml");
        factory = new XmlBeanFactory(resource);
    }
    
    public void tearDown() {
        factory.destroySingletons();
    }
    
    public void testCreateEmployee() throws Exception {
        EmployeeService bean = 
            (EmployeeService) factory.getBean("employee-service");
        Employee emp = new Employee();
        emp.setId(99);
        emp.setName("Wayne");
        bean.createEmployee(emp);
        EntityManager em = 
            (EntityManager) factory.getBean("shared-entity-manager");
        emp = em.find(Employee.class, 99);
        assertNotNull(emp);
        assertEquals(99, emp.getId());
        assertEquals("Wayne", emp.getName());
    }
}
