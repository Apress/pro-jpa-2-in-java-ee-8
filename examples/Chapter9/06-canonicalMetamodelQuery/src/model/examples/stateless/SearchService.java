package examples.stateless;

import java.util.List;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Subquery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import examples.model.*;

@Stateless
public class SearchService {
    @PersistenceContext(unitName="EmployeeHR")
    EntityManager em;

    public EntityManager getEntityManager() {
        return em;
    }

    public List<Employee> getEmployeesUsingJpqlQuery() {

        String qString = 
            "SELECT e FROM Employee e WHERE e.dept.id IN " +
                "(SELECT DISTINCT d.id FROM Department d JOIN d.employees emp JOIN emp.projects empProj " +
                " WHERE empProj.name LIKE \"QA%\")";

        TypedQuery<Employee> q = em.createQuery(qString, Employee.class);
        return q.getResultList();
    }

    public List<Employee> getEmployeesUsingStringBasedQuery() {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> c = cb.createQuery(Employee.class);
        Root<Employee> emp = c.from(Employee.class);
        Subquery<Integer> sq = c.subquery(Integer.class);
        Root<Department> dept = sq.from(Department.class);
        Join<Employee,Project> project = 
            dept.join("employees").join("projects");
        sq.select(dept.<Integer>get("id"))
          .distinct(true)
          .where(cb.like(project.<String>get("name"), "QA%"));
        c.select(emp)
         .where(cb.in(emp.get("dept").get("id")).value(sq));
        
        TypedQuery<Employee> q = em.createQuery(c);
        return q.getResultList();
    }

    public List<Employee> getEmployeesUsingCanonicalMetamodelQuery() {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> c = cb.createQuery(Employee.class);
        Root<Employee> emp = c.from(Employee.class);
        Subquery<Integer> sq = c.subquery(Integer.class);
        Root<Department> dept = sq.from(Department.class);
        Join<Employee,Project> project = 
            dept.join(Department_.employees).join(Employee_.projects);
        sq.select(dept.get(Department_.id))
          .distinct(true)
          .where(cb.like(project.get(Project_.name), "QA%"));
        c.select(emp)
         .where(cb.in(emp.get(Employee_.dept).get(Department_.id)).value(sq));
        
        TypedQuery<Employee> q = em.createQuery(c);
        return q.getResultList();
    }
}
