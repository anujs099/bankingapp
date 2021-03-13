package controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;




public class FactorySession {

	
	static Session session;
	static SessionFactory sessionFactory;
	
	static {
		
		sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(pojo.Registration.class).addAnnotatedClass(pojo.TransferInform.class).buildSessionFactory();
	 
	}
	
	public static Session configUtil() {
		
		try {
			if (sessionFactory != null) {
				session = sessionFactory.openSession();
				
			}
		}
		 catch (Exception e) {
			if (session==null) {
				session.close();
			}
		}
		return session;
	}
	
	
}
