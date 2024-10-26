package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dao.IGameDao;
import model.Game;

public class GameDaoImpl implements IGameDao {
	private SessionFactory sessionFactory;

	@Override
	public List<Game> readAll() {
		Session session = getSession();
		List<Game> games = session.createQuery("From Game", Game.class).getResultList();
		return games;
	}

	@Override
	public Game readByID(long id) throws Exception {
		Session session = getSession();
		Game game = session.get(Game.class, id);
		if (game == null)
			throw new Exception("Game not found");
		return null;
	}

	@Override
	public void insert(Game game) {
		Session session = getSession();

		session.save(game);
	}

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
