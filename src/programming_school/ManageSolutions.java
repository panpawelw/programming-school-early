package programming_school;

public class ManageSolutions {

	public static void main(String[] args) {
		while (true) {
			System.out.println("***SOLUTIONS LIST***");
			loadAllSolutionsInterface();
			String command = Demo.getTextFromConsole("Enter command:");
			switch (command) {
				case "quit":
					return;
				case "add":
					addSolutionInterface();
					break;
				case "edit":
					editSolutionInterface();
					break;
				case "delete":
					deleteSolutionInterface();
					break;
				case "user":
					usersSolutionsInterface(0);
					break;
				case "exercise":
					exerciseSolutionsInterface();
					break;
				default:
					System.out.println("Unknown command!");
			}
		}
	}

	static void loadAllSolutionsInterface() {
		Solution[] solutions = Solution.loadAllSolutions();
		for (Solution solution : solutions) {
			System.out.println(solution.toString());
		}
	}

	static void addSolutionInterface() {
		String description = Demo.getTextFromConsole("Enter new solution description:");
		int exercise_id = Demo.getIntFromConsole("Enter new exercise number:");
		int users_id = Demo.getIntFromConsole("Enter new user number:");
		Solution solution = new Solution(description, exercise_id, users_id);
		solution.saveSolutionToDB();
	}

	static void editSolutionInterface() {
		int id = Demo.getIntFromConsole("Enter ID of the solution you want to edit:");
		Solution solution = Solution.loadSolutionById(id);
		if (solution == null) {
			return;
		}
		String description = Demo.getTextFromConsole("Enter new solution description:");
		int exercise_id = Demo.getIntFromConsole("Enter new exercise number:");
		int users_id = Demo.getIntFromConsole("Enter new user number:");
		solution.setDescription(description);
		solution.setExercise_id(exercise_id);
		solution.setUsers_id(users_id);
		solution.saveSolutionToDB();
	}

	static void deleteSolutionInterface() {
		int id = Demo.getIntFromConsole("Enter ID of the solution you want to delete:");
		Solution solution = Solution.loadSolutionById(id);
		if(solution!=null) solution.deleteSolution();
	}

	static void usersSolutionsInterface(int users_id) {
		if (users_id == 0) {
			ManageUsers.loadAllUsersInterface();
			users_id = Demo.getIntFromConsole("Enter ID of the user whose solutions you'd like to browse:");
		}
		Solution[] usersSolutions;
		usersSolutions = Solution.loadAllByUserId(users_id);
		for (Solution solution : usersSolutions) {
			System.out.println(solution.toString());
		}
	}

	static void exerciseSolutionsInterface() {
		ManageExercises.loadAllExercisesInterface();
		int exercise_id = Demo.getIntFromConsole("Enter ID of the exercise which solutions of you'd like to browse:");
		Solution[] exerciseSolutions;
		exerciseSolutions = Solution.loadAllByExerciseId(exercise_id);
		for (Solution solution : exerciseSolutions) {
			System.out.println(solution.toString());
		}
	}
}