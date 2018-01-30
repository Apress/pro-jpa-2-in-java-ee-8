package examples.stateless;

import java.io.PrintWriter;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.AttributeNode;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import examples.model.*;

@Stateless
public class EmployeeService {
    @PersistenceContext(unitName="EmployeeService")
    protected EntityManager em;
    
    public void addEntityGraph(PrintWriter out) {
        EntityGraph<?> graph = em.createEntityGraph("Employee.graph2");
        graph.addAttributeNodes("projects");
        em.getEntityManagerFactory().addNamedEntityGraph("Employee.newGraph", graph);
        out.println("Added EntityGraph Employee.newGraph to Employee <br/>");
    }

    public void printAllEntityGraphs(PrintWriter out) {
        out.println("EntityGraphs for ContractEmployee: <br/>");
        List<EntityGraph<? super ContractEmployee>> egList = em.getEntityGraphs(ContractEmployee.class);
        for (EntityGraph<? super ContractEmployee> graph : egList) {
            out.println("EntityGraph: " + graph.getName() + "<br/>");
            List<AttributeNode<?>> attribs = graph.getAttributeNodes();
            for (AttributeNode<?> attr : attribs) {
                out.println(">>> Attribute: " + attr.getAttributeName() + "<br/>");
            }
        }
    }
}
