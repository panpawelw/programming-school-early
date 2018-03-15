package programming_school;

import java.util.Scanner;

public class Demo {

	static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		while(true) {
			System.out.println("PROGRAMMING SCHOOL");
			System.out.println("Java MySQL Project");
			System.out.println("Select option");
			System.out.println("1. TESTING");
			System.out.println("2. ADMIN INTERFACE");
			System.out.println("3. USER INTERFACE");
			System.out.println("4. EXIT");
			System.out.println();
			System.out.print("> ");
			while(!scanner.hasNextInt()) scanner.next();
			int mainMenu = scanner.nextInt();
			switch (mainMenu) {
			case 1:
				System.out.println("TESTING");
				break;
			case 2:
				adminInteface();
				break;
			case 3:
				System.out.println("USER INTERFACE");
				break;
			case 4:
				System.out.println("Thanks, bye!");
				return;
			default:
				System.out.println("Choose 1,2,3,4 or 5.");
			}
		}
	}
	
	public static void adminInteface() {																			// ADMIN MENU
		int adminMenu = 0;
		while (adminMenu!=5) {
			System.out.println("1. USER MANAGEMENT");
			System.out.println("2. GROUP MANAGEMENT");
			System.out.println("3. EXERCISE MANAGEMENT");
			System.out.println("4. SOLUTION MANAGEMENT");
			System.out.println("5. EXIT");
			System.out.print("> ");
			while(!scanner.hasNextInt()) scanner.next();
			adminMenu = scanner.nextInt();
			switch (adminMenu) {
			case 1:
				int adminUserMenu = 0;
				while (adminUserMenu!=4) {
					System.out.println("********************USERS LIST*********************");
					ManageUsers.loadAllUsersInterface();
					System.out.println("***************************************************");
					System.out.println("1. ADD USER; 2. EDIT USER; 3. DELETE USER; 4. EXIT;");
					System.out.print("> ");
					while(!scanner.hasNextInt()) scanner.next();
					adminUserMenu = scanner.nextInt();
					switch (adminUserMenu) {
					case 1:
						ManageUsers.addUserInterface();
						break;
					case 2:
						ManageUsers.editUserInterface();
						break;
					case 3:
						ManageUsers.deleteUserInterface();
						break;
					case 4:
						break;
					default:
						System.out.println("Choose 1,2,3 or 4.");
						break;
					}
				}
				break;
			case 2:
				int adminGroupMenu = 0;
				while (adminGroupMenu!=4) {
					System.out.println("**********************GROUPS LIST*********************");
					ManageGroups.loadAllGroupsInterface();
					System.out.println("******************************************************");
					System.out.println("1. ADD GROUP; 2. EDIT GROUP; 3. DELETE GROUP; 4. EXIT;");
					System.out.print("> ");
					while(!scanner.hasNextInt()) scanner.next();
					adminGroupMenu = scanner.nextInt();
					switch (adminGroupMenu) {
					case 1:
						ManageGroups.addGroupInterface();
						break;
					case 2:
						ManageGroups.editGroupInterface();
						break;
					case 3:
						ManageGroups.deleteGroupInterface();
						break;
					case 4:
						break;
					default:
						System.out.println("Choose 1,2,3 or 4.");
						break;
					}
				}
				break;
			case 3:
				int adminExerciseMenu = 0;
				while (adminExerciseMenu!=4) {
					System.out.println("*************************EXERCISES LIST************************");
					ManageExercises.loadAllExercisesInterface();
					System.out.println("***************************************************************");
					System.out.println("1. ADD EXERCISE; 2. EDIT EXERCISE; 3. DELETE EXERCISE; 4. EXIT;");
					System.out.print("> ");
					while(!scanner.hasNextInt()) scanner.next();
					adminExerciseMenu = scanner.nextInt();
					switch (adminExerciseMenu) {
					case 1:
						ManageExercises.addExerciseInterface();
						break;
					case 2:
						ManageExercises.editExerciseInterface();
						break;
					case 3:
						ManageExercises.deleteExerciseInterface();
						break;
					case 4:
						break;
					default:
						System.out.println("Choose 1,2,3 or 4.");
						break;
					}
				}
				break;
			case 4:
				int adminSolutionMenu = 0;
				while (adminSolutionMenu!=3) {
					System.out.println("*******************SOLUTIONS LIST*****************");
					ManageSolutions.loadAllSolutionsInterface();
					System.out.println("**************************************************");
					System.out.println("1. ADD SOLUTION; 2. VIEW USERS SOLUTIONS; 3. EXIT;");
					System.out.print("> ");
					while(!scanner.hasNextInt()) scanner.next();
					adminSolutionMenu = scanner.nextInt();
					switch (adminSolutionMenu) {
					case 1:
//						ManageExercises.addExerciseInterface();
						break;
					case 2:
//						ManageExercises.editExerciseInterface();
						break;
					case 3:
						break;
					default:
						System.out.println("Choose 1,2 or 3.");
						break;
					}
				}
				break;
			case 5:
				return;
			default:
				System.out.println("Choose 1,2,3,4 or 5.");
				break;
			}
		}
	}
}