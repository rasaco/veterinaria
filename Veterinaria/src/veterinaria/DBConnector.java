package veterinaria;

/**
 * Prueba de dos pushes a la vez
 * @author Raul
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static Connection conn;
    private static String url = "jdbc:mysql://localhost/veterinaria";
    private static String user = "root";//Username of database  
    private static String pass = "ElCaloret3";//Password of database  

    public static Connection connect() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Error: " + cnfe.getMessage());
        } catch (InstantiationException ie) {
            System.err.println("Error: " + ie.getMessage());
        } catch (IllegalAccessException iae) {
            System.err.println("Error: " + iae.getMessage());
        }
        conn = DriverManager.getConnection(url, user, pass);
        return conn;
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if (conn != null && !conn.isClosed()) {
            return conn;
        }
        connect();
        return conn;
    }
}
