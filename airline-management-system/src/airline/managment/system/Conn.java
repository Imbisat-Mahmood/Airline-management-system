
package airline.managment.system;



import java.sql.*;

public class Conn {
    public Connection c;
    public Statement s;
    public Connection conn; // FIXED: change Object to Connection

    public Conn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql:///airlinemanagementsystem", "root", "20ROOT-MySql");
            s = c.createStatement();
            conn = c; // assign `c` to `conn` so both work
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
