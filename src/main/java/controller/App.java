package controller;

import console.HomeView;

public class App {
	private boolean running = true;
	private int choice = 0;
	private PlayerController playerController;
	private TeamController teamController;
//	private TournamentController tournamentController;

//	public App(PlayerController playerController, TeamController teamController,
//			TournamentController tournamentController) {
//		this.playerController = playerController;
//		this.teamController = teamController;
//		this.tournamentController = tournamentController;
//		runApp();
//	}

	public App(PlayerController playerController, TeamController teamController) {
		this.playerController = playerController;
		this.teamController = teamController;
		runApp();
	}

	public void runApp() {
		while (isRunning()) {
			switch (choice) {
			case 0:
				choice = HomeView.displayMenu();
				break;
			case 1:
				choice = playerController.managePlayers();
				break;

			case 2:
				choice = teamController.manageTeams();
				break;

			case 4:
				choice = HomeView.exit();
				break;

			default:
				setRunning(false);
				break;
			}

		}
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
}
