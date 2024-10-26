package dao;

import java.util.List;

import model.Tournament;

public interface ITournamentDao {

	List<Tournament> readAll();

	Tournament readById(long id) throws Exception;

	void insert(Tournament tournament);

	void update(Tournament tournament) throws Exception;

	void delete(long id) throws Exception;

	void addOrRemoveTeam(long TournamentId, long teamId) throws Exception;

	double calculateEstimatedDuration(Tournament tournament);
}
