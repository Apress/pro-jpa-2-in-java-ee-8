package examples.stateless;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class NamedQueryOrgStructureBean implements OrgStructure {
    @PersistenceContext(unitName="EmployeeService")
    EntityManager em;
    
    public List findEmployeesReportingTo(int managerId) {
        return em.createNamedQuery("orgStructureReportingTo")
                 .setParameter(1, managerId)
                 .getResultList();
    }
    
    public List findAllEmployees() {
        Query query = em.createQuery("SELECT e FROM Employee e");
        return query.getResultList();
    }
}

