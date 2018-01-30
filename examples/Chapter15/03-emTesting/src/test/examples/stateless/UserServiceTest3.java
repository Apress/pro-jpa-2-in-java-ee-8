package examples.stateless;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import junit.framework.TestCase;
import examples.model.User;
import examples.stateless.UserServiceBean;

public class UserServiceTest3 extends TestCase {
    private static final String USER_ID = "test_id";
    private static final String PASSWORD = "test_password";
    private static final String INVALID_USER_ID = "test_user";
    
    private EntityManager em;
    private EntityManagerFactory emf;
    
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("hr");
        em = emf.createEntityManager();
        createTestData();
    }
    
    public void tearDown() {
        if (em != null) {
            removeTestData();
            em.close();
        }
        if (emf != null) {
            emf.close();
        }
    }
    
    private void createTestData() {
        User user = new User();
        user.setName(USER_ID);
        user.setPassword(PASSWORD);
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();        
    }
    
    private void removeTestData() {
        em.getTransaction().begin();
        User user = em.find(User.class, USER_ID);
        if (user != null) {
            em.remove(user);
        }
        em.getTransaction().commit();        
    }
    
    public void testAuthenticateValidUser() throws Exception {
        UserServiceBean service = new UserServiceBean();
        service.em = em;
        User user = service.authenticate(USER_ID, PASSWORD);
        assertNotNull(user);
        assertEquals(USER_ID, user.getName());
        assertEquals(PASSWORD, user.getPassword());
    }
    
    public void testAuthenticateInvalidUser() throws Exception {
        UserServiceBean service = new UserServiceBean();
        service.em = em;
        User user = service.authenticate(INVALID_USER_ID, PASSWORD);
        assertNull(user);
    }
}