package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConnection {

	public static Connection getConnection() {

		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql:///db1?user=root&password=root");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
