package service;

import java.util.List;

import model.Team;

public interface ITournamentService {
	List<Team> getALl();

	Team getTournamentById(long id) throws Exception;

	void createTournament(String name);

	void updateTournament(long id, String name) throws Exception;

	void deleteTournament(long id) throws Exception;

	void addOrRemoveTeam(long tournamentId, long teamId) throws Exception;
}
