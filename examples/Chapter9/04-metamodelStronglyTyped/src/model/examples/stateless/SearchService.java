package examples.stateless;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.MapJoin;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import examples.model.Employee;
import examples.model.Phone;


@Stateless
public class SearchService {
    @PersistenceContext(unitName="EmployeeHR")
    EntityManager em;

    public EntityManager getEntityManager() {
        return em;
    }

    public Collection<Object> executeQueryUsingMetamodel() {        
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object> c = cb.createQuery();
        Root<Employee> emp = c.from(Employee.class);
        EntityType<Employee> emp_ = emp.getModel();
        MapJoin<Employee,String,Phone> phone =
            emp.join(emp_.getMap("phones", String.class, Phone.class));
        c.multiselect(emp.get(emp_.getSingularAttribute("name", String.class)),
                              phone.key(), 
                              phone.value());
        TypedQuery<Object> q = em.createQuery(c);
        return q.getResultList();        
    }

    public Metamodel getMetamodel() {        
        return em.getMetamodel();        
    }
}
