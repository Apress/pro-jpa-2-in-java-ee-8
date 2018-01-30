package examples.model;


import javax.persistence.EntityManager;

public class AddressEntityDAO {
    private EntityManager em;


    public AddressEntityDAO(EntityManager em) {
        this.em = em;
    }


    public void create(Address address) {
        em.joinTransaction();
        em.persist(address);
    }


    public void update(Address address) {
        em.joinTransaction();
        em.merge(address);
    }


    public void remove(int id) {
        em.joinTransaction();
        Address entity = em.find(Address.class, id);
        if (entity != null) {
            em.remove(entity);
        } else {
            throw new RuntimeException("No such address id: " + id);
        }
    }


    public Address find(int id) {
        return em.find(Address.class, id);
    }
}
