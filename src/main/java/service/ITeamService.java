package service;

import java.util.List;

import model.Team;

public interface ITeamService {

	List<Team> getAll();

	Team getTeamById(long id) throws Exception;

	void createTeam(String name);

	void updateTeam(long id, String name) throws Exception;

	void deleteTeam(long id) throws Exception;

	void addOrRemovePlayer(long teamId, long playerId) throws Exception;
}
