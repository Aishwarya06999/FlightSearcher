package com.nagarro.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.model.Flight;

@Service
public class OutputPreferanceService {
	
	@Autowired
	private FlightComparatorService flightComparator;
	
	/*
	 * sortFlights function sorts the search result according to the user's
	 * preference.
	 */
	
    public List<Flight> sortFlights(List<Flight> flightList, int choice)
    {

		Comparator<Flight> comparator;

		if (choice == 1) {
			comparator = flightComparator.getFlightComparatorbyFare();
			Collections.sort(flightList, comparator);
		} else {
			comparator = flightComparator.getFlightComparatorbyFareDuration();
			Collections.sort(flightList, comparator);
		}
        
        return flightList;

    }
}
