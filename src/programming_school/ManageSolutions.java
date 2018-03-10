package programming_school;

import java.util.Scanner;

public class ManageSolutions {

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
				addSolutionInterface();
				break;
			default:
				System.out.println("Unknown command!");
			}
		}
	}

	public static void addSolutionInterface() {
		System.out.println("Adding solution!");
	}
}