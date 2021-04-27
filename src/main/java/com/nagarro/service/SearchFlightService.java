package com.nagarro.service;

import com.nagarro.model.Flight;
import com.nagarro.dao.FlightDao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchFlightService{
	
	@Autowired
	private FlightDao flightDao;

	/*
	 * searchFlight function finds matching flights from database whose values are same as user input i.e;
	 * @arrivalLocation, @departLocation, @flightDate, @flightClass
	 */
    public List<Flight> searchFlight(String arrivalLocation, String departLocation, String flightDate, String flightClass)
    {
    	List<Flight> flightList=new ArrayList<Flight>();
    	SimpleDateFormat object = new SimpleDateFormat("yyyy-MM-dd");
    	try {
    		Date date = object.parse(flightDate);
    		flightDate = new SimpleDateFormat("dd-MM-yyyy").format(date);
    	}
    	catch (Exception e) {
    		System.out.println("Please enter date in mentioned format ");
    	}
		flightList = flightDao.getFlights(arrivalLocation, departLocation, flightDate, flightClass);
		if (flightClass.equals("B")) {
			flightList.forEach(f -> {f.setFare(calculateEffectiveFare(f.getFare())); f.setClass("B");});
		}
		return flightList;
		
    }
    
	/*
	 * calculateEffectiveFare function calculates the fare according to the user's preference of seat
	 */
	private int calculateEffectiveFare(int fare){
		fare = (int) (fare*1.4);
		return fare;
	}
 
}