package examples.stateless;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import java.util.logging.Logger;

@Stateless
public class LoggerBean {
    private Logger logger;

    @PostConstruct
    private void init() {
        logger = Logger.getLogger("notification");
    }

    public void logMessage(String message) {
        logger.info(message);
    }
}

