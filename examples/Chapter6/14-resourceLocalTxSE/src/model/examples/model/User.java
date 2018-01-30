package examples.model;

import java.text.DateFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="USER_DB")
public class User {
    @Id
    private String name;
    private String password;
    @Temporal(TemporalType.DATE)
    private java.util.Calendar lastChange;
    
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
    
    public java.util.Calendar getLastChange() {
        return lastChange;
    }

    public void setLastChange(java.util.Calendar lastChange) {
        this.lastChange = lastChange;
    }

    public String toString() {
        return "User " + getName() +
               " pwd: " + getPassword() +
               " lastChange: " + DateFormat.getDateInstance().format(getLastChange().getTime());
    }
}
