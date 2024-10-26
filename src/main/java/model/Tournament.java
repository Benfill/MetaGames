package model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import enums.TournamentStatus;

@Entity
@Table(name = "tournaments")
public class Tournament {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false)
	private String name;

	@ManyToOne
	@JoinColumn(name = "game_id") // This creates a foreign key in the Tournament table
	private Game game;

	@Column(name = "start_date", nullable = false)
	private LocalDateTime startDate;

	@Column(name = "end_date", nullable = false)
	private LocalDateTime endDate;

	@Column(name = "spectator_count", nullable = false)
	private int spectatorCount;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "team_tournament", // Join table name
			joinColumns = @JoinColumn(name = "tournament_id"), // Foreign key in team_tournament referencing tournaments
			inverseJoinColumns = @JoinColumn(name = "team_id") // Foreign key in team_tournament referencing teams
	)
	private List<Team> teams;

	@Column(name = "estimated_duration", nullable = false)
	private double estimatedDuration;

	@Column(name = "break_time", nullable = false)
	private double breakTime;

	@Column(name = "ceremony_time", nullable = false)
	private double ceremonyTime;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TournamentStatus status;

	public Tournament(String name, Game game, LocalDateTime start, LocalDateTime end, int spectator, double breakTime,
			double ceremoneyTime, TournamentStatus status, List<Team> teams) {
		this.name = name;
		this.game = game;
		this.startDate = start;
		this.endDate = end;
		this.spectatorCount = spectator;
		this.breakTime = breakTime;
		this.ceremonyTime = ceremoneyTime;
		this.status = status;
		this.teams = teams;
	}

	public Tournament(long id, String name, Game game, LocalDateTime start, LocalDateTime end, int spectator,
			double breakTime, double ceremoneyTime, TournamentStatus status) {
		this.id = id;
		this.name = name;
		this.game = game;
		this.startDate = start;
		this.endDate = end;
		this.spectatorCount = spectator;
		this.breakTime = breakTime;
		this.ceremonyTime = ceremoneyTime;
		this.status = status;
	}

	public Tournament() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public int getSpectatorCount() {
		return spectatorCount;
	}

	public void setSpectatorCount(int spectatorCount) {
		this.spectatorCount = spectatorCount;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public double getEstimatedDuration() {
		return estimatedDuration;
	}

	public void setEstimatedDuration(double estimatedDuration) {
		this.estimatedDuration = estimatedDuration;
	}

	public double getBreakTime() {
		return breakTime;
	}

	public void setBreakTime(double breakTime) {
		this.breakTime = breakTime;
	}

	public double getCeremonyTime() {
		return ceremonyTime;
	}

	public void setCeremonyTime(double ceremonyTime) {
		this.ceremonyTime = ceremonyTime;
	}

	public TournamentStatus getStatus() {
		return status;
	}

	public void setStatus(TournamentStatus status) {
		this.status = status;
	}

}
