package billtracker.login;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class My_CNX {
	private static String servername = "localhost";
	private static String username = "root";
	private static String dbname = "users_db";
	private static Integer portnumber = 3306;
	private static String password = "";
	
	
	public static Connection getConnection() {
		Connection cnx = null;
		
		MysqlDataSource datasource = new MysqlDataSource();
		datasource.setServerName(servername);
		datasource.setUser(username);
		datasource.setPassword(password);
		datasource.setDatabaseName(dbname);
		datasource.setPortNumber(portnumber);
		
		try {
			cnx = datasource.getConnection();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			Logger.getLogger(My_CNX.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return cnx;
	}
}
