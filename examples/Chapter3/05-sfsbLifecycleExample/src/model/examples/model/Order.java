package examples.model;

public class Order {
    private int id;
    private String name;
    private int paymentId;

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }
    
    public String toString() {
        return "Order id: " + getId() + " name: " + getName() + " paymentId: " + getPaymentId();
    }
}
