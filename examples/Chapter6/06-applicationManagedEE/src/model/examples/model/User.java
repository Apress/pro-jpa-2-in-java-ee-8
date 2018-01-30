package examples.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USER_DB")
public class User {
    @Id
    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String name) {
        this.password = name;
    }
    
    public String toString() {
        return "User " + getName() +
               " pwd: " + getPassword();
    }
}
