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
