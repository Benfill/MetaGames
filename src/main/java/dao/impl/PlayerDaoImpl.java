package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import dao.IPlayerDao;
import model.Player;
import util.HibernateUtil;

public class PlayerDaoImpl implements IPlayerDao {

	@Override
	public List<Player> readAll() {
		Session session = HibernateUtil.getSession();
		Query<Player> query = session.createQuery("FROM Player", Player.class);
		List<Player> players = query.getResultList();
		session.close();
		return players;

	}

	@Override
	public Player readById(long id) throws Exception {
		Session session = HibernateUtil.getSession();
		Player player = session.get(Player.class, id);
		if (player == null)
			throw new Exception("Player not found");
		return player;
	}

	@Override
	public void insert(Player player) {
		Transaction transaction = null;

		Session session = HibernateUtil.getSession();

		try {
			transaction = session.beginTransaction();
			session.save(player);
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
	public void update(Player player) throws Exception {
		Player oldPlayer = readById(player.getId());
		Transaction transaction = null;

		Session session = HibernateUtil.getSession();

		oldPlayer.setPseudo(player.getPseudo());
		oldPlayer.setAge(player.getAge());
		oldPlayer.setTeam(player.getTeam());

		try {
			transaction = session.beginTransaction();
			session.update(oldPlayer);
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
	public void delete(long id) throws Exception {
		Player player = readById(id);
		Transaction transaction = null;

		Session session = HibernateUtil.getSession();

		try {
			transaction = session.beginTransaction();
			session.delete(player);
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

}
