package examples.stateless;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestSuite;

public class DatabaseTest {
    public static EntityManagerFactory emf;
    public static EntityManager em;
    
    public static Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(UserServiceTest3.class);
        
        TestSetup wrapper = new TestSetup(suite) {

            protected void setUp() throws Exception {
                emf = Persistence.createEntityManagerFactory("hr");
                em = emf.createEntityManager();
            }

            protected void tearDown() throws Exception {
                if (em != null) {
                    em.close();
                }
                if (emf != null) {
                    emf.close();
                }
            }
        };
        
        return wrapper;
    }
}
