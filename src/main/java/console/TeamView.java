package console;

import model.Team;

public class TeamView {
	public int displayMenu() {
		String title = "Team Management";
		String[] options = { "Create a team", "Modify a team", "Add/Remove players", "Display a team",
				"Display all teams" };
		Console.printMenu(title, options);

		return Console.getChoice();

	}

//	public Team getTeamDetails() {
//		String name = Console.getInput("Team Name: ");
//
//		return new Team(name);
//	}

	public int getTeamId() {
		return Integer.parseInt(Console.getInput("Enter team ID: "));
	}

	public void displayTeamInfo(Team team) {
		Console.displayMessage("Team Details:\n" + "ID: " + team.getId() + "\nName: " + team.getName());
	}

	public void displayAllTeams(Team[] teams) {
		StringBuilder builder = new StringBuilder("List of All Teams:\n");
		for (Team team : teams) {
			builder.append("ID: ").append(team.getId()).append(", Name: ").append(team.getName()).append("\n");
		}
		Console.displayMessage(builder.toString());
	}
}
