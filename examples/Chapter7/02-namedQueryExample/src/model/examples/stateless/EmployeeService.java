package examples.stateless;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import examples.model.Employee;

@Stateless
public class EmployeeService {
    @PersistenceContext(unitName="NamedQueries")
    EntityManager em;

    public Employee findEmployeeByName(String name) {
        try {
            return em.createNamedQuery("Employee.findByName", Employee.class)
                      .setParameter("name", name)
                      .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
   }

    public Employee findEmployeeByPrimaryKey(int id) {
        try {
            return em.createNamedQuery("Employee.findByPrimaryKey", Employee.class)
                     .setParameter("id", id)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public long findSalaryForNameAndDepartment(String deptName, String empName) {
        try {
            return em.createNamedQuery("findSalaryForNameAndDepartment", Long.class)
                     .setParameter("deptName", deptName)
                     .setParameter("empName", empName)
                     .getSingleResult();
        } catch (NoResultException e) {
            return 0;
        }
    }
    
    public List<Employee> findAllEmployees() {
        return em.createNamedQuery("Employee.findAll", Employee.class)
                 .getResultList();
    }
}
