package dao;

import java.util.List;

import model.Game;

public interface IGameDao {

	List<Game> readAll();

	Game readByID(long id) throws Exception;

	void insert(Game game);
}
