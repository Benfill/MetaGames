package service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import dao.IPlayerDao;
import model.Player;
import service.IPlayerService;

public class PlayerServiceImpl implements IPlayerService {

	private IPlayerDao playerDao;

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

	@Transactional
	@Override
	public void registerPlayer(String pseudo, int age) {
		Player player = new Player(pseudo, age);

		playerDao.insert(player);
	}

	@Transactional
	@Override
	public void updatePlayer(long id, String pseudo, int age) throws Exception {
		Player player = new Player(id, pseudo, age);

		playerDao.update(player);

	}

	@Transactional
	@Override
	public void deletePlayer(long id) throws Exception {
		playerDao.delete(id);

	}

}
