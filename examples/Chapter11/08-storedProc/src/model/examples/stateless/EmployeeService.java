package examples.stateless;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.ParameterMode;

import examples.model.*;

@Stateless
public class EmployeeService {
    @PersistenceContext(unitName="EmployeeService")
    protected EntityManager em;

    public String sayHello() {
        Object r = em.createNativeQuery("call hello(?)")
    	             .setParameter(1, "mike")
    	             .getResultList();
        System.out.println("************************* STORED PROC: execute returned: " + r + " ************************");
        return "Done";
    	    
/*    	    
        StoredProcedureQuery q = em.createStoredProcedureQuery("hello");
        q.registerStoredProcedureParameter("name", String.class, ParameterMode.INOUT);
        q.setParameter("name", "massimo");
        boolean r = q.execute();
        System.out.println("************************* STORED PROC: execute returned: " + r + " ************************");
        String value = (String) q.getOutputParameterValue("name");
        System.out.println("************************* STORED PROC: value returned: " + value + " ************************");
        return value;
*/
    }
    
    public List findAllEmployees() {

    	List result = null;
        StoredProcedureQuery query = em.createStoredProcedureQuery("readEmps", Employee.class);
        query.registerStoredProcedureParameter("p1", Integer.class, ParameterMode.IN);
        query.setParameter("p1", 5);
        boolean r = query.execute();
        System.out.println("************************* STORED PROC: execute returned: " + r + " ************************");
        r = query.hasMoreResults();
        System.out.println("************************* hasMoreResults: " + r + " *************************");
        if (r) {
            result = query.getResultList();
            System.out.println("************************* Results: " + result + " *************************");
        }
        r = query.hasMoreResults();
        System.out.println("************************* hasMoreResults: " + r + " *************************");
        return result;
    }
}
