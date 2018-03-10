package programming_school;

import java.util.Scanner;

public class ManageUsers {

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
				addUserInterface();
				break;
			default:
				System.out.println("Unknown command!");
			}
		}
	}

	public static void addUserInterface() {
		System.out.println("Adding user!");
	}
}