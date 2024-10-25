package dao;

import java.util.List;

import model.Player;

public interface IPlayerDao {

	List<Player> readAll();

	Player readById(long id) throws Exception;

	void insert(Player player);

	void update(Player player) throws Exception;

	void delete(long id) throws Exception;
}
