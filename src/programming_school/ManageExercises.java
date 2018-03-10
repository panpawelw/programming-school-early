package programming_school;

import java.util.Scanner;

public class ManageExercises {

	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			System.out.print("Enter command:");
			String command = scanner.nextLine();
			switch (command) {
			case "quit":
				return;
			case "add":
				addExerciseInterface();
				break;
			default:
				System.out.println("Unknown command!");
			}
		}
	}

	public static void addExerciseInterface() {
		System.out.println("Adding exercise!");
	}
}