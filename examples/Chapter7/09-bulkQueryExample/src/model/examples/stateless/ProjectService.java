package examples.stateless;

import static javax.ejb.TransactionAttributeType.REQUIRES_NEW;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import examples.model.Project;

@Stateless
public class ProjectService {
    @PersistenceContext(unitName="BulkQueries")
    EntityManager em;

    @TransactionAttribute(REQUIRES_NEW)
    public void removeEmptyProjects() {
         em.createQuery("DELETE FROM Project p " +
                        "WHERE p.employees IS EMPTY ")
           .executeUpdate();
    }

    public List<Project> findAllProjects() {
        return em.createQuery("SELECT p FROM Project p", Project.class)
                 .getResultList();
    }
}
