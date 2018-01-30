package examples.stateless;

import junit.framework.TestCase;
import examples.model.User;
import examples.stateless.UserServiceBean;

public class UserServiceTest3 extends TestCase {
    private static final String USER_ID = "test_id";
    private static final String PASSWORD = "test_password";
    private static final String INVALID_USER_ID = "test_user";
    
    public void setUp() {
        createTestData();
    }
    public void tearDown() {
        if (DatabaseTest.em != null) {
            removeTestData();
        }
    }

    private void createTestData() {
        User user = new User();
        user.setName(USER_ID);
        user.setPassword(PASSWORD);
        DatabaseTest.em.getTransaction().begin();
        DatabaseTest.em.persist(user);
        DatabaseTest.em.getTransaction().commit();        
    }
    
    private void removeTestData() {
        DatabaseTest.em.getTransaction().begin();
        User user = DatabaseTest.em.find(User.class, USER_ID);
        if (user != null) {
            DatabaseTest.em.remove(user);
        }
        DatabaseTest.em.getTransaction().commit();        
    }
    
    public void testAuthenticateValidUser() throws Exception {
        UserServiceBean service = new UserServiceBean();
        service.em = DatabaseTest.em;
        User user = service.authenticate(USER_ID, PASSWORD);
        assertNotNull(user);
        assertEquals(USER_ID, user.getName());
        assertEquals(PASSWORD, user.getPassword());
    }
    
    public void testAuthenticateInvalidUser() throws Exception {
        UserServiceBean service = new UserServiceBean();
        service.em = DatabaseTest.em;
        User user = service.authenticate(INVALID_USER_ID, PASSWORD);
        assertNull(user);
    }
}