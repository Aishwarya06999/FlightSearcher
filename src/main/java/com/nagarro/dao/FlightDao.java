package com.nagarro.dao;

import java.util.List;

import com.nagarro.model.Flight;
import com.nagarro.model.FlightFile;

public interface FlightDao {
	
	public void addFlight(Flight flight);
	public void addFlightFile(FlightFile newFlightFile);
	public List<FlightFile> getAllFlightFiles();
	public List<Flight> getFlights(String arrivalLocation, String departLocation, String flightDate, String flightClass);
}
