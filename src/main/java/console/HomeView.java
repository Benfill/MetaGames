package console;

public class HomeView {
	public static int displayMenu() {
		String title = "Welcome To MetaGames Tournament Management";
		String[] options = { "Manage Players", "Manage Teams", "Manage Tournaments" };
		Console.printMenu(title, options);

		return Console.getChoice();
	}

	public static int exit() {
		String message = "Are you sure?";

		if (Console.confirmAction(message)) {
			Console.displayMessage("Exiting...");
			return 100;
		} else
			return 0;
	}
}
