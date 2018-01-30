package examples.stateless;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import junit.framework.TestCase;
import examples.model.Phone;
import examples.stateless.PhoneServiceBean;

public class PhoneServiceTest extends TestCase {
    
    private EntityManager em;
    private EntityManagerFactory emf;
    
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("hr");
        em = emf.createEntityManager();
    }
    
    public void tearDown() {
        if (em != null) {
            em.close();
        }
        if (emf != null) {
            emf.close();
        }
    }
    
    public void testCreatePhone() throws Exception {
        PhoneServiceBean bean = new PhoneServiceBean();
        bean.em = em;
        Phone phone = new Phone();
        phone.setNumber("555-1234");
        phone.setType("Home");
        em.getTransaction().begin();
        bean.createPhone(phone);
        em.getTransaction().commit();
        phone = em.find(Phone.class, 1);
        assertNotNull(phone);
        assertEquals("555-1234", phone.getNumber());
        assertEquals("Home", phone.getType());
    }

}