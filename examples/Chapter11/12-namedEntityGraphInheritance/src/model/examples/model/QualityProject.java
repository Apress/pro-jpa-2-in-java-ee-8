package examples.model;

import javax.persistence.Entity;
import javax.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("QP")
public class QualityProject extends Project {
    private int qaRating; 
    
    public int getQaRating() { return qaRating; }
    public void setQaRating(int rating) { this.qaRating = rating; }    

    public String toString() {
        return super.toString() + ", rating: " + qaRating;
    }

}
