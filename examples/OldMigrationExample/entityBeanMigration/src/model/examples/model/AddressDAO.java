package examples.model;

import javax.persistence.EntityManager;

public class AddressDAO {
    private EntityManager em;
    
    public AddressDAO(EntityManager em) {
        this.em = em;
    }
    
    public void create(AddressTO address) {
        Address entity = createEntity(address);
        em.joinTransaction();
        em.persist(entity);
    }


    public void update(AddressTO address) {
        em.joinTransaction();
        em.merge(createEntity(address));
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
    
    public AddressTO find(int id) {
        Address entity = em.find(Address.class, id);
        if (entity != null) {
            return createTO(entity);
        } else {
            return null;
        }
    }


    private Address createEntity(AddressTO address) {
        Address entity = new Address();
        entity.setId(address.getId());
        entity.setStreet(address.getStreet());
        entity.setCity(address.getCity());
        entity.setState(address.getState());
        entity.setZip(address.getZip());
        return entity;
    }
    
    private AddressTO createTO(Address entity) {
        AddressTO address = new AddressTO();
        address.setId(entity.getId());
        address.setStreet(entity.getStreet());
        address.setCity(entity.getCity());
        address.setState(entity.getState());
        address.setZip(entity.getZip());
        return address;
    }
}


