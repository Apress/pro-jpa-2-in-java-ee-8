package examples.model;

import java.text.DecimalFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity 
public class Stock {
    @Id
    private String ticker;
    private int version;
    private double price;


    public String getTicker() {
        return ticker;
    }
    
    public void setTicker(String ticker) {
        this.ticker = ticker;
    }
    
    public int getVersion() {
        return version;
    }
    
    public void setVersion(int version) {
        this.version = version;
    }
    
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String toString() {
    	DecimalFormat df = new DecimalFormat("###.00");
        return ticker + ":  " + df.format(getPrice());
    }
}
