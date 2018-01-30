package examples.stateless;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import examples.model.*;

@Stateless
public class EmployeeService {
    @PersistenceContext(unitName="EmployeeService")
    protected EntityManager em;
    
    public List<Address> findAllAddresses() {
        TypedQuery<Address> query = em.createQuery(
        	"SELECT a FROM Address a",
        	Address.class);
        query.setHint("javax.persistence.fetchgraph", constructEntityGraph());
        return query.getResultList();
    }

    public EntityGraph<Address> constructEntityGraph() {
        EntityGraph<Address> graph = em.createEntityGraph(Address.class);
        graph.addAttributeNodes("street","city", "state", "zip");
        return graph;
    }
}
