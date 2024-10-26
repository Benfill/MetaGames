package controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import console.Console;
import console.GameView;
import console.TeamView;
import console.TournamentView;
import enums.TournamentStatus;
import model.Game;
import model.Team;
import model.Tournament;
import service.ITeamService;
import service.ITournamentService;

public class TournamentController {

	private ITournamentService tournamentService;
	private ITeamService teamService;
	private int choice;

	public TournamentController(ITournamentService tournamentService, ITeamService teamService) {
		if (tournamentService == null)
			throw new IllegalStateException("TournamentService must not be null");
		else if (teamService == null)
			throw new IllegalStateException("TeamService must not be null");

		this.tournamentService = tournamentService;
		this.teamService = teamService;
	}

	public int manageTournament() {
		choice = 0;

		while (true) {
			switch (choice) {
			case 0:
				choice = TournamentView.displayMenu();
				break;

			case 1:
				store();
				break;

			case 2:
				update();
				break;

			case 3:
				delete();
				break;

			case 4:
				addRemoveTeam();
				break;

			case 5:
				show();
				break;

			case 6:
				index();
				break;

			case 7:
				return 0;
			}
		}
	}

	private void index() {
		List<Tournament> tournaments = tournamentService.getAll();

		TournamentView.displayAllTournaments(tournaments);

		this.choice = 7;
	}

	private void show() {
		this.choice = 7;
		List<Tournament> tournaments = tournamentService.getAll();

		TournamentView.displayAllTournaments(tournaments);

		if (tournaments.isEmpty())
			return;

		long id = TournamentView.getTournamentId();

		try {
			tournamentService.getTournamentById(id);
		} catch (Exception e) {
			Console.displayMessage(e.getMessage());
		}
	}

	private void store() {
		List<Game> games = tournamentService.getGames();
		this.choice = 7;
		Game game = null;
		boolean newGame = Console.confirmAction("You want to create a new game?");
		if (!newGame)
			game = GameView.chooseGame(games);

		while (game == null) {
			game = GameView.getGameDetails();
			tournamentService.createGame(game);
		}

		List<Team> teams = new ArrayList<Team>();
		boolean checker = true;
		while (checker) {
			Team team = null;
			boolean newTeam = Console.confirmAction("You want to create a new team?");
			if (!newTeam)
				team = TeamView.chooseTeam(teamService.getAll());
			while (team == null) {
				team = TeamView.getTeamDetails();
				teamService.createTeam(team.getName());
			}

			teams.add(team);
			checker = Console.confirmAction("You want to add an other team?");
		}

		String name = Console.getInput("Enter Tournament Name: ");
		LocalDateTime startDate = Console.getValidatedLocalDateTime("Enter the start date ");
		LocalDateTime endDate = Console.getValidatedLocalDateTime("Enter the end date ");
		int spectatorcount = Console.getValidIntInput("Enter the expected spectator count: ", "Enter a valid number");
		double breakTime = Console.getValidDoubleInput("Enter the break time ");
		double ceremonyTime = Console.getValidDoubleInput("Enter the ceremony time ");
		TournamentStatus status = TournamentView.chooseTournamentStatus();

		Tournament tournament = new Tournament(name, game, startDate, endDate, spectatorcount, breakTime, ceremonyTime,
				status, teams);

		tournamentService.createTournament(tournament);
		Console.displayMessage("Tournament created successfully");
	}

	private void update() {
		this.choice = 7;
		List<Tournament> tournaments = tournamentService.getAll();

		TournamentView.displayAllTournaments(tournaments);
		if (tournaments.isEmpty())
			return;

		long id = TournamentView.getTournamentId();

		try {
			tournamentService.getTournamentById(id);
		} catch (Exception e) {
			Console.displayMessage(e.getMessage());
			return;
		}
		List<Game> games = tournamentService.getGames();
		this.choice = 7;
		Game game = null;
		boolean newGame = Console.confirmAction("You want to create a new game?");
		if (!newGame)
			game = GameView.chooseGame(games);

		if (game == null) {
			game = GameView.getGameDetails();
			tournamentService.createGame(game);
		}

		String name = Console.getOptionalInput("Enter Tournament Name ");
		LocalDateTime startDate = Console.getOptionalLocalDateTime("Enter the start date ");
		LocalDateTime endDate = Console.getOptionalLocalDateTime("Enter the end date ");
		int spectatorcount = Console.getOptionalIntInput("Enter the expected spectator count: ");
		double breakTime = Console.getOptionalDoubleInput("Enter the break time ");
		double ceremonyTime = Console.getOptionalDoubleInput("Enter the ceremony time ");
		TournamentStatus status = TournamentView.chooseTournamentStatus();

		Tournament tournament = new Tournament(id, name, game, startDate, endDate, spectatorcount, breakTime,
				ceremonyTime, status);

		try {
			tournamentService.updateTournament(tournament);
			Console.displayMessage("Tournament updated successfully");
		} catch (Exception e) {
			Console.displayMessage(e.getMessage());
		}
	}

	private void delete() {
		this.choice = 7;
		List<Tournament> tournaments = tournamentService.getAll();

		TournamentView.displayAllTournaments(tournaments);

		if (tournaments.isEmpty())
			return;

		long id = TournamentView.getTournamentId();

		try {
			tournamentService.deleteTournament(id);
			Console.displayMessage("Tournament deleted successfully");
		} catch (Exception e) {
			Console.displayMessage(e.getMessage());
		}
	}

	private void addRemoveTeam() {
		this.choice = 7;
		List<Tournament> tournaments = tournamentService.getAll();
		List<Team> teams = teamService.getAll();

		if (teams.isEmpty())
			Console.displayMessage("there is no team to add or delete");
		else if (tournaments.isEmpty())
			Console.displayMessage("there is no tournament to update");
		else {
			int tournamentId = TournamentView.getTournamentId();
			int teamId = TeamView.getTeamId();

			boolean checker = Console.confirmAction("Are you sure?");
			if (checker) {
				try {
					tournamentService.addOrRemoveTeam(tournamentId, teamId);
					Console.displayMessage("Operation succeeded");
				} catch (Exception e) {
					Console.displayMessage(e.getMessage());
				}
			}
		}
	}
}
