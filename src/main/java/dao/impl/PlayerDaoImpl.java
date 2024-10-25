package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import dao.IPlayerDao;
import model.Player;

public class PlayerDaoImpl implements IPlayerDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<Player> readAll() {
		Session session = getCurrentSession();
		Query<Player> query = session.createQuery("FROM Player", Player.class);
		List<Player> players = query.getResultList();
		return players;

	}

	@Override
	public Player readById(long id) throws Exception {
		Session session = getCurrentSession();
		Player player = session.get(Player.class, id);
		if (player == null)
			throw new Exception("Player not found");
		return player;
	}

	@Override
	public void insert(Player player) {
		Session session = getCurrentSession();
		session.save(player);
	}

	@Override
	public void update(Player player) throws Exception {
		Player oldPlayer = readById(player.getId());

		Session session = getCurrentSession();

		oldPlayer.setPseudo(player.getPseudo() != null ? player.getPseudo() : oldPlayer.getPseudo());
		oldPlayer.setAge(player.getAge() != 0 ? player.getAge() : oldPlayer.getAge());
		session.update(oldPlayer);

	}

	@Override
	public void delete(long id) throws Exception {
		Player player = readById(id);

		Session session = getCurrentSession();
		session.delete(player);
	}

}
