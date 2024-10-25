package dao.impl;

import model.Tournament;

public class TournamentDaoExtension extends TournamentDaoImpl {

	public TournamentDaoExtension(TeamDaoImpl teamDaoImpl) {
		super(teamDaoImpl);
	}

	@Override
	public double calculateEstimatedDuration(Tournament tournament) {
		int teamCount = tournament.getTeams().size();
		double averageMatchDuration = tournament.getGame().getAverageMatchDuration();
		double breakTime = tournament.getBreakTime();
		double difficulty = tournament.getGame().getDifficulty();
		double ceremonyTime = tournament.getCeremonyTime();
		return (teamCount * averageMatchDuration * difficulty) + breakTime + ceremonyTime;
	}
}
