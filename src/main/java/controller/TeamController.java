package controller;

import java.util.List;

import console.Console;
import console.PlayerView;
import console.TeamView;
import model.Player;
import model.Team;
import service.IPlayerService;
import service.ITeamService;

public class TeamController {

	private ITeamService teamService;
	private IPlayerService playerService;
	private int choice;

	public TeamController(ITeamService teamServiceImpl, IPlayerService playerServiceImpl) {
		if (teamServiceImpl == null)
			throw new IllegalStateException("TeamService must not be null");
		else if (playerServiceImpl == null)
			throw new IllegalStateException("PlayerService must not be null");
		this.teamService = teamServiceImpl;
		this.playerService = playerServiceImpl;
	}

	public int manageTeams() {
		choice = 0;

		while (true) {
			switch (choice) {
			case 0:
				choice = TeamView.displayMenu();
				break;

			case 1:
				store();
				return 0;

			case 2:
				update();
				return 0;

			case 3:
				choice = delete();
				break;

			case 4:
				addRemovePlayer();
				return 0;

			case 5:
				choice = show();
				break;

			case 6:
				choice = index();
				break;

			case 7:
				return 0;
			}
		}

	}

	private int index() {
		List<Team> teams = teamService.getAll();
		TeamView.displayAllTeams(teams);
		Console.getInput("Enter anything to Return: ");
		return 7;
	}

	private void store() {
		String name = Console.getInput("Enter team name: ");
		teamService.createTeam(name);
		Console.displayMessage("Team created successfully");
	}

	private int show() {
		List<Team> teams = teamService.getAll();
		if (teams.isEmpty())
			Console.displayMessage("There is no team to show");
		else {
			TeamView.displayAllTeams(teams);
			long id = TeamView.getTeamId();

			try {
				Team team = teamService.getTeamById(id);
				TeamView.displayTeamInfo(team);

			} catch (Exception e) {
				Console.displayMessage("error: " + e.getMessage());
			}

		}
		Console.getInput("Enter anything to Return: ");
		return 7;
	}

	private void update() {
		List<Team> teams = teamService.getAll();
		if (teams.isEmpty())
			Console.displayMessage("there is no team to update");
		else {
			TeamView.displayAllTeams(teams);
			long id = TeamView.getTeamId();

			String name = Console.getOptionalInput("Enter new team name ");
			try {
				teamService.updateTeam(id, name);
				Console.displayMessage("Team updated successfully");
			} catch (Exception e) {
				Console.displayMessage("error: " + e.getMessage());
			}

		}
	}

	private int delete() {
		List<Team> teams = teamService.getAll();
		if (teams.isEmpty())
			Console.displayMessage("there is no team to delete");
		else {
			TeamView.displayAllTeams(teams);
			long id = TeamView.getTeamId();
			boolean checker = Console.confirmAction("Are you sure you want to delete this team?");
			if (checker) {
				try {
					teamService.deleteTeam(id);
					Console.displayMessage("Team deleted successfully");
				} catch (Exception e) {
					Console.displayMessage("error: " + e.getMessage());
				}
			}
		}
		Console.getInput("Enter anything to Return: ");
		return 7;
	}

	private void addRemovePlayer() {
		List<Team> teams = teamService.getAll();
		List<Player> players = playerService.getALl();

		if (teams.isEmpty())
			Console.displayMessage("there is no team to update");
		else if (players.isEmpty())
			Console.displayMessage("there is no player to add or delete");
		else {
			int teamId = TeamView.getTeamId();
			int playerId = PlayerView.getPlayerId();

			boolean checker = Console.confirmAction("Are you sure?");
			if (checker) {
				try {
					teamService.addOrRemovePlayer(teamId, playerId);
					Console.displayMessage("Operation succeeded");
				} catch (Exception e) {
					Console.displayMessage(e.getMessage());
				}
			}
		}
	}
}
