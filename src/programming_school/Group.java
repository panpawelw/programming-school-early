package programming_school;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Group {

	private int id;
	private String name;

	public Group() {
	}

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

	void saveGroupToDB() {
		String dbUrl = "jdbc:mysql://localhost:3306/programming_school?useSSL=false&characterEncoding=utf-8";
		String user = "root";
		String pswd = "mojSQL";
		try (Connection con = DriverManager.getConnection(dbUrl, user, pswd)) {
			if (this.id == 0) {
				String sql = "INSERT INTO usergroup (name) VALUES (?)";
				String generatedColumns[] = {" ID "};
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
				String sql = "UPDATE usergroup SET name=? WHERE id=?;";
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

	static Group loadGroupById(int id) {
		String dbUrl = "jdbc:mysql://localhost:3306/programming_school?useSSL=false&characterEncoding=utf-8";
		String user = "root";
		String pswd = "mojSQL";
		try (Connection con = DriverManager.getConnection(dbUrl, user, pswd)) {
			String sql = "SELECT * FROM usergroup WHERE id=?;";
			try (PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setInt(1, id);
				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						return loadGroup(rs);
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("Database error!");
			e.printStackTrace();
		}
		System.out.println("No such group!");
		return null;
	}

	static Group[] loadAllGroups() {
		String dbUrl = "jdbc:mysql://localhost:3306/programming_school?useSSL=false&characterEncoding=utf-8";
		String user = "root";
		String pswd = "mojSQL";
		List<Group> groups = new ArrayList<>();
		try (Connection con = DriverManager.getConnection(dbUrl, user, pswd)) {
			String sql = "SELECT * FROM usergroup;";
			try (PreparedStatement ps = con.prepareStatement(sql)) {
				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						groups.add(loadGroup(rs));
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

	void deleteGroup() {
		String dbUrl = "jdbc:mysql://localhost:3306/programming_school?useSSL=false&characterEncoding=utf-8";
		String user = "root";
		String pswd = "mojSQL";
		try (Connection con = DriverManager.getConnection(dbUrl, user, pswd)) {
			String sql = "DELETE FROM usergroup WHERE id=?";
			try (PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setInt(1, this.id);
				ps.executeUpdate();
				this.id = 0;
			}
		} catch (SQLException e) {
			System.out.println("Database error!");
			e.printStackTrace();
		}
	}

	private static Group loadGroup(ResultSet rs) throws SQLException {
		Group loadedGroup = new Group();
		loadedGroup.id = rs.getInt("id");
		loadedGroup.name = rs.getString("name");
		return loadedGroup;
	}

	@Override
	public String toString() {
		return "id: " + this.id + " name: " + this.name;
	}
}