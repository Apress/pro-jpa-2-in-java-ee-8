package examples.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="MESSAGE_LOG")
public class MessageLog {
    @Id
    private int id;
    private String message;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="LOG_DTTM")
    private Date logDateTime;
    
    public int getId() {
        return id;
    }

    public Date getLogDateTime() {
        return logDateTime;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public String toString() {
        return "Message: " + getMessage() + " logged: " + getLogDateTime();
    }
}
