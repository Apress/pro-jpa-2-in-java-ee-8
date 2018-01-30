package examples.model;

import javax.persistence.Entity;
import javax.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("QP")
public class QualityProject extends Project {
    private int qa_rating; 
    
    public int getQaRating() { return qa_rating; }
    public void setQaRating(int rating) { this.qa_rating = rating; }    

    public String toString() {
        return super.toString() + ", rating: " + qa_rating;
    }

}
