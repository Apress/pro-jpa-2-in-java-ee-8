package examples.stateless;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import examples.model.Phone;

@Stateless
public class PhoneServiceBean implements PhoneService {
    @PersistenceContext(unitName="EmployeeService")
    protected EntityManager em;

    public Phone createPhone(String num, String type) {
        Phone phone = new Phone();
        phone.setNumber(num);
        phone.setType(type);
        em.persist(phone);
        
        return phone;
    }

    public Collection<Phone> findAllPhones() {
        Query query = em.createQuery("SELECT p FROM Phone p");
        return (Collection<Phone>) query.getResultList();
    }
}
