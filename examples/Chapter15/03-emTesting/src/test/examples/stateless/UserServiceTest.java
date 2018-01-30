package examples.stateless;

import junit.framework.TestCase;
import examples.model.User;
import examples.stateless.UserServiceBean;

public class UserServiceTest extends TestCase {
    private static final String USER_ID = "test_id";
    private static final String PASSWORD = "test_password";
    private static final String INVALID_USER_ID = "test_user";
    
    public void testAuthenticateValidUser() throws Exception {
        TestUserService service = new TestUserService();
        User user = service.authenticate(USER_ID, PASSWORD);
        assertNotNull(user);
        assertEquals(USER_ID, user.getName());
        assertEquals(PASSWORD, user.getPassword());
    }
    
    public void testAuthenticateInvalidUser() throws Exception {
        TestUserService service = new TestUserService();
        User user = service.authenticate(INVALID_USER_ID, PASSWORD);
        assertNull(user);
    }

    class TestUserService extends UserServiceBean {
        private User user;
        
        public TestUserService() {
            user = new User();
            user.setName(USER_ID);
            user.setPassword(PASSWORD);
        }

        protected User findUser(String userId) {
            if (userId.equals(user.getName())) {
                return user;
            }
            return null;
        }
    }
}
