package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import dao.ITournamentDao;
import model.Team;
import model.Tournament;

public class TournamentDaoImpl implements ITournamentDao {

	private TeamDaoImpl teamDao;

	private SessionFactory sessionFactory;

	public TournamentDaoImpl(TeamDaoImpl teamDaoImpl) {
		this.teamDao = teamDaoImpl;
	}

	@Override
	public List<Tournament> readAll() {
		Session session = getCurrentSession();
		Query<Tournament> query = session.createQuery("FROM Tournament", Tournament.class);
		List<Tournament> tournament = query.getResultList();
		session.close();
		return tournament;
	}

	@Override
	public Tournament readById(long id) throws Exception {
		Session session = getCurrentSession();
		Tournament tournament = session.get(Tournament.class, id);
		if (tournament == null)
			throw new Exception("Tournament not found");
		session.close();
		return tournament;

	}

	@Override
	public void insert(Tournament tournament) {
		Transaction transaction = null;

		Session session = getCurrentSession();

		try {
			transaction = session.beginTransaction();
			session.save(tournament);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback(); // Rollback in case of an error
			}
			e.printStackTrace();
		} finally {
			session.close(); // Always close the session
		}

	}

	@Override
	public void update(Tournament tournament) throws Exception {
		Tournament oldTournament = readById(tournament.getId());
		Transaction transaction = null;

		Session session = getCurrentSession();

//		oldTournament.set

		try {
			transaction = session.beginTransaction();
			session.update(oldTournament);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback(); // Rollback in case of an error
			}
			e.printStackTrace();
		} finally {
			session.close(); // Always close the session
		}

	}

	@Override
	public void delete(long id) {
		Tournament tournament = readById(id);
		Transaction transaction = null;

		Session session = getCurrentSession();

		try {
			transaction = session.beginTransaction();
			session.delete(tournament);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback(); // Rollback in case of an error
			}
			e.printStackTrace();
		} finally {
			session.close(); // Always close the session
		}

	}

	@Override
	public void addOrRemoveTeam(long TournamentId, long teamId) {
		Tournament tournament = readById(teamId);
		Team team = teamDao.readById(teamId);

		// Check if player is already in this team
		if (team.getTournaments() != null && team.getTournaments().getId() == teamId) {
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

	@Override
	public double calculateEstimatedDuration(Tournament tournament) {
		int teamCount = tournament.getTeams().size();
		double averageMatchDuration = tournament.getGame().getAverageMatchDuration();
		double breakTime = tournament.getBreakTime();
		return (teamCount * averageMatchDuration) + breakTime;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

}
