package programming_school;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Group {

	private int id;
	private String name;

	public Group() {
	};

	public Group(String name) {

		this.name = name;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public int getId() {

		return id;
	}

	public void saveGroupToDBAlt() {
		String dbUrl = "jdbc:mysql://localhost:3306/programming_school?useSSL=false&characterEncoding=utf-8";
		String user = "root";
		String pswd = "mojSQL";
		try (Connection con = DriverManager.getConnection(dbUrl, user, pswd)) {
			if (this.id == 0) {
				String sql = "INSERT INTO user_group (name) VALUES (?)";
				String generatedColumns[] = { " ID " };
				try (PreparedStatement ps = con.prepareStatement(sql, generatedColumns)) {
					ps.setString(1, this.name);
					ps.executeUpdate();
					try (ResultSet rs = ps.getGeneratedKeys()) {
						if (rs.next()) {
							this.id = rs.getInt(1);
						}
					}
				}
			} else {
				String sql = "UPDATE user_group SET name=? WHERE id=?;";
				try (PreparedStatement ps = con.prepareStatement(sql)) {
					ps.setString(1, this.name);
					ps.setInt(2, this.id);
					ps.executeUpdate();
				}
			}
		} catch (SQLException e) {
			System.out.println("Database error!");
			e.printStackTrace();
		}
	}
	
	static public Group loadGroupByIdAlt(int id) {
		String dbUrl = "jdbc:mysql://localhost:3306/programming_school?useSSL=false&characterEncoding=utf-8";
		String user = "root";
		String pswd = "mojSQL";
		try (Connection con = DriverManager.getConnection(dbUrl, user, pswd)) {
			String sql = "SELECT * FROM user_group WHERE id=?;";
			try (PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setInt(1, id);
				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						Group loadedGroup = new Group();
						loadedGroup.id = rs.getInt("id");
						loadedGroup.name = rs.getString("name");
						return loadedGroup;
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("Database error!");
			e.printStackTrace();
		}
		return null;
	}
	static public Group loadGroupById(int id) throws SQLException {
		SQLConnection connection = new SQLConnection();
		String sql = "SELECT * FROM user_group WHERE id=?;";
		PreparedStatement preparedStatement;
		preparedStatement = connection.connect().prepareStatement(sql);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			Group loadedGroup = new Group();
			loadedGroup.id = resultSet.getInt("id");
			loadedGroup.name = resultSet.getString("name");
			preparedStatement.close();
			resultSet.close();
			connection.disconnect();
			return loadedGroup;
		}
		preparedStatement.close();
		resultSet.close();
		connection.disconnect();
		return null;
	}
	
	static public Group[] loadAllGroupsAlt() {
		String dbUrl = "jdbc:mysql://localhost:3306/programming_school?useSSL=false&characterEncoding=utf-8";
		String user = "root";
		String pswd = "mojSQL";
		ArrayList<Group> groups = new ArrayList<Group>();
		try (Connection con = DriverManager.getConnection(dbUrl, user, pswd)) {
			String sql = "SELECT * FROM user_group;";
			try (PreparedStatement ps = con.prepareStatement(sql)) {
				try (ResultSet rs = ps.executeQuery()) {
					while(rs.next()) {
						Group loadedGroup = new Group();
						loadedGroup.id = rs.getInt("id");
						loadedGroup.name = rs.getString("name");
						groups.add(loadedGroup);
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("Database error!");
			e.printStackTrace();
		}
		Group gArray[] = new Group[groups.size()];
		gArray = groups.toArray(gArray);
		return gArray;
	}

	public void deleteGroupAlt() {
		String dbUrl = "jdbc:mysql://localhost:3306/programming_school?useSSL=false&characterEncoding=utf-8";
		String user = "root";
		String pswd = "mojSQL";
		try (Connection con = DriverManager.getConnection(dbUrl, user, pswd)) {
			String sql = "DELETE FROM user_group WHERE id=?";
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

	// static public Group[] loadAllByGroup(Connection conn) throws SQLException {
	// ArrayList<Group> groups= new ArrayList<Group>();
	// String sql = "SELECT * FROM user_group;";
	// PreparedStatement preparedStatement;
	// preparedStatement = conn.prepareStatement(sql);
	// ResultSet resultSet = preparedStatement.executeQuery();
	// while(resultSet.next()) {
	// Group loadedGroup = new Group();
	// loadedGroup.id = resultSet.getInt("id");
	// loadedGroup.name = resultSet.getString("name");
	// groups.add(loadedGroup);
	// }
	// Group gArray[] = new Group[groups.size()];
	// gArray = groups.toArray(gArray);
	// return gArray;
	// }
	@Override
	public String toString() {
		String str = "id: " + this.id + " name: " + this.name;
		return str;
	}
}