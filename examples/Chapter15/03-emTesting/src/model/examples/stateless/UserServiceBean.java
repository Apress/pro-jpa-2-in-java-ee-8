package examples.stateless;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import examples.model.User;

@Stateless
public class UserServiceBean implements UserService {
    @PersistenceContext
    EntityManager em;

    public User authenticate(String userId, String password) {
        User user = findUser(userId);
        // ...
        return user;
    }
    
    protected User findUser(String userId) {
        return em.find(User.class, userId);
    }
}


