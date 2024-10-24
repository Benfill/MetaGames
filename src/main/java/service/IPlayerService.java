package service;

import java.util.List;

import model.Player;
import model.Team;

public interface IPlayerService {

	List<Player> getALl();

	Player getPlayerById(long id) throws Exception;

	void registerPlayer(String pseudo, int age, Team team);

	void updatePlayer(long id, String pseudo, int age, Team team) throws Exception;

	void deletePlayer(long id) throws Exception;
}
