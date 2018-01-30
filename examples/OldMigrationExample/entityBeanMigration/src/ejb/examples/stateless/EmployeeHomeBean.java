package examples.stateless;

import java.util.Collection;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import examples.model.Employee;

@Stateless
//@PersistenceContext(name="EmployeeService", unitName="EmployeeService")
public class EmployeeHomeBean implements EmployeeHome {
    //@PersistenceContext(unitName="EmployeeService")
	@PersistenceContext(name="EmployeeService", unitName="EmployeeService")
    private EntityManager em;

    public Employee create(int id) throws CreateException {
        Employee emp = new Employee();
        emp.setId(id);
        try {
            em.persist(emp);
        } catch (PersistenceException e) {
            throw new CreateException(e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new CreateException(e.getMessage());
        }
        return emp;
    }

    public Employee findByPrimaryKey(int id) throws FinderException {
        try {
            return em.find(Employee.class, id);
        } catch (PersistenceException e) {
            throw new FinderException(e.getMessage());
        }
    }

    public Collection findAll() throws FinderException {
        try {
            return em.createNamedQuery("Employee.findAll")
                     .getResultList();
        } catch (PersistenceException e) {
            throw new FinderException(e.getMessage());
        }
    }

    public Collection getManagerStats() throws FinderException {
        try {
            return em.createNamedQuery("Employee.findManagerStats")
                     .getResultList();
        } catch (PersistenceException e) {
            throw new FinderException(e.getMessage());
        }
    }
    
    public void remove (Object pk) throws RemoveException {
        Employee e = em.find(Employee.class, pk);
        if (e == null) {
            throw new RemoveException("Unable to find entity with pk: " + pk);
        }
        em.remove(e);
    }

    public void remove(Employee emp) throws RemoveException {
        Employee e = em.find(Employee.class, emp.getId());
        if (e == null) {
            throw new RemoveException("Unable to find entity with pk: " + emp.getId());
        }
        em.remove(e);
    }
}

