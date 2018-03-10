package programming_school;

import java.sql.SQLException;
import java.util.Scanner;

public class ManageGroups {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		while (true) {
			loadAllGroupsInterface();
			System.out.print("Enter command:");
			String command = scanner.nextLine();
			switch (command) {
			case "quit":
				return;
			case "add":
				addGroupInterface();
				break;
			case "edit":
				editGroupInterface();
				break;
			case "delete":
				deleteGroupInterface();
				break;
			default:
				System.out.println("Unknown command!");
			}
		}
	}

	public static void loadAllGroupsInterface() {
		Group[] groups;
		try {
			groups = Group.loadAllGroups();
			for (Group gr : groups) {
				System.out.println(gr.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void addGroupInterface() {
		@SuppressWarnings({ "resource" })
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter new group name:");
		String name = scanner.nextLine();
		Group group = new Group(name);
		try {
			group.saveGroupToDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void editGroupInterface() {
		@SuppressWarnings({ "resource" })
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter ID of the group you want to edit:");
		int id = scanner.nextInt();
		Group group = new Group();
		try {
			group = Group.loadGroupById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.print("Enter the new name of the group:");
		scanner.nextLine();
		String name = scanner.nextLine();
		group.setName(name);
		try {
			group.saveGroupToDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deleteGroupInterface() {
		@SuppressWarnings({ "resource" })
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter ID of the group you want to delete:");
		int id = scanner.nextInt();
		Group group = new Group();
		try {
			group = Group.loadGroupById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			group.deleteGroup();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}