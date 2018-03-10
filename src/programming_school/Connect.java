package programming_school;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
	Connection connect = connect();
	
	public static Connection connect() {
		Connection connect = null;
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/programming_school?useSSL=false&characterEncoding=utf-8", "root", "mojSQL");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connect;
	}
}