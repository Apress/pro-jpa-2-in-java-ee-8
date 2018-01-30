package examples.stateless;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class OrgStructure {
    @PersistenceContext(unitName="EmployeeService")
    EntityManager em;
    
    
    public List findAllEmployees() {
//      Oracle-specific SQL string
//        Query query = em.createNativeQuery(
//                "SELECT emp_id, name, salary, manager_id, dept_id, address_id " +
//                "FROM emp " +
//                "START WITH manager_id IS NULL " +
//                "CONNECT BY PRIOR emp_id = manager_id",
//                "EmployeeResult");
        Query query = em.createNativeQuery(
                "SELECT emp_id, name, salary, manager_id, dept_id, address_id " +
                "FROM EMP ", 
                "EmployeeResult");
        return query.getResultList();
    }
    
    public List findEmployeeWithAddress() {
        Query query = em.createNativeQuery(
                "SELECT emp_id, name, salary, manager_id, dept_id, address_id, " +
                "id, street, city, state, zip " +
                "FROM emp, address " +
                "WHERE address_id = id",
                "EmployeeWithAddress");
        return query.getResultList();
    }
    
    public List findEmployeeWithAddressColumnAlias() {
        Query query = em.createNativeQuery(
                "SELECT emp.emp_id AS emp_id, name, salary, manager_id, dept_id, address_id, " +
                "address.id, street, city, state, zip " +
                "FROM emp, address " +
                "WHERE address_id = id",
                "EmployeeWithAddressColumnAlias");
        return query.getResultList();
    }
    
    public List findEmployeeWithManager() {
//      Oracle-specific SQL string
//        Query query = em.createNativeQuery(
//                "SELECT e.name AS emp_name, m.name AS manager_name " +
//                "FROM emp e, emp m " +
//                "WHERE e.manager_id = m.emp_id (+) " +
//                "START WITH e.manager_id IS NULL " +
//                "CONNECT BY PRIOR e.emp_id = e.manager_id",
//                "EmployeeWithManager");
//      Derby SQL string (not the equivalent to above)
        Query query = em.createNativeQuery(
                "SELECT e.name AS emp_name, m.name AS manager_name " +
                "FROM emp e, emp m " +
                "WHERE e.manager_id = m.emp_id",
                "EmployeeAndManager");
        return query.getResultList();
    }
    
    public List findDepartmentSummary() {
//      Oracle-specific SQL string
//        Query query = em.createNativeQuery(
//                "SELECT d.id, d.name AS dept_name, " +
//                "e.emp_id, e.name, e.salary, e.manager_id, e.dept_id, e.address_id, " +
//                "s.tot_emp, s.avg_sal " +
//                "FROM dept d, " +
//                "(SELECT * " +
//                "FROM emp e " +
//                "WHERE EXISTS(SELECT 1 FROM emp WHERE manager_id = e.emp_id)) e, " +
//                "(SELECT d.id, COUNT(*) AS tot_emp, AVG(e.salary) AS avg_sal " +
//                "FROM dept d, emp e " +
//                "WHERE d.id = e.dept_id (+) " +
//                "GROUP BY d.id) s " +
//                "WHERE d.id = e.dept_id (+) AND " +
//                "d.id = s.id",
//                "DepartmentSummary");
// Derby can't handle this query, use JP QL for this example.
        Query query = em.createQuery(
                "SELECT d, m, COUNT(e), AVG(e.salary) " + 
                "FROM Department d LEFT JOIN d.employees e " + 
                                  "LEFT JOIN d.employees m " + 
                "WHERE m IN (SELECT de.manager " + 
                            "FROM Employee de " + 
                            "WHERE de.department = d) " + 
                "GROUP BY d, m");
        return query.getResultList();
    }
}

