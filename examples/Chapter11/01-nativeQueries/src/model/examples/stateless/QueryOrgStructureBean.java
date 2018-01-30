package examples.stateless;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import examples.model.Employee;

@Stateless
public class QueryOrgStructureBean implements OrgStructure {
//  Oracle-specific SQL string
//  private static final String ORG_QUERY =
//      "SELECT emp_id, name, salary " +
//      "FROM emp " +
//      "START WITH manager_id = ? " +
//      "CONNECT BY PRIOR emp_id = manager_id";
  private static final String ORG_QUERY =
      "SELECT emp1.emp_id, emp1.name, emp1.salary, emp1.manager_id, " +
      "emp1.dept_id, emp1.address_id " +
      "FROM EMP emp1, EMP emp2 " +
      "WHERE ((emp2.EMP_ID = ?) AND (emp2.EMP_ID = emp1.MANAGER_ID))";
  
    @PersistenceContext(unitName="EmployeeService")
    EntityManager em;
    
    public List findEmployeesReportingTo(int managerId) {
        return em.createNativeQuery(ORG_QUERY, Employee.class)
                 .setParameter(1, managerId)
                 .getResultList();
    }
    
    public List findAllEmployees() {
        Query query = em.createQuery("SELECT e FROM Employee e");
        return query.getResultList();
    }
}

