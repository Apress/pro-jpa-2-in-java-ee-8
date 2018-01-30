package examples.stateful;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import examples.model.Employee;

@Stateful
public class FeeManagement {
    static final Float UNIFORM_COST = 4.7f;

    @PersistenceContext(unitName="EmployeeService",
                        type=PersistenceContextType.EXTENDED)
    protected EntityManager em;

    public void calculateCleaningCost(int id) {
        Employee emp = em.find(Employee.class, id);
        Float cost = emp.getUniforms().size() * UNIFORM_COST;
        emp.setCost(emp.getCost() + cost); // NOTE: this isn't entirely accurate but it
                                           // it forces an update to demonstrate the lock
    }
}

