package examples.stateless;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.annotation.PostConstruct;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.persistence.NoResultException;

import examples.model.Employee;

@Stateless
public class QueryService {
    private static final String QUERY =
        "SELECT e.salary " +
        "FROM Employee e " +
        "WHERE e.department.name = :deptName AND " +
        " e.name = :empName ";
        
    private static final String QUERY2 =
        "SELECT e FROM Employee e WHERE e.name = :name";

    private static final String QUERY3 =
        "SELECT e FROM Employee e WHERE e.id = :id";
        
    private static final String QUERY4 = 
        "SELECT e FROM Employee e";
    
    @PersistenceContext(unitName="QueriesUnit")
    EntityManager em;
    
    @PersistenceUnit(unitName="QueriesUnit")
    EntityManagerFactory emf;
    
    @PostConstruct
    public void init() {
        TypedQuery<Long> q = em.createQuery(QUERY, Long.class);
        emf.addNamedQuery("Employee.findSalaryForNameAndDepartment", q);
        
        TypedQuery<Employee> q2 = em.createQuery(QUERY2, Employee.class);
        emf.addNamedQuery("Employee.findByName", q2);
        
        TypedQuery<Employee> q3 = em.createQuery(QUERY3, Employee.class);
        emf.addNamedQuery("Employee.findByPrimaryKey", q3);

        TypedQuery<Employee> q4 = em.createQuery(QUERY4, Employee.class);
        emf.addNamedQuery("Employee.findAll", q4);
    }
 
    public Long queryEmpSalary(String deptName, String empName) {
        try {
            return em.createNamedQuery("Employee.findSalaryForNameAndDepartment", Long.class)
                 .setParameter("deptName", deptName)
                 .setParameter("empName", empName)
                 .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
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

    public Collection<Employee> findAllEmployees() {
        return em.createNamedQuery("Employee.findAll", Employee.class)
                 .getResultList();
    }
}
