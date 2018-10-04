package programming_school;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import mindrot.jbcrypt.BCrypt;

public class User {

	private int id;
	private String username;
	private String password;
	private String email;
	private int usergroup_id;

	public User() {}

	public User(String username, String email, String password, int usergroup_id) {
		this.username = username;
		this.email = email;
		this.setPassword(password);
		this.usergroup_id = usergroup_id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return this.id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = BCrypt.hashpw(password, BCrypt.gensalt());
	}

	public int getUsergroup_id() {
		return usergroup_id;
	}

	public void setUsergroup_id(int person_group_id) {
		this.usergroup_id = person_group_id;
	}

	public void saveUserToDB() {
		String dbUrl = "jdbc:mysql://localhost:3306/programming_school?useSSL=false&characterEncoding=utf-8";
		String user = "root";
		String pswd = "mojSQL";
		try (Connection con = DriverManager.getConnection(dbUrl, user, pswd)) {
			if (this.id == 0) {
				String sql = "INSERT INTO users(username, email, password, person_group_id) VALUES (?, ?, ?, ?);";
				String generatedColumns[] = { " ID " };
				try (PreparedStatement ps = con.prepareStatement(sql, generatedColumns)) {
					ps.setString(1, this.username);
					ps.setString(2, this.email);
					ps.setString(3, this.password);
					ps.setInt(4, this.usergroup_id);
					ps.executeUpdate();
					try (ResultSet rs = ps.getGeneratedKeys()) {
						if (rs.next()) {
							this.id = rs.getInt(1);
						}
					}
				}
			} else {
				String sql = "UPDATE user SET username=?, email=?, password=?, usergroup_id=? WHERE id = ?;";
				try (PreparedStatement ps = con.prepareStatement(sql)) {
					ps.setString(1, this.username);
					ps.setString(2, this.email);
					ps.setString(3, this.password);
					ps.setInt(4, this.usergroup_id);
					ps.setInt(5, this.id);
					ps.executeUpdate();
				}
			}
		} catch (MySQLIntegrityConstraintViolationException m) {
			System.out.println("No such user group!");
		} catch (SQLException e) {
			System.out.println("Database error!");
			e.printStackTrace();
		}
	}

	static public User loadUserById(int id) {
		String dbUrl = "jdbc:mysql://localhost:3306/programming_school?useSSL=false&characterEncoding=utf-8";
		String user = "root";
		String pswd = "mojSQL";
		try (Connection con = DriverManager.getConnection(dbUrl, user, pswd)) {
			String sql = "SELECT * FROM user WHERE id=?;";
			try (PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setInt(1, id);
				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						return loadUser(rs);
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("Database error!");
			e.printStackTrace();
		}
		System.out.println("No such user!");
		return null;
	}
	
	static public User[] loadAllUsers() {
		return loadUsersBy(true, 0);
	}
	
	public void deleteUser() {
		String dbUrl = "jdbc:mysql://localhost:3306/programming_school?useSSL=false&characterEncoding=utf-8";
		String user = "root";
		String pswd = "mojSQL";
		try (Connection con = DriverManager.getConnection(dbUrl, user, pswd)) {
			String sql = "DELETE FROM user WHERE id=?";
			try (PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setInt(1, this.id);
				ps.executeUpdate();
				this.id=0;
			}
		} catch (SQLException e) {
			System.out.println("Database error!");
			e.printStackTrace();
		}
	}

	public static User[] loadAllbyGroupId(int usergroup_id) {
		return loadUsersBy(false, usergroup_id);
	}
	
	
	@Override
	public String toString() {
		String userToString = this.id + ": " + this.username + " email: " + this.email + " group: " + this.usergroup_id;
		return userToString;
	}
}