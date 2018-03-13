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
	private int person_group_id;

	public User() {}

	public User(String username, String email, String password, int person_group_id) {
		this.username = username;
		this.email = email;
		this.setPassword(password);
		this.person_group_id = person_group_id;
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

	public int getPerson_group_id() {
		return person_group_id;
	}

	public void setPerson_group_id(int person_group_id) {
		this.person_group_id = person_group_id;
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
					ps.setInt(4, this.person_group_id);
					ps.executeUpdate();
					try (ResultSet rs = ps.getGeneratedKeys()) {
						if (rs.next()) {
							this.id = rs.getInt(1);
						}
					}
				}
			} else {
				String sql = "UPDATE users SET username=?, email=?, password=?, person_group_id=? WHERE id = ?;";
				try (PreparedStatement ps = con.prepareStatement(sql)) {
					ps.setString(1, this.username);
					ps.setString(2, this.email);
					ps.setString(3, this.password);
					ps.setInt(4, this.person_group_id);
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
			String sql = "SELECT * FROM users WHERE id=?;";
			try (PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setInt(1, id);
				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						User loadedUser = new User();
						loadedUser.id = rs.getInt("id");
						loadedUser.username = rs.getString("username");
						loadedUser.password = rs.getString("password");
						loadedUser.email = rs.getString("email");
						loadedUser.person_group_id = rs.getInt("person_group_id");
						return loadedUser;
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
		String dbUrl = "jdbc:mysql://localhost:3306/programming_school?useSSL=false&characterEncoding=utf-8";
		String user = "root";
		String pswd = "mojSQL";
		ArrayList<User> users = new ArrayList<User>();
		try (Connection con = DriverManager.getConnection(dbUrl, user, pswd)) {
			String sql = "SELECT * FROM users;";
			try (PreparedStatement ps = con.prepareStatement(sql)) {
				try (ResultSet rs = ps.executeQuery()) {
					while(rs.next()) {
						User loadedUser = new User();
						loadedUser.id = rs.getInt("id");
						loadedUser.username = rs.getString("username");
						loadedUser.password = rs.getString("password");
						loadedUser.email = rs.getString("email");
						loadedUser.person_group_id = rs.getInt("person_group_id");
						users.add(loadedUser);
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("Database error!");
			e.printStackTrace();
		}
		User[] uArray = new User[users.size()];
		uArray = users.toArray(uArray);
		return uArray;
	}
	
	public void deleteUser() {
		String dbUrl = "jdbc:mysql://localhost:3306/programming_school?useSSL=false&characterEncoding=utf-8";
		String user = "root";
		String pswd = "mojSQL";
		try (Connection con = DriverManager.getConnection(dbUrl, user, pswd)) {
			String sql = "DELETE FROM users WHERE id=?";
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
	
	@Override
	public String toString() {
		String userToString = this.id + ": " + this.username + " email: " + this.email + " group: " + this.person_group_id;
		return userToString;
	}
}