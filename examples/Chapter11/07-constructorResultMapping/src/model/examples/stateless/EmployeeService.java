package examples.stateless;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class EmployeeService {
    @PersistenceContext(unitName="EmployeeService")
    protected EntityManager em;
    
    public List findAllEmployeeDetails() {
        Query query = em.createNativeQuery(
        	"SELECT e.name, e.salary, d.name AS deptName " +
                "FROM emp e, dept d " +
                "WHERE e.dept_id = d.id",
                "EmployeeDetailMapping");
        return query.getResultList();
    }
}
