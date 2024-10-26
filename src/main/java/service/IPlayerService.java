package service;

import java.util.List;

import model.Player;

public interface IPlayerService {

	List<Player> getAll();

	Player getPlayerById(long id) throws Exception;

	void registerPlayer(String pseudo, int age);

	void updatePlayer(long id, String pseudo, int age) throws Exception;

	void deletePlayer(long id) throws Exception;
}
