package examples.stateless;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import examples.model.Project;

@Stateless
public class ProjectServiceBean implements ProjectService {
    @PersistenceContext(unitName="EmployeeService")
    protected EntityManager em;

    public Project createProject(String name) {
        Project proj = new Project();
        proj.setName(name);
        em.persist(proj);
        
        return proj;
    }

    public Collection<Project> findAllProjects() {
        Query query = em.createQuery("SELECT p FROM Project p");
        return (Collection<Project>) query.getResultList();
    }
}
