import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/reservation_db",
                    "root",
                    "root"   // change if your password is different
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
