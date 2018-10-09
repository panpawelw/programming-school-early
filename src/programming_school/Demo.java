package programming_school;

import java.util.Scanner;

public class Demo {

	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
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
		while(true) {
			System.out.println("PROGRAMMING SCHOOL");
			System.out.println("Java MySQL Project");
			System.out.println("Select option");
			System.out.println("1. ADMIN INTERFACE");
			System.out.println("2. USER INTERFACE");
			System.out.println("0. EXIT");
			System.out.println();
			System.out.print("> ");
			int mainMenu = getIntFromConsole();
			switch (mainMenu) {
			case 1:
				adminInteface();
				break;
			case 2:
				userInterface(0);
				break;
			case 0:
				System.out.println("Thanks, bye!");
				scanner.close();
				return;
			default:
				System.out.println("Choose 1,2 or 0");
			}
		}
	}

//	ADMIN MENU
	private static void adminInteface() {
		System.out.println("ADMIN INTERFACE");
		while (true) {
			System.out.println("1. USER MANAGEMENT");
			System.out.println("2. GROUP MANAGEMENT");
			System.out.println("3. EXERCISE MANAGEMENT");
			System.out.println("4. SOLUTION MANAGEMENT");
			System.out.println("5. USERS AND EXERCISES");
			System.out.println("0. EXIT");
			System.out.print("> ");
			int adminMenu = getIntFromConsole();
			switch (adminMenu) {
			case 1:
				int adminUserMenu = 5;
				while (adminUserMenu!=0) {
					System.out.println("******************************USERS LIST******************************");
					ManageUsers.loadAllUsersInterface();
					System.out.println("**********************************************************************");
					System.out.println("1. ADD USER; 2. EDIT USER; 3. DELETE USER; 4. USERS BY GROUP; 0. EXIT;");
					System.out.print("> ");
					adminUserMenu = getIntFromConsole();
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
					case 0:
						break;
					default:
						System.out.println("Choose 1,2,3,4 or 0.");
						break;
					}
				}
				break;
			case 2:
				int adminGroupMenu = 4;
				while (adminGroupMenu!=0) {
					System.out.println("**********************GROUPS LIST*********************");
					ManageGroups.loadAllGroupsInterface();
					System.out.println("******************************************************");
					System.out.println("1. ADD GROUP; 2. EDIT GROUP; 3. DELETE GROUP; 0. EXIT;");
					System.out.print("> ");
					adminGroupMenu = getIntFromConsole();
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
					case 0:
						break;
					default:
						System.out.println("Choose 1,2,3 or 0.");
						break;
					}
				}
				break;
			case 3:
				int adminExerciseMenu = 4;
				while (adminExerciseMenu!=0) {
					System.out.println("*************************EXERCISES LIST************************");
					ManageExercises.loadAllExercisesInterface();
					System.out.println("***************************************************************");
					System.out.println("1. ADD EXERCISE; 2. EDIT EXERCISE; 3. DELETE EXERCISE; 0. EXIT;");
					System.out.print("> ");
					adminExerciseMenu = getIntFromConsole();
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
					case 0:
						break;
					default:
						System.out.println("Choose 1,2,3 or 0.");
						break;
					}
				}
				break;
			case 4:
				int adminSolutionMenu = 7;
				while (adminSolutionMenu!=0) {
					System.out.println("*************************************************************SOLUTIONS LIST************************************************************");
					ManageSolutions.loadAllSolutionsInterface();
					System.out.println("***************************************************************************************************************************************");
					System.out.println("1. ADD SOLUTION; 2. EDIT SOLUTION; 3. DELETE SOLUTION; 4. ADD SOLUTION TO USER; 5. SOLUTIONS BY USER; 6. SOLUTIONS BY EXERCISE; 0.EXIT;");
					System.out.print("> ");
					adminSolutionMenu = getIntFromConsole();
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
						ManageUsers.loadAllUsersInterface();
						System.out.println("Select user:");
						System.out.print("> ");
						int user_id = getIntFromConsole();
						System.out.println("Exercises you don't have solutions for yet:");
						Exercise[] notByUser = Exercise.allExercisesNotByUserId(user_id);
						for(Exercise exercise : notByUser) {
							System.out.println(exercise.toString());
						}
						System.out.println("Select exercise:");
						System.out.print("> ");
						int exercise_id = getIntFromConsole();
						Solution[] usersSolutions;
						usersSolutions = Solution.loadAllByUserId(user_id);
						boolean exerciseExists = false;
						for(Solution solution : usersSolutions) {
							if(solution.getExercise_id()==exercise_id) {
								exerciseExists = true;
							}
						}
						if(!exerciseExists) {
							getNewSolutionName(exercise_id, user_id);
						}else {
							System.out.println("This user's solution for this exercise already exists!");
						}

						break;
					case 5:
						ManageSolutions.usersSolutionsInterface(0);
						break;
					case 6:
						ManageSolutions.exerciseSolutionsInterface();
						break;
					case 0:
						break;
					default:
						System.out.println("Choose 1,2,3,4,5,6 or 0.");
						break;
					}
				}
				break;
			case 5:
				int adminUserExerciseMenu = 3;
				while (adminUserExerciseMenu!=0) {
					System.out.println("1. ADD EXERCISE TO USER - CREATE SOLUTION; 2. SOLUTIONS BY USERS; 0. EXIT;");
					System.out.print("> ");
					adminUserExerciseMenu = getIntFromConsole();
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
						ManageSolutions.usersSolutionsInterface(0);
						break;
					case 0:
						break;
					default:
						System.out.println("Choose 1,2 or 0.");
						break;
					}
				}
				break;
			case 0:
				return;
			default:
				System.out.println("Choose 1,2,3,4,5 or 0.");
				break;
			}
		}
	}
//	USER MENU
	private static void userInterface(int user) {
		User selectedUser = null;
		int user_id=user;
		System.out.println("USER INTERFACE");
		while(selectedUser==null) {
			if(user==0) {
				ManageUsers.loadAllUsersInterface();
				System.out.println("Select user (0 to quit):");
				System.out.print("> ");
				user = getIntFromConsole();
				if (user==0) { return; }
			}else {
				System.out.println("User selected: " + user);
			}
			selectedUser = User.loadUserById(user);
			user_id = user;
			user = 0;
		}
		int userMenu = 3;
		while (userMenu!=0) {
			System.out.println(selectedUser.toString());
			System.out.println("1. ADD EXERCISE - CREATE SOLUTION; 2. VIEW YOUR SOLUTIONS; 0. EXIT;");
			System.out.print("> ");
			userMenu = getIntFromConsole();
			switch (userMenu) {
			case 1:
				System.out.println("Exercises you don't have solutions for yet:");
				Exercise[] notByUser = Exercise.allExercisesNotByUserId(user_id);
				for(Exercise exercise : notByUser) {
					System.out.println(exercise.toString());
				}
				System.out.println("Select exercise:");
				System.out.print("> ");
				while(!scanner.hasNextInt()) scanner.next();
				int exercise_id = scanner.nextInt();
				boolean exerciseExists = false;
				Solution[] usersSolutions;
				usersSolutions = Solution.loadAllByUserId(user_id);
				for(Solution solution : usersSolutions) {
					if(solution.getExercise_id()==exercise_id) {
						exerciseExists = true;
					}
				}
				if(!exerciseExists) {
					getNewSolutionName(exercise_id, user_id);
				}else {
					System.out.println("Your solution for this exercise already exists!");
				}
				break;
			case 2:
				System.out.println("************YOUR SOLUTIONS LIST************");
				ManageSolutions.usersSolutionsInterface(user_id);
				System.out.println("*******************************************");
				System.out.println();
				break;
			case 0:
				break;
			default:
				System.out.println("Choose 1,2 or 0.");
				break;
			}
		}
	}

	private static void getNewSolutionName(int exercise_id, int user_id) {
		System.out.println("Enter solution description:");
		System.out.print("> ");
		scanner.next();
		String description = scanner.nextLine();
		System.out.println(description);
		Solution solution = new Solution(description, exercise_id, user_id);
		System.out.println(solution.toString());
		solution.saveSolutionToDB();
		solution.saveSolutionToDB();
	}

//	get integer from console input
	static int getIntFromConsole() {
		while(!scanner.hasNextInt()) scanner.next();
		return scanner.nextInt();
	}
}