package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import dao.IPlayerDao;
import dao.ITeamDao;
import model.Player;
import model.Team;

public class TeamDaoImpl implements ITeamDao {

	private IPlayerDao playerDao;

	private SessionFactory sessionFactory;

	public TeamDaoImpl(IPlayerDao playerDaoImpl) {
		playerDao = playerDaoImpl;
	}

	@Override
	public List<Team> readAll() {
		Session session = getCurrentSession();
		Query<Team> query = session.createQuery("FROM Team", Team.class);
		List<Team> teams = query.getResultList();
		return teams;

	}

	@Override
	public Team readById(long id) throws Exception {
		Session session = getCurrentSession();
		Team team = session.get(Team.class, id);
		if (team == null)
			throw new Exception("Team not found");
		return team;
	}

	@Override
	public void insert(Team team) {
		Session session = getCurrentSession();
		session.save(team);
	}

	@Override
	public void update(Team team) throws Exception {
		Team oldTeam = readById(team.getId());

		Session session = getCurrentSession();

		if (team.getName() != null)
			oldTeam.setName(team.getName());
		session.update(oldTeam);
	}

	@Override
	public void delete(long id) throws Exception {
		Team team = readById(id);

		Session session = getCurrentSession();
		session.delete(team);

	}

	@Override
	public void addOrRemovePlayer(long teamId, long playerId) throws Exception {
		Team team = readById(teamId);
		Player player = playerDao.readById(playerId);

		// Check if player is already in this team
		if (player.getTeam() != null && player.getTeam().getId() == teamId) {
			// Player is in the team - remove them
			player.setTeam(null);
			team.getPlayers().remove(player);
		} else {
			// If player is in another team, remove from that team first
			if (player.getTeam() != null) {
				player.getTeam().getPlayers().remove(player);
			}

			// Add player to new team
			player.setTeam(team);
			team.getPlayers().add(player);
		}

		// Save changes
		update(team);
		playerDao.update(player);
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

}
