package programming_school;

import java.util.Scanner;

public class ManageExercises {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("***EXERCISES LIST***");
			loadAllExercisesInterface();
			System.out.print("Enter command:");
			String command = scanner.nextLine();
			switch (command) {
			case "quit":
				System.out.println("Bye!");
				return;
			case "add":
				addExerciseInterface();
				break;
			case "edit":
				editExerciseInterface();
				break;
			case "delete":
				deleteExerciseInterface();
				break;
			case "notuser":
				allExercisesNotByUserIdInterface();
				break;
			default:
				System.out.println("Unknown command!");
			}
		}
	}

	public static void loadAllExercisesInterface() {
		Exercise[] exercises = Exercise.loadAllExercises();
		for (Exercise exercise : exercises) {
			System.out.println(exercise.toString());
		}
	}

	public static void addExerciseInterface() {
		@SuppressWarnings({ "resource" })
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter new exercise title: ");
		String title = scanner.nextLine();
		System.out.print("Enter new exercise description: ");
		String description = scanner.nextLine();
		Exercise exercise = new Exercise(title, description);
		exercise.saveExerciseToDB();
	}

	public static void editExerciseInterface() {
		@SuppressWarnings({ "resource" })
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter ID of the exercise you want to edit: ");
		while(!scanner.hasNextInt()) scanner.next();
		int id = scanner.nextInt();
		Exercise exercise = new Exercise();
		exercise = Exercise.loadExerciseById(id);
		if(exercise==null) {
			return;
		}
		System.out.print("Enter new exercise title: ");
		scanner.nextLine();
		String title = scanner.nextLine();
		System.out.print("Enter new exercise description: ");
		String description = scanner.nextLine();
		exercise.setTitle(title);
		exercise.setDescription(description);
		exercise.saveExerciseToDB();
	}

	public static void deleteExerciseInterface() {
		@SuppressWarnings({ "resource" })
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter ID of the exercise you want to delete: ");
		while(!scanner.hasNextInt()) scanner.next();
		int id = scanner.nextInt();
		Exercise exercise = new Exercise();
		exercise = Exercise.loadExerciseById(id);
		if(exercise==null) {
			return;
		}
		exercise.deleteExercise();
	}
	public static void allExercisesNotByUserIdInterface() {
		int user_id = Demo.getIntFromConsole();
		Exercise[] notByUser = Exercise.allExercisesNotByUserId(user_id);
		for (Exercise exercise : notByUser) {
			System.out.println(exercise.toString());
		}
		
	}
}