package controller;

import java.util.List;

import console.Console;
import console.PlayerView;
import model.Player;
import service.IPlayerService;

public class PlayerController {
	private int choice;

	private IPlayerService playerService;

	public PlayerController(IPlayerService service) {
		if (service == null)
			throw new IllegalStateException("PlayerService must not be null");
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
				update();
				return 0;

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
		List<Player> players = playerService.getAll();
		PlayerView.displayAllPlayers(players);
		Console.getInput("Enter anything to Return: ");
		return 6;
	}

	private int store() {
		String pseudo = Console.getInput("Enter player pseudo: ");
		int age = Console.getValidIntInput("Enter player age: ", "Enter a valid number");
		playerService.registerPlayer(pseudo, age);
		Console.displayMessage("Player created successfully");
		Console.getInput("Enter anything to Return: ");
		return 6;
	}

	private int show() {
		long id = PlayerView.getPlayerId();
		try {
			Player player = playerService.getPlayerById(id);
			PlayerView.displayPlayerInfo(player);
		} catch (Exception e) {
			Console.displayMessage(e.getMessage());
		}
		Console.getInput("Enter anything to Return: ");
		return 6;
	}

	private void update() {
		List<Player> players = playerService.getAll();
		PlayerView.displayAllPlayers(players);
		long id = PlayerView.getPlayerId();

		String pseudo = Console.getOptionalInput("Enter new player pseudo");
		int age = Console.getOptionalIntInput("Enter new age");

		try {
			playerService.updatePlayer(id, pseudo, age);
			Console.displayMessage("Player updated successfully");
		} catch (Exception e) {
			Console.displayMessage(e.getMessage());
		}
	}

	private int delete() {
		List<Player> players = playerService.getAll();
		PlayerView.displayAllPlayers(players);
		long id = PlayerView.getPlayerId();
		try {
			playerService.deletePlayer(id);
			Console.displayMessage("Player deleted successfully");
		} catch (Exception e) {
			Console.displayMessage(e.getMessage());
		}
		Console.getInput("Enter anything to Return: ");
		return 6;
	}
}
