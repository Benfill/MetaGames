package console;

import java.util.List;
import java.util.Scanner;

import enums.TournamentStatus;
import model.Tournament;

public class TournamentView {

	public static int displayMenu() {
		String title = "Tournament Management";
		String[] options = { "Create a tournament", "Modify a tournament", "Delete Tournament", "Add/Remove teams",
				"Display a tournament", "Display all tournaments" };
		Console.printMenu(title, options);
		return Console.getChoice();
	}

	public static int getTournamentId() {
		return Integer.parseInt(Console.getInput("Enter tournament ID: "));
	}

	public static void displayTournamentInfo(Tournament tournament) {
		Console.displayMessage(
				"Tournament Details:\n" + "ID: " + tournament.getId() + "\nName: " + tournament.getName());
	}

	public static void displayAllTournaments(List<Tournament> tournaments) {
		StringBuilder builder = new StringBuilder("List of All Tournaments:\n");
		tournaments.forEach(tournament -> {
			builder.append("ID: ").append(tournament.getId()).append(", Name: ").append(tournament.getName())
					.append("\n");
		});
		Console.displayMessage(!tournaments.isEmpty() ? builder.toString() : "List is empty");
	}

	public static TournamentStatus chooseTournamentStatus() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please choose the tournament status:");

		int index = 1;
		for (TournamentStatus status : TournamentStatus.values()) {
			System.out.println(index++ + ". " + status.name());
		}

		TournamentStatus selectedStatus = null;
		boolean validChoice = false;

		while (!validChoice) {
			System.out.print("Enter your choice (1-" + TournamentStatus.values().length + "): ");
			int choice = scanner.nextInt();

			index = 1; // Reset index for validation loop
			for (TournamentStatus status : TournamentStatus.values()) {
				if (choice == index) {
					selectedStatus = status;
					validChoice = true;
					break;
				}
				index++;
			}

			if (!validChoice) {
				System.out.println("Invalid choice. Please try again.");
			}
		}

		return selectedStatus;
	}
}
