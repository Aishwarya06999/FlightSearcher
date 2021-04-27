package com.nagarro.daoImp;

import java.util.List;

import com.nagarro.dao.*;
import com.nagarro.model.Flight;
import com.nagarro.model.FlightFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;

import org.springframework.beans.factory.annotation.Autowired;

public class FlightDaoImp implements FlightDao {
	
	@Autowired
	private Dao dao;
	
	/*
	 * Adds a new row in database which has information regarding Flight.
	 * */
	
	public void addFlight(Flight flight) {

		dao.begin();

		dao.getSession().save(flight);

		dao.commit();
		dao.close();

	}
	
	/*
	 * Adds a new row in database which has information regarding Flight file name.
	 * */
	
	public void addFlightFile(FlightFile FlightFile) {
		
		dao.begin();

		dao.getSession().save(FlightFile);

		dao.commit();
		dao.close();

	}
	
	/*
	 * Gets all rows in database which has information regarding Flight file name.
	 * */
	
	public List<FlightFile> getAllFlightFiles() {
		
		dao.begin();

		Criteria criteria = dao.getSession().createCriteria(FlightFile.class);
		List<FlightFile> flightFiles = (List<FlightFile>) criteria.list();

		dao.commit();
		dao.close();

		return flightFiles;
	}
	
	/*
	 * Gets all rows in database which has information regarding Flight.
	 * */
	
	public List<Flight> getFlights(String arrivalLocation, String departLocation, String flightDate, String flightClass) {
		
//		Configuration con = new Configuration().configure().addAnnotatedClass(Flight.class);
//        SessionFactory sf=con.buildSessionFactory();
//        Session session=sf.openSession();
//        Transaction t=session.beginTransaction();
		
		dao.begin();
        
        Session session = dao.getSession();

		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Flight> criteriaQuery = criteriaBuilder.createQuery(Flight.class);
		Root<Flight> root = criteriaQuery.from(Flight.class);

		Predicate[] predicates = new Predicate[4];
		
//		predicates[0] = criteriaBuilder.equal(root.get("departLoc"), "DEL");
//		predicates[1] = criteriaBuilder.equal(root.get("arrivalLoc"), "MUB");
//		predicates[2] = criteriaBuilder.equal(root.get("date"), "10-12-2013");
//		predicates[3] = criteriaBuilder.equal(root.get("seat"),"Y");
		
		System.out.println(arrivalLocation +departLocation+flightDate+ flightClass);

		predicates[0] = criteriaBuilder.equal(root.get("departLoc"), departLocation);
		predicates[1] = criteriaBuilder.equal(root.get("arrivalLoc"), arrivalLocation);
		predicates[2] = criteriaBuilder.equal(root.get("date"), flightDate);
		predicates[3] = criteriaBuilder.equal(root.get("seat"),"Y");

		criteriaQuery.select(root).where(predicates);
		
		List<Flight> searchResult = session.createQuery(criteriaQuery).getResultList();

		dao.commit();
		dao.close();
		
//		SQLQuery q = dao.getSession().createSQLQuery("SELECT * FROM airline_table WHERE departLoc=:departLocation and arrivalLoc=:arrivalLocation and date=:flightDate");
//		q.addEntity(Flight.class);
//		q.setParameter("departLocation",departLocation);
//		q.setParameter("arrivalLocation",arrivalLocation);
//		q.setParameter("flightDate",flightDate);
//		List<Flight> searchResult = q.list();
//		dao.commit();
//		dao.close();
		
//		Query q = dao.getSession().createQuery("from Flight where departLoc=:departLocation and arrivalLoc=:arrivalLocation and date=:flightDate");
//		q.setParameter("departLocation",departLocation);
//		q.setParameter("arrivalLocation",arrivalLocation);
//		q.setParameter("flightDate",flightDate);
//		List<Flight> searchResult = q.list();
//		dao.commit();
//		dao.close();
		
//		System.out.println(searchResult);
		
		return searchResult;
	}

}
