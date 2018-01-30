package examples.stateless;

import java.util.List;

public interface Logger {
    public void logMessage(String message);
    public void clearMessageLog();
    public List findAllMessages();
}
