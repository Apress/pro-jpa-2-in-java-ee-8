package examples.stateless;

import java.util.List;
import java.io.PrintWriter;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import examples.model.Employee;
import examples.model.Department;

@Stateless
public class EmployeeService {
    @PersistenceContext(unitName="EmployeeService")
    EntityManager em;

    public List<Department> findAllDepartments() {
        return em.createQuery("SELECT d FROM Department d", Department.class)
                 .getResultList();
    }

    public List<Employee> findAllEmployees() {
        return em.createQuery("SELECT e FROM Employee e", Employee.class)
                 .getResultList();
    }

    public void printRawDepartmentData(PrintWriter out) {
        List<Object[]> result = em.createNativeQuery("SELECT id, name FROM Department d")
                                  .getResultList();
        for (Object[] row : result) {
            out.println("ID=" + row[0] + ", NAME=" + row[1] + "<br/>");
        }
    }

    public void printRawEmployeeData(PrintWriter out) {
        List<Object[]> result = em.createNativeQuery("SELECT id, firstname, lastname, fulltime, bonded, datebonded, homepage FROM Employee e")
                                  .getResultList();
        for (Object[] row : result) {
            out.println("ID=" + row[0] + ", FIRSTNAME=" + row[1] + ", LASTNAME=" + row[2] +
            	    ", FULLTIME=" + row[3] + ", BONDED=" + row[4] + ", DATEBONDED=" + row[5] + 
            	    ", HOMEPAGE=" + row[6] + "<br/>");
        }
    }

}
