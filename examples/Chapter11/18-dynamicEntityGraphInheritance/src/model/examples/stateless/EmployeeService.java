package examples.stateless;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityGraph;
import javax.persistence.Subgraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import examples.model.*;

@Stateless
public class EmployeeService {
    @PersistenceContext(unitName="EmployeeService")
    protected EntityManager em;
    
    public List<Employee> findAllEmployees() {
        TypedQuery<Employee> query = em.createQuery(
        	"SELECT e FROM Employee e",
        	Employee.class);
        query.setHint("javax.persistence.fetchgraph", constructEntityGraph());
        return query.getResultList();
    }
    
    public EntityGraph<Employee> constructEntityGraph() {
        EntityGraph<Employee> graph = em.createEntityGraph(Employee.class);
        graph.addAttributeNodes("name","salary", "address");
        Subgraph<Project> project = graph.addSubgraph("projects", Project.class);
        project.addAttributeNodes("name");
        Subgraph<QualityProject> qaProject = graph.addSubgraph("projects", QualityProject.class);
        qaProject.addAttributeNodes("qaRating");
        return graph;
    }
}
