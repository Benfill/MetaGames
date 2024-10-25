package console;

import java.util.List;

import model.Team;

public class TeamView {
	public static int displayMenu() {
		String title = "Team Management";
		String[] options = { "Create a team", "Modify a team", "Delete a team", "Add/Remove players", "Display a team",
				"Display all teams" };
		Console.printMenu(title, options);

		return Console.getChoice();

	}

//	public Team getTeamDetails() {
//		String name = Console.getInput("Team Name: ");
//
//		return new Team(name);
//	}

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
}
