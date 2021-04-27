package com.nagarro.daoImp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import com.nagarro.dao.Dao;

public class DaoImp implements Dao{

    // Hibernate settings equivalent to hibernate.cfg.xml's properties
	
	private Configuration con = new Configuration().configure("hibernate.cfg.xml");
	private SessionFactory sf = con.buildSessionFactory();
	private Session session;
	
//    private SessionFactory sf = Util.getSessionFactory();
//	private Session session;
	
	public Session getSession() {
		return session;
	}
	
	public void begin() {
		session = sf.openSession();
		session.beginTransaction();
	}

	public void commit() {
		session.getTransaction().commit();
	}

	public void close() {
		session.close();
	}

	public void rollback() {
		session.getTransaction().rollback();

	}

}
