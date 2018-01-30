package examples.stateless;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import examples.model.Employee;

@Stateless
public class JdbcOrgStructureBean implements OrgStructure {
// Oracle-specific SQL string
//    private static final String ORG_QUERY =
//        "SELECT emp_id, name, salary " +
//        "FROM emp " +
//        "START WITH manager_id = ? " +
//        "CONNECT BY PRIOR emp_id = manager_id";
    private static final String ORG_QUERY =
        "SELECT emp1.emp_id, emp1.name, emp1.salary, emp1.manager_id, " +
        "emp1.dept_id, emp1.address_id " +
        "FROM EMP emp1, EMP emp2 " +
        "WHERE ((emp2.EMP_ID = ?) AND (emp2.EMP_ID = emp1.MANAGER_ID))";

    @Resource(mappedName="jdbc/nativeQueries")
    DataSource hrDs;
    
    public List findEmployeesReportingTo(int managerId) {
        Connection conn = null;
        PreparedStatement sth = null;
        try {
            conn = hrDs.getConnection();
            sth = conn.prepareStatement(ORG_QUERY);
            sth.setLong(1, managerId);
            ResultSet rs = sth.executeQuery();
            
            ArrayList<Employee> result = new ArrayList<Employee>();
            while (rs.next()) {
                Employee emp = new Employee();
                emp.setId(rs.getInt(1));
                emp.setName(rs.getString(2));
                emp.setSalary(rs.getLong(3));
                result.add(emp);
            }
            return result;
        } catch (SQLException e) {
            throw new EJBException(e);
        }
    }
    
    public List findAllEmployees() {
        throw new EJBException("Not implemented!");
    }
 }
