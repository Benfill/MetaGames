package controller;

import java.util.List;

import console.Console;
import console.PlayerView;
import model.Player;
import service.impl.PlayerServiceImpl;

public class PlayerController {
	private int choice;

	private PlayerServiceImpl playerService;

	public PlayerController() {

	}

	public PlayerController(PlayerServiceImpl service) {
		this.playerService = service;
	}

	public int managePlayers() {
		choice = 0;

		while (true) {
			switch (choice) {
			case 0:
				choice = PlayerView.displayMenu();
				break;

			case 1:
				choice = store();
				break;

			case 2:
				choice = update();
				break;

			case 3:
				choice = delete();
				break;

			case 4:
				choice = show();
				break;

			case 5:
				choice = index();
				break;

			case 6:
				return 0;
			}
		}

	}

	private int index() {
		List<Player> players = playerService.getALl();
		PlayerView.displayAllPlayers(players);
		Console.getInput("Enter anything to Return: ");
		return 6;
	}

	private int store() {
		Console.getInput("Enter anything to Return: ");
		return 6;
	}

	private int show() {
		Console.getInput("Enter anything to Return: ");
		return 6;
	}

	private int update() {
		Console.getInput("Enter anything to Return: ");
		return 6;
	}

	private int delete() {
		Console.getInput("Enter anything to Return: ");
		return 6;
	}
}
