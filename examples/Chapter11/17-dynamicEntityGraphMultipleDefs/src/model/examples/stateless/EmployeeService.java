package examples.stateless;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityGraph;
import javax.persistence.Subgraph;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import examples.model.*;

@Stateless
public class EmployeeService {
    @PersistenceContext(unitName="EmployeeService")
    protected EntityManager em;
    
    public List<Employee> findAllEmployees() {
        TypedQuery<Employee> query = em.createQuery(
        	"SELECT e FROM Employee e",
        	Employee.class);
        query.setHint("javax.persistence.fetchgraph", constructEntityGraph());
        return query.getResultList();
    }
    
    public EntityGraph<Employee> constructEntityGraph() {
        EntityGraph<Employee> graph = em.createEntityGraph(Employee.class);
        graph.addAttributeNodes("name","salary", "address");
        Subgraph<Phone> phone = graph.addSubgraph("phones");
        phone.addAttributeNodes("number", "type");

        Subgraph<Employee> namedEmp = phone.addSubgraph("employee");
        namedEmp.addAttributeNodes("name");

        Subgraph<Department> dept = graph.addSubgraph("department");
        dept.addAttributeNodes("name");

        Subgraph<Employee> mgrNamedEmp = graph.addSubgraph("manager");
        mgrNamedEmp.addAttributeNodes("name");
        return graph;
    }
}
