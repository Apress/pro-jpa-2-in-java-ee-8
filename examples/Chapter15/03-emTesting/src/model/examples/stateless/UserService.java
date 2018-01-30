package examples.stateless;

import examples.model.User;

public interface UserService {
    public User authenticate(String userId, String password);
}
