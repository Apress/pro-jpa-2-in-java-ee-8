package examples.stateless;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import examples.model.Employee;

@Stateless
public class QueryService {
    private static final String QUERY =
        "SELECT e.salary " +
        "FROM Employee e " +
        "WHERE e.department.name = :deptName AND " +
        "      e.name = :empName ";

    @PersistenceContext(unitName="DynamicQueries")
    EntityManager em;

    public long queryEmpSalary(String deptName, String empName) {
        String query = "SELECT e.salary " +
                       "FROM Employee e " +
                       "WHERE e.department.name = '" + deptName + "' AND " +
                       "      e.name = '" + empName + "'";
        try {
            return em.createQuery(query, Long.class).getSingleResult();
        } catch (NoResultException e) {
            return 0;
        }
    }
    
    public long queryEmpSalaryUsingParams(String deptName, String empName) {
        try {
            return em.createQuery(QUERY, Long.class)
                     .setParameter("deptName", deptName)
                     .setParameter("empName", empName)
                     .getSingleResult();
        } catch (NoResultException e) {
            return 0;
        }
    }


    public List<Employee> findAllEmployees() {
        return em.createQuery("SELECT e FROM Employee e", Employee.class)
                 .getResultList();
    }
}
