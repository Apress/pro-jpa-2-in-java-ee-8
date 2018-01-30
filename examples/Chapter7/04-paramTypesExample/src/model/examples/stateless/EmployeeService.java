package examples.stateless;

import java.util.List;
import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

import examples.model.Department;
import examples.model.Employee;

@Stateless
public class EmployeeService {
    @PersistenceContext(unitName="EmployeeService")
    EntityManager em;

    public List<Employee> findEmployeesAboveSal(Department dept, long minSal) {
        return em.createNamedQuery("findEmployeesAboveSal", Employee.class)
                 .setParameter("dept", dept)
                 .setParameter("sal", minSal)
                 .getResultList();
    }

    public List<Employee> findEmployeesHiredDuringPeriod(Date start, Date end) {
        return em.createQuery("SELECT e " +
                              "FROM Employee e " +
                              "WHERE e.startDate BETWEEN :start AND :end", Employee.class)
                 .setParameter("start", start, TemporalType.DATE)
                 .setParameter("end", end, TemporalType.DATE)
                 .getResultList();
    }

    public Employee findHighestPaidByDepartment(Department dept) {
        try {
            return em.createNamedQuery("findHighestPaidByDepartment", Employee.class)
                     .setParameter("dept", dept)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Employee> findAllEmployees() {
        return em.createQuery("SELECT e FROM Employee e", Employee.class)
                 .getResultList();
    }
}
