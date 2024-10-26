package service;

import java.util.List;

import model.Game;
import model.Tournament;

public interface ITournamentService {
	List<Tournament> getAll();

	Tournament getTournamentById(long id) throws Exception;

	void createTournament(Tournament tournament);

	void updateTournament(Tournament tournament) throws Exception;

	void deleteTournament(long id) throws Exception;

	void addOrRemoveTeam(long tournamentId, long teamId) throws Exception;

	List<Game> getGames();

	Game getGameById(long id) throws Exception;

	void createGame(Game game);
}
