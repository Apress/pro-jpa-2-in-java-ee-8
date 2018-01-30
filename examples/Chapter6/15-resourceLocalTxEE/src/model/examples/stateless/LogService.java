package examples.stateless;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import examples.model.LogRecord;

@Stateless
public class LogService {
    @PersistenceUnit(unitName="logging")
    EntityManagerFactory emf;
    
    public void logAccess(int userId, String action) {
        EntityManager em = emf.createEntityManager();
        try {
            LogRecord lr = new LogRecord(userId, action);
            em.getTransaction().begin();
            em.persist(lr);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}


