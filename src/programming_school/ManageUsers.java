package programming_school;

public class ManageUsers {

	public static void main(String[] args) {
		while (true) {
			System.out.println("***USERS LIST***");
			loadAllUsersInterface();
			String command = Demo.getTextFromConsole("Enter command:");
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
					loadAllByGroupIdInterface();
					break;
				default:
					System.out.println("Unknown command!");
			}
		}
	}

	static void loadAllUsersInterface() {
		User[] users = User.loadAllUsers();
		for (User user : users) {
			System.out.println(user.toString());
		}
	}

	static void addUserInterface() {
		String name = Demo.getTextFromConsole("Enter new user name:");
		String email = Demo.getTextFromConsole("Enter new user email:");
		String password = Demo.getTextFromConsole("Enter new user password: ");
		int usergroup_id = Demo.getIntFromConsole("Enter new user group number:");
		User user = new User(name, email, password, usergroup_id);
		user.saveUserToDB();
	}

	static void editUserInterface() {
		int id = Demo.getIntFromConsole("Enter ID of the user you want to edit:");
		User user = User.loadUserById(id);
		if (user == null) {
			return;
		}
		String name = Demo.getTextFromConsole("Enter new user name:");
		String email = Demo.getTextFromConsole("Enter new user email:");
		String password = Demo.getTextFromConsole("Enter new user password:");
		int usergroup_id = Demo.getIntFromConsole("Enter new user group number:");
		user.setUsername(name);
		user.setEmail(email);
		user.setPassword(password);
		user.setUsergroup_id(usergroup_id);
		user.saveUserToDB();
	}

	static void deleteUserInterface() {
		int id = Demo.getIntFromConsole("Enter ID of the user you want to delete:");
		User user = User.loadUserById(id);
		if(user!=null) user.deleteUser();
	}

	static void loadAllByGroupIdInterface() {
		int usergroup_id = Demo.getIntFromConsole("Enter ID of the group of users you want to display:");
		User[] groupUsers = User.loadAllbyGroupId(usergroup_id);
		if(groupUsers.length==0) System.out.println("None");
		for (User user : groupUsers) {
			System.out.println(user.toString());
		}
	}
}