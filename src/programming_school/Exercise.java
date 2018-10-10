package programming_school;

import java.sql.Connection;
import java.sql.DriverManager;
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
	
	public void saveExerciseToDB() {
		String dbUrl = "jdbc:mysql://localhost:3306/programming_school?useSSL=false&characterEncoding=utf-8";
		String user = "root";
		String pswd = "mojSQL";
		try (Connection con = DriverManager.getConnection(dbUrl, user, pswd)) {
			if (this.id == 0) {
				String sql = "INSERT INTO exercise (title, description) VALUES (?, ?)";
				String generatedColumns[] = { " ID " };
				try (PreparedStatement ps = con.prepareStatement(sql, generatedColumns)) {
					ps.setString(1, this.title);
					ps.setString(2, this.description);
					ps.executeUpdate();
					try (ResultSet rs = ps.getGeneratedKeys()) {
						if (rs.next()) {
							this.id = rs.getInt(1);
						}
					}
				}
			} else {
				String sql = "UPDATE exercise SET title=?, description=? WHERE id=?;";
				try (PreparedStatement ps = con.prepareStatement(sql)) {
					ps.setString(1, this.title);
					ps.setString(2, this.description);
					ps.setInt(3, this.id);
					ps.executeUpdate();
				}
			}
		} catch (SQLException e) {
			System.out.println("Database error!");
			e.printStackTrace();
		}
	}

	static Exercise loadExerciseById(int id) {
		String dbUrl = "jdbc:mysql://localhost:3306/programming_school?useSSL=false&characterEncoding=utf-8";
		String user = "root";
		String pswd = "mojSQL";
		try (Connection con = DriverManager.getConnection(dbUrl, user, pswd)) {
			String sql = "SELECT * FROM exercise WHERE id=?;";
			try (PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setInt(1, id);
				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						return loadExercise(rs);
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("Database error!");
			e.printStackTrace();
		}
		System.out.println("No such exercise!");
		return null;
	}
	
	static public Exercise[] loadAllExercises() {
		String dbUrl = "jdbc:mysql://localhost:3306/programming_school?useSSL=false&characterEncoding=utf-8";
		String user = "root";
		String pswd = "mojSQL";
		ArrayList<Exercise> exercises = new ArrayList<Exercise>();
		try (Connection con = DriverManager.getConnection(dbUrl, user, pswd)) {
			String sql = "SELECT * FROM exercise;";
			try (PreparedStatement ps = con.prepareStatement(sql)) {
				try (ResultSet rs = ps.executeQuery()) {
					while(rs.next()) {
						Exercise loadedExercise = new Exercise();
						loadedExercise.id = rs.getInt("id");
						loadedExercise.title = rs.getString("title");
						loadedExercise.description = rs.getString("description");
						exercises.add(loadedExercise);
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("Database error!");
			e.printStackTrace();
		}
		Exercise eArray[] = new Exercise[exercises.size()];
		eArray = exercises.toArray(eArray);
		return eArray;
	}
	
	public void deleteExercise() {
		String dbUrl = "jdbc:mysql://localhost:3306/programming_school?useSSL=false&characterEncoding=utf-8";
		String user = "root";
		String pswd = "mojSQL";
		try (Connection con = DriverManager.getConnection(dbUrl, user, pswd)) {
			String sql = "DELETE FROM exercise WHERE id=?";
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
	
	static public Exercise[] allExercisesNotByUserId(int users_id) {
		String dbUrl = "jdbc:mysql://localhost:3306/programming_school?useSSL=false&characterEncoding=utf-8";
		String user = "root";
		String pswd = "mojSQL";
		ArrayList<Exercise> exercises = new ArrayList<Exercise>();
		try (Connection con = DriverManager.getConnection(dbUrl, user, pswd)) {
			String sql = "SELECT * FROM exercise WHERE exercise.id NOT IN (SELECT exercise.id FROM exercise JOIN solution ON exercise.id = solution.exercise_id WHERE users_id = ?);";
			try (PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setInt(1, users_id);
				try (ResultSet rs = ps.executeQuery()) {
					while(rs.next()) {
						Exercise loadedExercise = new Exercise();
						loadedExercise.id = rs.getInt("id");
						loadedExercise.title = rs.getString("title");
						loadedExercise.description = rs.getString("description");
						exercises.add(loadedExercise);
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("Database error!");
			e.printStackTrace();
		}
		Exercise eArray[] = new Exercise[exercises.size()];
		eArray = exercises.toArray(eArray);
		return eArray;
	}

	private static Exercise[] loadExercisesBy(boolean loadAll, int param){
		String dbUrl = "jdbc:mysql://localhost:3306/programming_school?useSSL=false&characterEncoding=utf-8";
		String exercise = "root";
		String pswd = "mojSQL";
		String sql;
		if(loadAll) sql = "SELECT * FROM exercise;";
		else sql = "SELECT * FROM exercise WHERE exercise.id NOT IN (SELECT exercise.id FROM exercise JOIN solution ON exercise.id = solution.exercise_id WHERE user_id = ?);";
		ArrayList<Exercise> exercisesByParamArrayList = new ArrayList<>();
		try (Connection con = DriverManager.getConnection(dbUrl, exercise, pswd)) {
			try (PreparedStatement ps = con.prepareStatement(sql)) {
				if(param!=0) ps.setInt(1, param);
				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						exercisesByParamArrayList.add(loadExercise(rs));
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("Database error!");
			e.printStackTrace();
		}
		Exercise[] exercisesByParamArray = new Exercise[exercisesByParamArrayList.size()];
		exercisesByParamArray = exercisesByParamArrayList.toArray(exercisesByParamArray);
		return exercisesByParamArray;
	}

	private static Exercise loadExercise(ResultSet rs) throws SQLException {
		Exercise loadedExercise = new Exercise();
		loadedExercise.id = rs.getInt("id");
		loadedExercise.title = rs.getString("title");
		loadedExercise.description = rs.getString("description");
		return loadedExercise;
	}

	@Override
	public String toString() {
		return "id: " + this.id + " title: " + this.title + " description: " + this.description;
	}
}