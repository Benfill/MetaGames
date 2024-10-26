package dao.impl;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import dao.ITeamDao;
import dao.ITournamentDao;
import model.Team;
import model.Tournament;

public class TournamentDaoImpl implements ITournamentDao {

	private ITeamDao teamDao;

	private SessionFactory sessionFactory;

	public TournamentDaoImpl(ITeamDao teamDaoImpl) {
		this.teamDao = teamDaoImpl;
	}

	@Override
	public List<Tournament> readAll() {
		Session session = getCurrentSession();
		Query<Tournament> query = session.createQuery("FROM Tournament", Tournament.class);
		List<Tournament> tournament = query.getResultList();
		return tournament;
	}

	@Override
	public Tournament readById(long id) throws Exception {
		Session session = getCurrentSession();
		Tournament tournament = session.get(Tournament.class, id);
		if (tournament == null)
			throw new Exception("Tournament not found");
		return tournament;

	}

	@Override
	public void insert(Tournament tournament) {
		Session session = getCurrentSession();
		tournament.setEstimatedDuration(calculateEstimatedDuration(tournament));
		session.save(tournament);
	}

	@Override
	public void update(Tournament tournament) throws Exception {
		Tournament oldTournament = readById(tournament.getId());

		oldTournament.setGame(tournament.getGame());
		oldTournament.setName(tournament.getName() != null ? tournament.getName() : oldTournament.getName());
		oldTournament.setStartDate(
				tournament.getStartDate() != null ? tournament.getStartDate() : oldTournament.getStartDate());
		oldTournament
				.setEndDate(tournament.getEndDate() != null ? tournament.getEndDate() : oldTournament.getEndDate());
		oldTournament.setSpectatorCount(tournament.getSpectatorCount() != 0 ? tournament.getSpectatorCount()
				: oldTournament.getSpectatorCount());
		oldTournament.setBreakTime(
				tournament.getBreakTime() != 0 ? tournament.getBreakTime() : oldTournament.getBreakTime());
		oldTournament.setCeremonyTime(
				tournament.getCeremonyTime() != 0 ? tournament.getCeremonyTime() : oldTournament.getCeremonyTime());
		oldTournament.setStatus(tournament.getStatus());

		Session session = getCurrentSession();
		session.update(oldTournament);
	}

	@Override
	public void delete(long id) throws Exception {
		Tournament tournament = readById(id);
		Session session = getCurrentSession();
		session.delete(tournament);
	}

	@Override
	public void addOrRemoveTeam(long TournamentId, long teamId) throws Exception {
		Tournament tournament = readById(TournamentId);
		Team team = teamDao.readById(teamId);

		Optional<Team> teamOptional = tournament.getTeams().stream().filter((t) -> t.getId() == teamId).findFirst();

		if (teamOptional.isPresent()) {
			tournament.getTeams().remove(team);
			team.getTournaments().remove(tournament);
		} else {
			tournament.getTeams().add(team);
			team.getTournaments().add(tournament);
		}

		update(tournament);
		teamDao.update(team);

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
