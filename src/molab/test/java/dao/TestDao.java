package molab.test.java.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class TestDao {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Configuration cfg = new Configuration().configure();
		Session session = cfg.buildSessionFactory().openSession(); 
		Transaction tx = session.beginTransaction();
        
        tx.commit(); 
        session.close();
	}

}
