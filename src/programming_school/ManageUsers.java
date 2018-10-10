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

	static void addUserInterface() {
		String name = Demo.getTextFromConsole("Enter new user name:");
		String email = Demo.getTextFromConsole("Enter new user email:");
		String password = Demo.getTextFromConsole("Enter new user password: ");
		int usergroup_id = Demo.getIntFromConsole("Enter new user group number:");
		User user = new User(name, email, password, usergroup_id);
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

	public static void deleteUserInterface() {
		@SuppressWarnings({ "resource" })
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter ID of the user you want to delete: ");
		while(!scanner.hasNextInt()) scanner.next();
		int id = scanner.nextInt();
		User user = User.loadUserById(id);
		if(user!=null) user.deleteUser();
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