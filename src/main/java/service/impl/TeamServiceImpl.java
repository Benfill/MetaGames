package service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import dao.ITeamDao;
import model.Team;
import service.ITeamService;

public class TeamServiceImpl implements ITeamService {

	private ITeamDao teamDao;

	public TeamServiceImpl(ITeamDao teamDaoImpl) {
		if (teamDaoImpl == null)
			throw new IllegalStateException("TeamDao must not be null");
		this.teamDao = teamDaoImpl;
	}

	@Override
	public List<Team> getAll() {
		return teamDao.readAll();
	}

	@Override
	public Team getTeamById(long id) throws Exception {
		return teamDao.readById(id);
	}

	@Transactional
	@Override
	public void createTeam(String name) {
		Team team = new Team(name);
		team.setRanking(0);
		teamDao.insert(team);

	}

	@Transactional
	@Override
	public void updateTeam(long id, String name) throws Exception {
		Team team = new Team(id, name);
		teamDao.update(team);

	}

	@Transactional
	@Override
	public void deleteTeam(long id) throws Exception {
		teamDao.delete(id);
	}

	@Transactional
	@Override
	public void addOrRemovePlayer(long teamId, long playerId) throws Exception {
		teamDao.addOrRemovePlayer(teamId, playerId);

	}

}
