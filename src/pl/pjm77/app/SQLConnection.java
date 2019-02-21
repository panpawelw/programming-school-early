package pl.pjm77.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {

	private static Connection connection = null;

	public static Connection connect() throws SQLException{

		connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/programming_school?useSSL=false&characterEncoding=utf-8",
					"root", "mojSQL");
		return connection;
	}

	public static void disconnect() {

		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}