package programming_school;

public class ManageGroups {

	public static void main(String[] args) {
		while (true) {
			System.out.println("***GROUPS LIST***");
			loadAllGroupsInterface();
			String command = Demo.getTextFromConsole("Enter command:");
			switch (command) {
				case "quit":
					System.out.println("Bye!");
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

	static void loadAllGroupsInterface() {
		Group[] groups = Group.loadAllGroups();
		for (Group group : groups) {
			System.out.println(group.toString());
		}
	}

	static void addGroupInterface() {
		String name = Demo.getTextFromConsole("Enter new group name:");
		Group group = new Group(name);
		group.saveGroupToDB();
	}

	static void editGroupInterface() {
		int id = Demo.getIntFromConsole("Enter ID of the group you want to edit:");
		Group group = Group.loadGroupById(id);
		if (group == null) {
			return;
		}
		String name = Demo.getTextFromConsole("Enter new group name:");
		group.setName(name);
		group.saveGroupToDB();
	}

	static void deleteGroupInterface() {
		int id = Demo.getIntFromConsole("Enter ID of the group you want to delete:");
		Group group = Group.loadGroupById(id);
		if (group == null) {
			return;
		}
		group.deleteGroup();
	}
}