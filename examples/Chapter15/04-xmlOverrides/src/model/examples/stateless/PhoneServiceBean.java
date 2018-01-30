package examples.stateless;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import examples.model.Phone;

@Stateless
public class PhoneServiceBean implements PhoneService {
    @PersistenceContext
    EntityManager em;

    public Phone createPhone(Phone phone) {
        em.persist(phone);
        return phone;
    }
    
    protected Phone findUser(int id) {
        return em.find(Phone.class, id);
    }
}


