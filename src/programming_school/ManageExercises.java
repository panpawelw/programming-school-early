package programming_school;

public class ManageExercises {

	public static void main(String[] args) {
		while (true) {
			System.out.println("***EXERCISES LIST***");
			loadAllExercisesInterface();
			String command = Demo.getTextFromConsole("Enter command:");
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

	static void loadAllExercisesInterface() {
		Exercise[] exercises = Exercise.loadAllExercises();
		for (Exercise exercise : exercises) {
			System.out.println(exercise.toString());
		}
	}

	static void addExerciseInterface() {
		String title = Demo.getTextFromConsole("Enter new exercise title:");
		String description = Demo.getTextFromConsole("Enter new exercise description:");
		Exercise exercise = new Exercise(title, description);
		exercise.saveExerciseToDB();
	}

	static void editExerciseInterface() {
		int id = Demo.getIntFromConsole("Enter ID of the exercise you want to edit:");
		Exercise exercise = Exercise.loadExerciseById(id);
		if (exercise == null) {
			return;
		}
		String title = Demo.getTextFromConsole("Enter new exercise title:");
		String description = Demo.getTextFromConsole("Enter new exercise description:");
		exercise.setTitle(title);
		exercise.setDescription(description);
		exercise.saveExerciseToDB();
	}

	static void deleteExerciseInterface() {
		int id = Demo.getIntFromConsole("Enter ID of the exercise you want to delete:");
		Exercise exercise = Exercise.loadExerciseById(id);
		if (exercise == null) {
			return;
		}
		exercise.deleteExercise();
	}

	private static void allExercisesNotByUserIdInterface() {
		int user_id = Demo.getIntFromConsole("Enter ID of the user whose exercises without solutions you want to see:");
		Exercise[] notByUser = Exercise.allExercisesNotByUserId(user_id);
		for (Exercise exercise : notByUser) {
			System.out.println(exercise.toString());
		}
	}
}