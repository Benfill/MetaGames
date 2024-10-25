package console;

import model.Tournament;

public class TournamentView {
	public void displayMenu() {
		String title = "Tournament Management";
		String[] options = { "Create a tournament", "Modify a tournament", "Add/Remove teams", "Display a tournament",
				"Display all tournaments" };
		Console.printMenu(title, options);
	}

	public int getTournamentId() {
		return Integer.parseInt(Console.getInput("Enter tournament ID: "));
	}

	public void displayTournamentInfo(Tournament tournament) {
		Console.displayMessage(
				"Tournament Details:\n" + "ID: " + tournament.getId() + "\nName: " + tournament.getName());
	}

	public void displayAllTournaments(Tournament[] tournaments) {
		StringBuilder builder = new StringBuilder("List of All Tournaments:\n");
		for (Tournament tournament : tournaments) {
			builder.append("ID: ").append(tournament.getId()).append(", Name: ").append(tournament.getName())
					.append("\n");
		}
		Console.displayMessage(builder.toString());
	}
}
