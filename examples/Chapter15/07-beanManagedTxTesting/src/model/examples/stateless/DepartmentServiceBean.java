package examples.stateless;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import examples.model.Department;
import examples.model.Employee;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class DepartmentServiceBean implements DepartmentService {
    private static final String QUERY = 
        "SELECT e " +
        "FROM Employee e " +
        "WHERE e.department = ?1 ORDER BY e.name";
    
    @PersistenceContext(unitName="hr")
    EntityManager em;

    @Resource 
    UserTransaction tx;
    
    public List assignEmployeeToDepartment(int deptId, int empId) {
        try {
            tx.begin();
            Department dept = em.find(Department.class, deptId);
            Employee emp = em.find(Employee.class, empId);
            dept.getEmployees().add(emp);
            emp.setDepartment(dept);
            tx.commit();
            return em.createQuery(QUERY)
                     .setParameter(1, dept)
                     .getResultList();
        } catch (Exception e) {
            // handle transaction exceptions
            throw new EJBException(e);
        }
    }
}
