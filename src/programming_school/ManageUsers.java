//package programming_school;
//
//import java.sql.SQLException;
//import java.util.Scanner;
//
//public class ManageUsers {
//
//	public static void main(String[] args) {
//		@SuppressWarnings("resource")
//		Scanner scanner = new Scanner(System.in);
//		while (true) {
//			loadAllUsersInterface();
//			System.out.print("Enter command:");
//			String command = scanner.nextLine();
//			switch (command) {
//			case "quit":
//				return;
//			case "add":
//				addUserInterface();
//				break;
//			case "edit":
//				editUserInterface();
//				break;
//			case "delete":
//				deleteUserInterface();
//				break;
//			default:
//				System.out.println("Unknown command!");
//			}
//		}
//	}
//
//	public static void loadAllUsersInterface() {
//		User[] users;
//		try {
//			users = User.loadAllUsers();
//			for (User us : users) {
//				System.out.println(us.toString());
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static void addUserInterface() {
//		@SuppressWarnings({ "resource" })
//		Scanner scanner = new Scanner(System.in);
//		System.out.print("Enter new group name:");
//		String name = scanner.nextLine();
//		Group group = new Group(name);
//		try {
//			group.saveGroupToDB();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static void editUserInterface() {
//		@SuppressWarnings({ "resource" })
//		Scanner scanner = new Scanner(System.in);
//		System.out.print("Enter ID of the group you want to edit:");
//		int id = scanner.nextInt();
//		Group group = new Group();
//		try {
//			group = Group.loadGroupById(id);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		System.out.print("Enter the new name of the group:");
//		scanner.nextLine();
//		String name = scanner.nextLine();
//		group.setName(name);
//		try {
//			group.saveGroupToDB();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static void deleteUserInterface() {
//		@SuppressWarnings({ "resource" })
//		Scanner scanner = new Scanner(System.in);
//		System.out.print("Enter ID of the group you want to delete:");
//		int id = scanner.nextInt();
//		Group group = new Group();
//		try {
//			group = Group.loadGroupById(id);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		try {
//			group.deleteGroup();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//}