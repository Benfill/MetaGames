package console;

import java.util.List;
import java.util.Scanner;

import model.Game;

public class GameView {

	private static final double[] averageDurations = { 15, 30, 45, 60, 120, 180 };

	public static int getTournamentId() {
		return Integer.parseInt(Console.getInput("Enter game ID: "));
	}

	public static void displayGameInfo(Game game) {
		Console.displayMessage("Game Details:\n" + "ID: " + game.getId() + "\nName: " + game.getName());
	}

	public static Game chooseGame(List<Game> games) {
		if (games.isEmpty()) {
			System.out.println("No games available.");
			return null;
		}

		System.out.println("Choose a game:");
		int index = 1;

		// Display all games with indices
		for (Game game : games) {
			System.out.println(index + ". ID: " + game.getId() + ", Name: " + game.getName());
			index++;
		}

		Scanner scanner = new Scanner(System.in);
		int choice;

		do {
			System.out.print("Enter your choice (1-" + games.size() + "): ");
			choice = scanner.nextInt();
		} while (choice < 1 || choice > games.size());

		// Return the selected game using list index
		return games.get(choice - 1);
	}

	public static double chooseAverageMatchDuration() {
		Console.displayMessage("Choose the average match duration (in minutes):");

		// Display the options
		for (int i = 0; i < averageDurations.length; i++) {
			System.out.println((i + 1) + ". " + averageDurations[i] + " minutes");
		}

		int choice;
		do {
			choice = Console.getValidIntInput("Enter your choice (1-" + averageDurations.length + "): ",
					"Enter a valid choice number");
		} while (choice < 1 || choice > averageDurations.length);

		return averageDurations[choice - 1];
	}

	public static Game getGameDetails() {
		Console.displayMessage("Enter Game Details:");

		// Get the game name
		String name = Console.getInput("Enter the game name: ");

		// Get the difficulty level as a double
		double difficulty = Console.getValidDoubleInput("Enter the game difficulty (e.g., 1.0, 2.5)");

		double averageMatchDuration = chooseAverageMatchDuration();

		// Confirm the action to proceed
		boolean confirm = Console.confirmAction("Do you want to save these game details?");
		if (confirm) {
			Console.displayMessage("Game details saved successfully.");
			return new Game(name, difficulty, averageMatchDuration);
		} else {
			Console.displayMessage("Game creation cancelled.");
			return null; // Returns null if the user cancels
		}
	}
}
