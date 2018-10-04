package programming_school;

import java.util.Scanner;

public class ManageUsers {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("***USERS LIST***");
			loadAllUsersInterface();
			System.out.print("Enter command:");
			String command = scanner.nextLine();
			switch (command) {
			case "quit":
				return;
			case "add":
				addUserInterface();
				break;
			case "edit":
				editUserInterface();
				break;
			case "delete":
				deleteUserInterface();
				break;
			case "group":
				loadAllbyGroupIdInterface();
				break;
			default:
				System.out.println("Unknown command!");
			}
		}
	}

	public static void loadAllUsersInterface() {
		User[] users;
		users = User.loadAllUsers();
		for(User user : users) {
			System.out.println(user.toString());
		}
	}

	public static void addUserInterface() {
		@SuppressWarnings({ "resource" })
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter new user name: ");
		String name = scanner.nextLine();
		System.out.print("Enter new user email: ");
		String email = scanner.nextLine();
		System.out.print("Enter new user password: ");
		String password = scanner.nextLine();
		System.out.print("Enter new user group number: ");
		while(!scanner.hasNextInt()) scanner.next();
		int person_group_id = scanner.nextInt();
		User user = new User(name, email, password, person_group_id);
		user.saveUserToDB();
	}

	public static void editUserInterface() {
		@SuppressWarnings({ "resource" })
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter ID of the user you want to edit: ");
		while(!scanner.hasNextInt()) scanner.next();
		int id = scanner.nextInt();
		User user = User.loadUserById(id);
		if (user == null) {
			return;
		}
		scanner.nextLine();
		System.out.print("Enter new user name: ");
		String name = scanner.nextLine();
		System.out.print("Enter new user email: ");
		String email = scanner.nextLine();
		System.out.print("Enter new user password: ");
		String password = scanner.nextLine();
		System.out.print("Enter new user group number: ");
		while(!scanner.hasNextInt()) scanner.next();
		int person_group_id = scanner.nextInt();
		user.setUsername(name);
		user.setEmail(email);
		user.setPassword(password);
		user.setUsergroup_id(usergroup_id);
		user.saveUserToDB();
	}

	public static void deleteUserInterface() {
		@SuppressWarnings({ "resource" })
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter ID of the user you want to delete: ");
		while(!scanner.hasNextInt()) scanner.next();
		int id = scanner.nextInt();
		User user = User.loadUserById(id);
		user.deleteUser();
	}
	
	public static void loadAllbyGroupIdInterface() {
		@SuppressWarnings({ "resource" })
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter ID of the group of users you want to display: ");
		while(!scanner.hasNextInt()) scanner.next();
		int person_group_id = scanner.nextInt();
		User[] groupUsers;
		groupUsers = User.loadAllbyGroupId(person_group_id);
		for(User user : groupUsers) {
			System.out.println(user.toString());
		}
	}
}