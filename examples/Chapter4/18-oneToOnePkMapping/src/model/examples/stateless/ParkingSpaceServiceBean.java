package examples.stateless;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import examples.model.Employee;
import examples.model.ParkingSpace;

@Stateless
public class ParkingSpaceServiceBean implements ParkingSpaceService {
    @PersistenceContext(unitName="EmployeeService")
    protected EntityManager em;

    public ParkingSpace createParkingSpace(Employee emp, int lot, String location) {
        ParkingSpace space = new ParkingSpace();
        space.setId(emp.getId());
        space.setLot(lot);
        space.setLocation(location);
        
        emp.setParkingSpace(space);
        space.setEmployee(emp);
        em.persist(space);
        
        return space;
    }

    public Collection<ParkingSpace> findAllParkingSpaces() {
        Query query = em.createQuery("SELECT p FROM ParkingSpace p");
        return (Collection<ParkingSpace>) query.getResultList();
    }
}
