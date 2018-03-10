package programming_school;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Group {
	
	private int id;
	private String name;
	
	public Group() {};
	
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
	
	public void saveGroupToDB() throws SQLException {
		if(this.id == 0) {
			String sql = "INSERT INTO user_group (name) VALUES (?);";
			String generatedColumns[] = {" ID "};
			PreparedStatement preparedStatement;
			preparedStatement = Connect.connect().prepareStatement(sql, generatedColumns);
			preparedStatement.setString(1, this.name);
			preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			if(resultSet.next()) {
				this.id = resultSet.getInt(1);
			}
				resultSet.close();
				preparedStatement.close();			
		}else {
			String sql = "UPDATE user_group SET name=? WHERE id=?;";
			PreparedStatement preparedStatement;
			preparedStatement = Connect.connect().prepareStatement(sql);
			preparedStatement.setString(1, this.name);
			preparedStatement.setInt(2, this.id);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		}
	}

	static public Group loadGroupById(int id) throws SQLException {
		String sql = "SELECT * FROM user_group WHERE id=?;";
		PreparedStatement preparedStatement;
		preparedStatement = Connect.connect().prepareStatement(sql);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		if(resultSet.next()) {
			Group loadedGroup = new Group();
			loadedGroup.id = resultSet.getInt("id");
			loadedGroup.name = resultSet.getString("name");
			preparedStatement.close();
			resultSet.close();
			return loadedGroup;
		}
		preparedStatement.close();
		resultSet.close();
		return null;
	}
	
	static public Group[] loadAllGroups() throws SQLException {
		ArrayList<Group> groups= new ArrayList<Group>();
		String sql = "SELECT * FROM user_group;";
		PreparedStatement preparedStatement;
		preparedStatement = Connect.connect().prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) {
			Group loadedGroup = new Group();
			loadedGroup.id = resultSet.getInt("id");
			loadedGroup.name = resultSet.getString("name");
			groups.add(loadedGroup);
		}
		Group gArray[] = new Group[groups.size()];
		gArray = groups.toArray(gArray);
		preparedStatement.close();
		resultSet.close();
		return gArray;
	}
	
	public void deleteGroup() throws SQLException {
		if(this.id != 0) {
			String sql = "DELETE FROM user_group WHERE id=?;";
			PreparedStatement preparedStatement;
			preparedStatement = Connect.connect().prepareStatement(sql);
			preparedStatement.setInt(1, this.id);
			preparedStatement.executeUpdate();
			this.id = 0;
			preparedStatement.close();
		}
	}
	
//	static public Group[] loadAllByGroup(Connection conn) throws SQLException {
//		ArrayList<Group> groups= new ArrayList<Group>();
//		String sql = "SELECT * FROM user_group;";
//		PreparedStatement preparedStatement;
//		preparedStatement = conn.prepareStatement(sql);
//		ResultSet resultSet = preparedStatement.executeQuery();
//		while(resultSet.next()) {
//			Group loadedGroup = new Group();
//			loadedGroup.id = resultSet.getInt("id");
//			loadedGroup.name = resultSet.getString("name");
//			groups.add(loadedGroup);
//		}
//		Group gArray[] = new Group[groups.size()];
//		gArray = groups.toArray(gArray);
//		return gArray;
//	}
	@Override
	public String toString() {
		String str="id: " + this.id + " name: " + this.name;
		return str;
	} 
}