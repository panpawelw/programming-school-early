//package programming_school;
//
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import mindrot.jbcrypt.BCrypt;
//
//public class User {
//
//	private int id;
//	private String username;
//	private String password;
//	private String email;
//	private int person_group_id;
//
//	public User() {}
//
//	public User(String username, String email, int person_group_id, String password) {
//		this.username = username;
//		this.email = email;
//		this.person_group_id = person_group_id;
//		this.setPassword(password);
//	}
//
//	public String getEmail() {
//		return this.email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public int getId() {
//		return this.id;
//	}
//
//	public String getUsername() {
//		return this.username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public String getPassword() {
//		return this.password;
//	}
//
//	public void setPassword(String password) {
//		this.password = BCrypt.hashpw(password, BCrypt.gensalt());
//	}
//
//	public int getPerson_group_id() {
//		return person_group_id;
//	}
//
//	public void setPerson_group_id(int person_group_id) {
//		this.person_group_id = person_group_id;
//	}
//
//	public void saveUserToDB() throws SQLException {
//		if (this.id == 0) {
//			String sql = "INSERT INTO users(username, email, password, person_group_id) VALUES (?, ?, ?, ?);";
//			String generatedColumns[] = { "ID" };
//			PreparedStatement preparedStatement;
//			preparedStatement = Connect.connect().prepareStatement(sql, generatedColumns);
//			preparedStatement.setString(1, this.username);
//			preparedStatement.setString(2, this.email);
//			preparedStatement.setString(3, this.password);
//			preparedStatement.setInt(4, this.person_group_id);
//			preparedStatement.executeUpdate();
//			ResultSet resultSet = preparedStatement.getGeneratedKeys();
//			if (resultSet.next()) {
//				this.id = resultSet.getInt(1);
//			}
//			resultSet.close();
//			preparedStatement.close();
//		}else {
//			String sql = "UPDATE users SET username=?, email=?, password=?, person_group_id=? WHERE id = ?;";
//			PreparedStatement preparedStatement;
//			preparedStatement = Connect.connect().prepareStatement(sql);
//			preparedStatement.setString(1, this.username);
//			preparedStatement.setString(2, this.email);
//			preparedStatement.setString(3, this.password);
//			preparedStatement.setInt(4, this.person_group_id);
//			preparedStatement.setInt(5, this.id);
//			preparedStatement.executeUpdate();
//			preparedStatement.close();
//		}
//	}
//
//	static public User loadUserById(int id) throws SQLException {
//		String sql = "SELECT * FROM users where id=?;";
//		PreparedStatement preparedStatement;
//		preparedStatement = Connect.connect().prepareStatement(sql);
//		preparedStatement.setInt(1, id);
//		ResultSet resultSet = preparedStatement.executeQuery();
//		if (resultSet.next()) {
//			User loadedUser = new User();
//			loadedUser.id = resultSet.getInt("id");
//			loadedUser.username = resultSet.getString("username");
//			loadedUser.password = resultSet.getString("password");
//			loadedUser.email = resultSet.getString("email");
//			loadedUser.person_group_id = resultSet.getInt("person_group_id");
//			resultSet.close();
//			preparedStatement.close();
//			return loadedUser;
//		}
//		resultSet.close();
//		preparedStatement.close();
//		return null;
//	}
//
//	static public User[] loadAllUsers() throws SQLException {
//		ArrayList<User> users = new ArrayList<User>();
//		String sql = "SELECT * FROM users;";
//		PreparedStatement preparedStatement;
//		preparedStatement = Connect.connect().prepareStatement(sql);
//		ResultSet resultSet = preparedStatement.executeQuery();
//		while (resultSet.next()) {
//			User loadedUser = new User();
//			loadedUser.id = resultSet.getInt("id");
//			loadedUser.username = resultSet.getString("username");
//			loadedUser.password = resultSet.getString("password");
//			loadedUser.email = resultSet.getString("email");
//			users.add(loadedUser);
//		}
//		User[] uArray = new User[users.size()];
//		uArray = users.toArray(uArray);
//		resultSet.close();
//		preparedStatement.close();
//		return uArray;
//	}
//
//	public void deleteUser() throws SQLException {
//
//		if (this.id != 0) {
//			String sql = "DELETE FROM users WHERE id=?;";
//			PreparedStatement preparedStatement;
//			preparedStatement = Connect.connect().prepareStatement(sql);
//			preparedStatement.setInt(1, this.id);
//			preparedStatement.executeUpdate();
//			this.id = 0;
//			preparedStatement.close();
//		}
//	}
//	
//	@Override
//	public String toString() {
//		String userToString = this.id + ": " + this.username + "|| email: " + this.email + "|| group: " + this.person_group_id;
//		return userToString;
//	}
//}