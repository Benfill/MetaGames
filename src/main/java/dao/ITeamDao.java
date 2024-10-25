package dao;

import java.util.List;

import model.Team;

public interface ITeamDao {

	List<Team> readAll();

	Team readById(long id) throws Exception;

	void insert(Team team);

	void update(Team team) throws Exception;

	void delete(long id) throws Exception;

	void addOrRemovePlayer(long TeamId, long playerId) throws Exception;
}
