package examples.stateless;

import junit.framework.TestCase;
import examples.model.User;
import examples.stateless.UserServiceBean;

public class UserServiceTest2 extends TestCase {
    private static final String USER_ID = "test_id";
    private static final String PASSWORD = "test_password";
    private static final String INVALID_USER_ID = "test_user";
    
    public void testAuthenticateValidUser() throws Exception {
        UserServiceBean service = new UserServiceBean();
        service.em = new TestEntityManager(USER_ID, PASSWORD);
        User user = service.authenticate(USER_ID, PASSWORD);
        assertNotNull(user);
        assertEquals(USER_ID, user.getName());
        assertEquals(PASSWORD, user.getPassword());
    }
    
    public void testAuthenticateInvalidUser() throws Exception {
        UserServiceBean service = new UserServiceBean();
        service.em = new TestEntityManager(USER_ID, PASSWORD);
        User user = service.authenticate(INVALID_USER_ID, PASSWORD);
        assertNull(user);
    }
    
    class TestEntityManager extends MockEntityManager {
        private User user;
        
        public TestEntityManager(String user, String password) {
            this.user = new User();
            this.user.setName(user);
            this.user.setPassword(password);
        }
        
        public <T> T find(Class<T> entityClass, Object pk) {
            if (entityClass == User.class && ((String)pk).equals(user.getName())) {
                return (T) user;
            }
            return null;
        }
    }
}

