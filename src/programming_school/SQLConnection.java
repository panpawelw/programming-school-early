package programming_school;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {
	
	Connection connection = null;
	
	public Connection connect() {

		try {
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/programming_school?useSSL=false&characterEncoding=utf-8", "root", "mojSQL");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public void disconnect() {
		
		if( this.connection != null) {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}