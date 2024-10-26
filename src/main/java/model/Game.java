package model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "games")
public class Game {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private double difficulty;

	@Column(nullable = false)
	private double averageMatchDuration;

	@OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Tournament> tournament;

	public Game(String name, double difficulty, double averageMatchDuration) {
		this.name = name;
		this.difficulty = difficulty;
		this.averageMatchDuration = averageMatchDuration;
	}

	public Game() {

	}

	public List<Tournament> getTournament() {
		return tournament;
	}

	public void setTournament(List<Tournament> tournament) {
		this.tournament = tournament;
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

	public double getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(double difficulty) {
		this.difficulty = difficulty;
	}

	public double getAverageMatchDuration() {
		return averageMatchDuration;
	}

	public void setAverageMatchDuration(double averageMatchDuration) {
		this.averageMatchDuration = averageMatchDuration;
	}
}
