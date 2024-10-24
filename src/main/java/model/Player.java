package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "players")
public class Player {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false)
	private String pseudo;

	@Column(nullable = false)
	private int age;

	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;

	public Player() {
	}

	public Player(long id, String pseudo, int age, Team team) {
		this.id = id;
		this.pseudo = pseudo;
		this.age = age;
		this.team = team;
	}

	public Player(String pseudo, int age, Team team) {
		this.pseudo = pseudo;
		this.age = age;
		this.team = team;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
}
