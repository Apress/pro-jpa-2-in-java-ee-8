package examples.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Phone {
    @Id
    String num;
        
    String type;
    
    public String getNum() {
        return num;
    }
    public void setNum(String num) {
        this.num = num;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String toString() {
        return "Phone num: " + num + " type: " + type;
    }
    
}