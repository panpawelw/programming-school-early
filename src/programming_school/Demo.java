package programming_school;

import java.util.Scanner;

public class Demo {

	static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		while(true) {
			if(args.length>0) {
				int user=0;
				try {
					user = Integer.parseInt(args[0]);
				}catch(NumberFormatException e) {
					System.err.println("User number must be an integer!");
					user = 0;
				}
				userInterface(user);
			}
			System.out.println("PROGRAMMING SCHOOL");
			System.out.println("Java MySQL Project");
			System.out.println("Select option");
			System.out.println("1. ADMIN INTERFACE");
			System.out.println("2. USER INTERFACE");
			System.out.println("3. EXIT");
			System.out.println();
			System.out.print("> ");
			while(!scanner.hasNextInt()) scanner.next();
			int mainMenu = scanner.nextInt();
			switch (mainMenu) {
			case 1:
				adminInteface();
				break;
			case 2:
				userInterface(0);
				break;
			case 3:
				System.out.println("Thanks, bye!");
				scanner.close();
				return;
			default:
				System.out.println("Choose 1,2 or 3");
			}
		}
	}
	
	public static void adminInteface() {																			// ADMIN MENU
		System.out.println("ADMIN INTERFACE");
		int adminMenu = 0;
		while (adminMenu!=5) {
			System.out.println("1. USER MANAGEMENT");
			System.out.println("2. GROUP MANAGEMENT");
			System.out.println("3. EXERCISE MANAGEMENT");
			System.out.println("4. SOLUTION MANAGEMENT");
			System.out.println("5. USERS AND EXERCISES");
			System.out.println("6. EXIT");
			System.out.print("> ");
			while(!scanner.hasNextInt()) scanner.next();
			adminMenu = scanner.nextInt();
			switch (adminMenu) {
			case 1:
				int adminUserMenu = 0;
				while (adminUserMenu!=5) {
					System.out.println("******************************USERS LIST******************************");
					ManageUsers.loadAllUsersInterface();
					System.out.println("**********************************************************************");
					System.out.println("1. ADD USER; 2. EDIT USER; 3. DELETE USER; 4. USERS BY GROUP; 5. EXIT;");
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
						ManageUsers.loadAllbyGroupIdInterface();
						break;
					case 5:
						break;
					default:
						System.out.println("Choose 1,2,3,4 or 5.");
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
				while (adminSolutionMenu!=6) {
					System.out.println("**************************************************SOLUTIONS LIST*************************************************");
					ManageSolutions.loadAllSolutionsInterface();
					System.out.println("*****************************************************************************************************************");
					System.out.println("1. ADD SOLUTION; 2. EDIT SOLUTION; 3. DELETE SOLUTION; 4. ADD SOLUTION TO USER; 5. VIEW USERS SOLUTIONS; 6. EXIT;");
					System.out.print("> ");
					while(!scanner.hasNextInt()) scanner.next();
					adminSolutionMenu = scanner.nextInt();
					switch (adminSolutionMenu) {
					case 1:
						ManageSolutions.addSolutionInterface();
						break;
					case 2:
						ManageSolutions.editSolutionInterface();
						break;
					case 3:
						ManageSolutions.deleteSolutionInterface();
						break;
					case 4:
						break;
					default:
						System.out.println("Choose 1,2,3,4,5 or 6.");
						break;
					}
				}
				break;
			case 5:
				int adminUserExerciseMenu = 0;
				while (adminUserExerciseMenu!=3) {
					System.out.println("1. ADD EXERCISE TO USER - CREATE SOLUTION; 2. SOLUTIONS BY USERS; 3. EXIT;");
					System.out.print("> ");
					while(!scanner.hasNextInt()) scanner.next();
					adminUserExerciseMenu = scanner.nextInt();
					switch (adminUserExerciseMenu) {
					case 1:
						ManageUsers.loadAllUsersInterface();
						System.out.println("Select user:");
						System.out.print("> ");
						while(!scanner.hasNextInt()) scanner.next();
						int user_id = scanner.nextInt();
						ManageExercises.loadAllExercisesInterface();
						System.out.println("Select exercise:");
						System.out.print("> ");
						while(!scanner.hasNextInt()) scanner.next();
						int exercise_id = scanner.nextInt();
						String description = null;
						Solution solution = new Solution(description, exercise_id, user_id);
						solution.saveSolutionToDB();
						break;
					case 2:
						ManageSolutions.usersSolutionsInterface();
						break;
					case 3:
						break;
					default:
						System.out.println("Choose 1,2 or 3.");
						break;
					}
				}
				break;
			case 6:
				return;
			default:
				System.out.println("Choose 1,2,3,4 or 5.");
				break;
			}
		}
	}
	public static void userInterface(int user) {
		User selectedUser = null;
		System.out.println("USER INTERFACE");
		while(selectedUser==null) {
			if(user==0) {
				ManageUsers.loadAllUsersInterface();
				System.out.println("Select user:");
				System.out.print("> ");
				while(!scanner.hasNextInt()) scanner.next();
				user = scanner.nextInt();
			}else {
				System.out.println("User selected: " + user);
			}
			selectedUser = User.loadUserById(user);
			user = 0;
		}
		int userMenu = 0;
		while (userMenu!=3) {
			System.out.println(selectedUser.toString());
			System.out.println("1. ADD SOLUTION; 2. VIEW YOUR SOLUTIONS; 3. EXIT;");
			System.out.print("> ");
			while(!scanner.hasNextInt()) scanner.next();
			userMenu = scanner.nextInt();
			switch (userMenu) {
			case 1:
				ManageUsers.loadAllUsersInterface();
				System.out.println("Select user:");
				System.out.print("> ");
				while(!scanner.hasNextInt()) scanner.next();
				int user_id = scanner.nextInt();
				ManageExercises.loadAllExercisesInterface();
				System.out.println("Select exercise:");
				System.out.print("> ");
				while(!scanner.hasNextInt()) scanner.next();
				int exercise_id = scanner.nextInt();
				String description = null;
				Solution solution = new Solution(description, exercise_id, user_id);
				solution.saveSolutionToDB();
				break;
			case 2:
				ManageSolutions.usersSolutionsInterface();
				break;
			case 3:
				break;
			default:
				System.out.println("Choose 1,2 or 3.");
				break;
			}
		}
	}
}