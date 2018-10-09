package programming_school;

import java.util.Scanner;

public class ManageSolutions {

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("***SOLUTIONS LIST***");
			loadAllSolutionsInterface();
			System.out.print("Enter command:");
			String command = scanner.nextLine();
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

	public static void addSolutionInterface() {
		@SuppressWarnings({ "resource" })
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter new solution description: ");
		String description = scanner.nextLine();
		System.out.print("Enter new exercise number: ");
		int exercise_id = Demo.getIntFromConsole();
		System.out.print("Enter new user number: ");
		int users_id = Demo.getIntFromConsole();
		Solution solution = new Solution(description, exercise_id, users_id);
		solution.saveSolutionToDB();
	}

	public static void editSolutionInterface() {
		@SuppressWarnings({ "resource" })
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter ID of the solution you want to edit: ");
		int id = Demo.getIntFromConsole();
		Solution solution = Solution.loadSolutionById(id);
		if (solution == null) {
			return;
		}
		scanner.nextLine();
		System.out.print("Enter new solution description: ");
		String description = scanner.nextLine();
		System.out.print("Enter new exercise number: ");
		int exercise_id = Demo.getIntFromConsole();
		System.out.print("Enter new user number: ");
		int users_id = Demo.getIntFromConsole();
		solution.setDescription(description);
		solution.setExercise_id(exercise_id);
		solution.setUsers_id(users_id);
		solution.saveSolutionToDB();
	}

	public static void deleteSolutionInterface() {
		@SuppressWarnings({ "resource" })
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter ID of the solution you want to delete: ");
		int id = Demo.getIntFromConsole();
		Solution solution = Solution.loadSolutionById(id);
		if(solution!=null) solution.deleteSolution();
	}
	
	public static void usersSolutionsInterface(int users_id) {
		if(users_id==0) {
			@SuppressWarnings({ "resource" })
			Scanner scanner = new Scanner(System.in);
			ManageUsers.loadAllUsersInterface();
			System.out.print("Enter ID of the user whose solutions you'd like to browse: ");
			users_id = Demo.getIntFromConsole();
		}
		Solution[] usersSolutions;
		usersSolutions = Solution.loadAllByUserId(users_id);
		for (Solution solution : usersSolutions) {
			System.out.println(solution.toString());
		}
	}
	
	public static void exerciseSolutionsInterface() {
		@SuppressWarnings({ "resource" })
		Scanner scanner = new Scanner(System.in);
		ManageExercises.loadAllExercisesInterface();
		System.out.print("Enter ID of the exercise which solutions of you'd like to browse: ");
		int exercise_id = Demo.getIntFromConsole();
		Solution[] exerciseSolutions;
		exerciseSolutions = Solution.loadAllByExerciseId(exercise_id);
		for (Solution solution : exerciseSolutions) {
			System.out.println(solution.toString());
		}
	}
}