package examples.stateless;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryBean {
    String unitName;
    EntityManagerFactory emf;

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
    
    public EntityManager createEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory(unitName);
        }
        return emf.createEntityManager();
    }
    
    public void destroy() {
        if (emf != null) {
            emf.close();
        }
    }
}
