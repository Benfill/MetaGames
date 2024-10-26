package service.impl;

import java.util.List;

import dao.IGameDao;
import dao.ITournamentDao;
import model.Game;
import model.Tournament;
import service.ITournamentService;

public class TournamentServiceImpl implements ITournamentService {

	private ITournamentDao tournamentDao;
	private IGameDao gameDao;

	public TournamentServiceImpl(ITournamentDao tournamentDao, IGameDao gameDao) {
		if (tournamentDao == null)
			throw new IllegalStateException("TournamentDao must not be null");
		else if (gameDao == null)
			throw new IllegalStateException("GameDao must not be null");
		this.tournamentDao = tournamentDao;
		this.gameDao = gameDao;
	}

	@Override
	public List<Tournament> getAll() {
		return tournamentDao.readAll();
	}

	@Override
	public Tournament getTournamentById(long id) throws Exception {
		return tournamentDao.readById(id);
	}

	@Override
	public void createTournament(Tournament tournament) {
		tournamentDao.insert(tournament);
	}

	@Override
	public void updateTournament(Tournament tournament) throws Exception {
		tournamentDao.update(tournament);

	}

	@Override
	public void deleteTournament(long id) throws Exception {
		tournamentDao.delete(id);
	}

	@Override
	public void addOrRemoveTeam(long tournamentId, long teamId) throws Exception {
		tournamentDao.addOrRemoveTeam(tournamentId, teamId);
	}

	@Override
	public List<Game> getGames() {
		return gameDao.readAll();
	}

	@Override
	public Game getGameById(long id) throws Exception {
		return gameDao.readByID(id);
	}

	@Override
	public void createGame(Game game) {
		gameDao.insert(game);
	}

}
