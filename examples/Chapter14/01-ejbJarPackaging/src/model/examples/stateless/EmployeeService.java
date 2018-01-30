package examples.stateless;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import examples.model.Employee;

@Stateless
public class EmployeeService {
    @PersistenceContext(unitName="EmployeeService")
    protected EntityManager em;

    public List<Employee> findAllEmployees() {
        return em.createNamedQuery("Employee.findAll", Employee.class)
                 .getResultList();
    }
}
