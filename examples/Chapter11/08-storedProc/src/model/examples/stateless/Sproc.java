package examples.stateless;

import java.util.List;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Sproc {

    public static void hello(String[] name) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:default:connection");
        String result = "Hello " + name[0];
        name[0] = result;
    }

	
    public static void selectEmps(int param, ResultSet[] r1) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:default:connection");
        PreparedStatement ps = conn.prepareStatement("select * from EMPLOYEE e where e.id < ?");
        ps.setInt(1, param);
        r1[0] = ps.executeQuery();

//        PreparedStatement ps2 = conn.prepareStatement("select * from t1 where i >= ?");
//        ps2.setInt(1, p2);
//        data2[0] = ps2.executeQuery();

//        PreparedStatement ps1 = conn.prepareStatement("select * from EMPLOYEE e where e.id < ?");
//        ps1.setInt(1, p1);
//        r1[0] = ps1.executeQuery();

        conn.close();
    }
}
