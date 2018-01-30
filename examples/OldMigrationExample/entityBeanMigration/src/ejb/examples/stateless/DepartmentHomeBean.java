package examples.stateless;

import java.util.Collection;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import examples.model.Department;

@Stateless
//@PersistenceContext(name="EmployeeService", unitName="EmployeeService")
public class DepartmentHomeBean implements DepartmentHome {
    @PersistenceContext(unitName="EmployeeService")
	//@PersistenceContext(name="EmployeeService", unitName="EmployeeService")
    private EntityManager em;

    public Department create(int id) throws CreateException {
        Department dept = new Department();
        dept.setId(id);
        try {
            em.persist(dept);
        } catch (PersistenceException e) {
            throw new CreateException(e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new CreateException(e.getMessage());
        }
        return dept;
    }

    public Department findByPrimaryKey(int id) throws FinderException {
        try {
            return em.find(Department.class, id);
        } catch (PersistenceException e) {
            throw new FinderException(e.getMessage());
        }
    }

    public Collection findAll() throws FinderException {
        try {
            return em.createNamedQuery("Department.findAll")
                     .getResultList();
        } catch (PersistenceException e) {
            throw new FinderException(e.getMessage());
        }
    }

    public Collection findByName(String name) throws FinderException {
        try {
            return em.createNamedQuery("Department.findByName")
                     .setParameter(1, name)
                     .getResultList();
        } catch (PersistenceException e) {
            throw new FinderException(e.getMessage());
        }
    }

    public Collection unallocatedEmployees() {
        return em.createNamedQuery("Department.empsWithNoDepartment")
                 .getResultList();
    }

    public void remove (Object pk) throws RemoveException {
        Department d = em.find(Department.class, pk);
        if (d == null) {
            throw new RemoveException("Unable to find entity with pk: " + pk);
        }
        em.remove(d);
    }

    public void remove(Department dept) throws RemoveException {
        Department d = em.find(Department.class, dept.getId());
        if (d == null) {
            throw new RemoveException("Unable to find entity with pk: " + dept.getId());
        }
        em.remove(d);
    }
}

