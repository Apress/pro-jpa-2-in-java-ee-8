package examples.stateful;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.EJBException;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import examples.model.Order;

@Stateful
@DataSourceDefinition(name="java:app/env/jdbc/MyDS",
    className="org.apache.derby.jdbc.ClientXADataSource",
    portNumber=1527,
    serverName="localhost",
    databaseName="sfsbLifecycleExample;create=true",
    user="APP",
    password="APP")	
public class OrderBrowser {

    @Resource(lookup="java:app/env/jdbc/MyDS")
    DataSource ds;

    Connection conn;

    @PostConstruct
    void init() {
        acquireConnection();
    }
    
    @PrePassivate
    void passivate() {
        releaseConnection();
    }

    @PostActivate
    void activate() {
        acquireConnection();
    }

    @PreDestroy
    void shutdown() {
        releaseConnection();
    }
    
    void acquireConnection() {
        try {
            conn = ds.getConnection();
        } catch (SQLException e) {
            throw new EJBException(e);
        }
    }

    void releaseConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
        }
        conn = null;
    }

    public Collection<Order> listOrders() {
        // ... Issue a query through the connection...
        return new ArrayList<Order>();
    }
    
    @Remove
    public void remove() {}
}

