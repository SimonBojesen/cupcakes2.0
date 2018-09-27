package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DBConnector {
	private static Connection connection = null;
	
	//Constants
	private static final String IP	     = "localhost";
	private static final String PORT     = "3306";
	public static final String DATABASE = "cupcakeshop";
	private static final String USERNAME = "root"; 
	private static final String PASSWORD = "1234";	     
	
	public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                String url = "jdbc:mysql://" + IP + ":" + PORT + "/" + DATABASE;
                connection = (Connection) DriverManager.getConnection(url, USERNAME, PASSWORD);
            } catch (Exception ex) {
                Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return connection;
    }

}