package examples.stateless;

import java.util.List;

import javax.ejb.Stateless;
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
        query.setHint("javax.persistence.fetchgraph", em.getEntityGraph("Address"));
        return query.getResultList();
    }
}
