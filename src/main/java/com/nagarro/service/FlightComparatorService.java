package com.nagarro.service;

import java.util.Comparator;

import org.springframework.stereotype.Service;

import com.nagarro.model.Flight;

/*
 * FlightComparatorService class generates Comparator according to user's output
 * preference.
 */

@Service
public class FlightComparatorService {
	/*
	 * This method creates and returns a Comparator that sorts the flights by fare.
	 */
	public Comparator<Flight> getFlightComparatorbyFare() {

		return (flight1, flight2) -> {
			return Integer.compare(flight1.getFare(), flight2.getFare());
		};

	}

	/*
	 * This method creates and returns a Comparator that sorts the flights by duration.
	 */
	public Comparator<Flight> getFlightComparatorbyFareDuration() {

		return (flight1, flight2) -> {
			int sortLevel1 = Integer.compare(flight1.getFare(), flight2.getFare());
			if (sortLevel1 == 0) {
				int sortLevel2 = Double.compare(flight1.getDuration(), flight2.getDuration());
				return sortLevel2;
			}
			return sortLevel1;
		};
		
	}

}
