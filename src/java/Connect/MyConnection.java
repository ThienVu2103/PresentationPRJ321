package Connect;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author ldtvu
 */
public class MyConnection {

    public static Connection openConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection c = DriverManager.getConnection("jdbc:sqlserver://localhost:1433; databasename=hospital; username=sa;"
                    + "password=ledinhthienvu");
            return c;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
