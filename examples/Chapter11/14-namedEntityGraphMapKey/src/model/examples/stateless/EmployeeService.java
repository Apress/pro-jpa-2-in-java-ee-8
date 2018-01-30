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
    
    public List<Department> findAllDepartments() {
        TypedQuery<Department> query = em.createQuery(
        	"SELECT d FROM Department d",
        	Department.class);
        query.setHint("javax.persistence.fetchgraph", em.getEntityGraph("Department.graph1"));
        return query.getResultList();
    }
}
