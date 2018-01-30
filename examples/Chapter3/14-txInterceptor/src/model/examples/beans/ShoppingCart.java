package examples.beans;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.transaction.Transactional;
import javax.annotation.PostConstruct;

@SessionScoped
@Transactional
public class ShoppingCart implements Serializable {

    private HashMap<String,Integer> items;
    
    @PostConstruct
    void init() {
        items = new HashMap<String,Integer>();
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public void addItem(String item, Integer quantity) {
        Integer orderQuantity = items.get(item);
        if (orderQuantity == null) {
            orderQuantity = 0;
        }
        orderQuantity += quantity;
        items.put(item, orderQuantity);
    }
    
    public Map<String, Integer> getItems() {
        return items;
    }
    
    public void process() {
       // Process the order ...
       init();
    }

    public void cancel() {
        init();
    }
}

