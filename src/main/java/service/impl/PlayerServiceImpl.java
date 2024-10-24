package service.impl;

import java.util.List;

import dao.IPlayerDao;
import model.Player;
import model.Team;
import service.IPlayerService;

public class PlayerServiceImpl implements IPlayerService {

	IPlayerDao playerDao;

	public PlayerServiceImpl(IPlayerDao playerDao) {
		this.playerDao = playerDao;
	}

	@Override
	public List<Player> getALl() {
		return playerDao.readAll();
	}

	@Override
	public Player getPlayerById(long id) throws Exception {
		return playerDao.readById(id);
	}

	@Override
	public void registerPlayer(String pseudo, int age, Team team) {
		Player player = new Player(pseudo, age, team);

		playerDao.insert(player);
	}

	@Override
	public void updatePlayer(long id, String pseudo, int age, Team team) throws Exception {
		Player player = new Player(id, pseudo, age, team);

		playerDao.update(player);

	}

	@Override
	public void deletePlayer(long id) throws Exception {
		playerDao.delete(id);

	}

}
