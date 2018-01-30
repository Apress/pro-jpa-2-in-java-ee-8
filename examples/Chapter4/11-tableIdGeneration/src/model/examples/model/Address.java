package examples.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

@Entity
public class Address {
    @TableGenerator(name="Address_Gen",
            table="ID_GEN",
            pkColumnName="GEN_NAME",
            valueColumnName="GEN_VAL",
            pkColumnValue="Addr_Gen",
            initialValue=10000,
            allocationSize=100)
    @Id @GeneratedValue(strategy=GenerationType.TABLE,
                        generator="Address_Gen")
    private int id;
    private String street;
    private String city;
    private String state;
    private String zip;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getStreet() {
        return street;
    }
    
    public void setStreet(String address) {
        this.street = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
    public String toString() {
        return "Address id: " + getId() + 
               ", street: " + getStreet() +
               ", city: " + getCity() +
               ", state: " + getState() +
               ", zip: " + getZip();
    }

}
