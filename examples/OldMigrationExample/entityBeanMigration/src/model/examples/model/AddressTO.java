package examples.model;

public class AddressTO implements java.io.Serializable {
    private int id;
    private String street;
    private String city;
    private String state;
    private String zip;

    public AddressTO() {}
    
    public AddressTO(int id, String street, String city, 
                     String state, String zip) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    
    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }
    
    public String getZip() { return zip; }
    public void setZip(String zip) { this.zip = zip; }    
    
    public String toString() {
        return "Address street: " + getStreet() +
               ", city: " + getCity() +
               ", state: " + getState() +
               ", zip: " + getZip();
    }
}
