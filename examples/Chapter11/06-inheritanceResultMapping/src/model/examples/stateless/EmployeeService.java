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
    
    public List findAllEmployees() {
        Query query = em.createNativeQuery(
                "SELECT id, name, start_date, daily_rate, term, vacation, " +
                "hourly_rate, salary, pension, type " +
                "FROM employee_stage",
                "EmployeeStageMapping");
        return query.getResultList();
    }
}
