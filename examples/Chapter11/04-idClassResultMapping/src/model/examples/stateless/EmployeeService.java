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
    
    public List findEmployeeWithManager() {
        Query query = em.createNativeQuery(
                "SELECT e.country, e.emp_id, e.name, e.salary, " +
                "e.manager_country, e.manager_id, m.country AS mgr_country, " +
                "m.emp_id AS mgr_id, m.name AS mgr_name, m.salary AS mgr_salary,  " +
                "m.manager_country AS mgr_mgr_country, m.manager_id AS mgr_mgr_id " +
                "FROM   emp e, emp m " +
                "WHERE  e.manager_country = m.country AND " +
                "e.manager_id = m.emp_id ",
                "EmployeeAndManager");
        return query.getResultList();
    }
}
