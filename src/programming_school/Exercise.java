package programming_school;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Exercise {

	private int id;
	private String title;
	private String description;
	
	public Exercise() {};
	
	public Exercise(String title, String description) {
		
		this.title = title;
		this.description = description;
		
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}
	
	public void saveExerciseToDB(Connection conn) throws SQLException {
		if(this.id == 0) {
			String sql = "INSERT INTO exercise (title, description) VALUES (?, ?);";
			String generatedColumns[] = {" ID "};
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql, generatedColumns);
			preparedStatement.setString(1, this.title);
			preparedStatement.setString(2, this.description);
			preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			if(resultSet.next()) {
				this.id = resultSet.getInt(1);
		} else {
			String sql1 = "UPDATE exercise SET title=?, description=? WHERE id=?;";
			
			PreparedStatement preparedStatement1;
			preparedStatement1 = conn.prepareStatement(sql1);
			preparedStatement1.setString(1, this.title);
			preparedStatement1.setString(2, this.description);
			preparedStatement1.setInt(3, this.id);
			preparedStatement1.executeUpdate();
			}
			
		}
		
	}

	static public Exercise loadExerciseById(Connection conn, int id) throws SQLException {
	
		String sql = "SELECT * FROM exercise WHERE id=?";
		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		if(resultSet.next()) {
			Exercise loadedExercise = new Exercise();
			loadedExercise.id = resultSet.getInt("id");
			loadedExercise.title = resultSet.getString("title");
			loadedExercise.description = resultSet.getString("description");
			return loadedExercise;
		}
		return null;
	}
	
	static public Exercise[] loadAllExercises(Connection conn) throws SQLException {
		
		ArrayList<Exercise> exercises = new ArrayList<Exercise>();
		String sql = "SELECT * FROM exercise";
		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) {
			Exercise loadedExercise = new Exercise();
			loadedExercise.id = resultSet.getInt("id");
			loadedExercise.title = resultSet.getString("title");
			loadedExercise.description = resultSet.getString("description");
			exercises.add(loadedExercise);
		}
		Exercise[] eArray = new Exercise[exercises.size()];
		eArray = exercises.toArray(eArray);
		return eArray;
	}
	
	public void deleteExercise(Connection conn) throws SQLException {
		
		if(this.id !=0) {
			String sql = "DELETE FROM exercie WHERE id=?";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, this.id);
			preparedStatement.executeUpdate();
			this.id = 0;
		}
		
	}
	
	static public Exercise[] loadAllByUserId(Connection conn) throws SQLException {
		
		ArrayList<Exercise> exercises = new ArrayList<Exercise>();
		String sql = "SELECT * FROM exercise";
		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) {
			Exercise loadedExercise = new Exercise();
			loadedExercise.id = resultSet.getInt("id");
			loadedExercise.title = resultSet.getString("title");
			loadedExercise.description = resultSet.getString("description");
			exercises.add(loadedExercise);
		}
		Exercise[] eArray = new Exercise[exercises.size()];
		eArray = exercises.toArray(eArray);
		return eArray;
	}
}