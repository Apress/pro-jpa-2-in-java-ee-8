package examples.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
    @NamedQuery(name="findActiveConversations",
                query="SELECT c " +
                      "FROM Conversation c " +
                      "WHERE c.status = 'ACTIVE'"),
    @NamedQuery(name="findLastMessageDate",
                query="SELECT MAX(m.postingDate) " +
                      "FROM Conversation c JOIN c.messages m " +
                      "WHERE c = :conversation")
})

public class Conversation {
    public final static String ACTIVE   = "ACTIVE";
    public final static String INACTIVE = "INACTIVE";
    
    @Id
    private int id;
    private String status;
    
    @OneToMany(mappedBy="conversation")
    private Collection<Message> messages = new ArrayList<Message>();

    public int getId() {
        return id;
    }
    
    public void setId(int empNo) {
        this.id = empNo;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
    public Collection<Message> getMessages() {
        return messages;
    }
    
    public void addMessage(Message m) {
        if (!getMessages().contains(m)) {
            getMessages().add(m);
            if (m.getConversation() != null) {
                m.getConversation().getMessages().remove(m);
            }
            m.setConversation(this);
        }
    }
    
    public String toString() {
        return "Conversation " + getId() + ": status: " + getStatus();
    }
}
