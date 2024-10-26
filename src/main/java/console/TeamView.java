package console;

import java.util.List;
import java.util.Scanner;

import model.Team;

public class TeamView {
	public static int displayMenu() {
		String title = "Team Management";
		String[] options = { "Create a team", "Modify a team", "Delete a team", "Add/Remove players", "Display a team",
				"Display all teams" };
		Console.printMenu(title, options);

		return Console.getChoice();

	}

	public static int getTeamId() {
		return Console.getValidIntInput("Enter team ID: ", "Enter a valid number");
	}

	public static void displayTeamInfo(Team team) {
		Console.displayMessage(
				"Team Details:\n" + "ID: " + team.getId() + "\nName: " + team.getName() + "\nTeam Players:\n");
		if (team.getPlayers() == null)
			Console.displayMessage("List is empty");
		team.getPlayers().forEach(player -> {
			PlayerView.displayPlayerInfo(player);
		});
	}

	public static void displayAllTeams(List<Team> teams) {
		StringBuilder builder = new StringBuilder("List of All Teams:\n");
		teams.forEach(team -> {
			builder.append("ID: ").append(team.getId()).append(", Name: ").append(team.getName()).append("\n");
		});
		Console.displayMessage(!teams.isEmpty() ? builder.toString() : "List is empty");
	}

	public static Team chooseTeam(List<Team> teams) {
		if (teams.isEmpty()) {
			System.out.println("No teams available.");
			return null;
		}

		System.out.println("Choose a team:");
		int index = 1;

		for (Team team : teams) {
			System.out.println(index + ". ID: " + team.getId() + ", Name: " + team.getName());
			index++;
		}

		Scanner scanner = new Scanner(System.in);
		int choice;

		do {
			System.out.print("Enter your choice (1-" + teams.size() + "): ");
			choice = scanner.nextInt();
		} while (choice < 1 || choice > teams.size());

		return teams.get(choice - 1);
	}

	public static Team getTeamDetails() {
		Console.displayMessage("Enter team Details:");

		String name = Console.getInput("Enter the team name: ");

		boolean confirm = Console.confirmAction("Do you want to save these team details?");
		if (confirm) {
			Console.displayMessage("Game details saved successfully.");
			return new Team(name);
		} else {
			Console.displayMessage("team creation cancelled.");
			return null;
		}
	}
}
