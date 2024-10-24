package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.Game;
import model.Player;
import model.Team;
import model.Tournament;

public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private HibernateUtil() {
	} // Private constructor to prevent instantiation

	private static SessionFactory buildSessionFactory() {
		try {
			// Create the Configuration
			Configuration configuration = new Configuration();

			// Configure using hibernate.cfg.xml
			configuration.configure();

			configuration.addAnnotatedClass(Player.class);
			configuration.addAnnotatedClass(Team.class);
			configuration.addAnnotatedClass(Tournament.class);
			configuration.addAnnotatedClass(Game.class);

			return configuration.buildSessionFactory();

		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed: " + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	private static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static Session getSession() {
		return sessionFactory.openSession();
	}

	// Optional: Call this on application shutdown
	public static void shutdown() {
		getSessionFactory().close();
	}
}