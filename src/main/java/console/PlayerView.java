package console;

import java.util.List;

import model.Player;

public class PlayerView {
	public static int displayMenu() {
		String title = "Player Management";
		String[] options = { "Register a player", "Modify a player", "Delete a player", "Display a player",
				"Display all players" };
		Console.printMenu(title, options);
		return Console.getChoice();
	}

	public static int getPlayerId() {
		return Console.getValidIntInput("Enter player ID: ");
	}

	public static void displayPlayerInfo(Player player) {
		Console.displayMessage("Player Details:\n" + "ID: " + player.getId() + "\nPseudo: " + player.getPseudo()
				+ "\nAge: " + player.getAge() + "\nTeam: " + player.getTeam().getName());
	}

	public static void displayAllPlayers(List<Player> players) {
		StringBuilder builder = new StringBuilder("List of All Players:\n");
		players.forEach(player -> {
			builder.append("ID: ").append(player.getId()).append(", Name: ").append(player.getPseudo())
					.append(", Age: ").append(player.getAge()).append(", Team: ").append(player.getTeam().getName())
					.append("\n");
		});
		Console.displayMessage(!players.isEmpty() ? builder.toString() : "List is empty");
	}
}