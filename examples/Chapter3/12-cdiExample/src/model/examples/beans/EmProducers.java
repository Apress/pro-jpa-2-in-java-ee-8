package examples.beans;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;

@RequestScoped
public class EmProducers {
    @Produces @EmployeeEM
    @PersistenceContext(unitName="Employee")
    private EntityManager em1;
}


