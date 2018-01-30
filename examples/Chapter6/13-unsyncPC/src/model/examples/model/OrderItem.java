package examples.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class OrderItem {

    @Id
    long id;
    String name;
    int quantity;
    
    @ManyToOne
    CustomerOrder order;
    
    public OrderItem() { }
    public OrderItem(String name) { 
        this.name = name;
    }
    public long getId() { return this.id; }
    public void setId(long id) { this.id = id; }
    
    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }
    
    public int getQuantity() { return this.quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    
    public CustomerOrder getOrder() { return order; }
    public void setOrder(CustomerOrder order) { this.order = order; }

}
