package com.nagarro.service;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import com.nagarro.dao.*;
import com.nagarro.daoImp.*;
import com.nagarro.model.Flight;
import com.nagarro.model.FlightFile;

/*
 * FlightLoaderService class loads files from folder whose path is given into database. 
 * It periodically checks for new files and adds it to database.
 */
public class FlightLoaderService{

	@Autowired
	FlightDao flightDao;
	
	public File[] listOfFiles;
	
	@Scheduled(fixedRate = 15000,initialDelay = 5000)
	public void loadFlights() {
		File folder = new File("C:\\Users\\aishwaryabasutkar\\Desktop\\Assignment Links");
		FileFilter filterLambda = (File pathname) -> pathname.getName().endsWith(".csv");
		listOfFiles = folder.listFiles(filterLambda);
		readFlightsFromFile();

    }
	
	
	/*
	 * readFlightsFromFile loads data from csv to mysql database if that csv file is not already read 
	 * which means that file name is not in FlightFile table in mysql database
	 */
	private void readFlightsFromFile() {
		List<FlightFile> dbFiles = flightDao.getAllFlightFiles();
		FlightFile folderflightFile = new FlightFile();
		for (File file : listOfFiles) {
			try {
				String path = file.getAbsolutePath();
				System.out.println(file.getName());
				folderflightFile.setFileName(file.getName());
				if (!dbFiles.contains(folderflightFile)) {
					
					FileReader filereader = new FileReader(path);
				
					CSVParser parser = new CSVParserBuilder().withSeparator('|').build();
					  
			        CSVReader csvReader = new CSVReaderBuilder(filereader)
			                                  .withCSVParser(parser)
			                                  .build();
			        List<String[]> allFlightData = csvReader.readAll();
	
					for (int rowNumber = 1; rowNumber < allFlightData.size(); rowNumber++) {
						Flight newFlight = createFlightObject(allFlightData.get(rowNumber));
						
						flightDao.addFlight(newFlight);
					}
					flightDao.addFlightFile(folderflightFile);
				}
			}
			catch (Exception e) {
		        e.printStackTrace();
		    }
		}
			
	}
	
	private Flight createFlightObject(String[] flightData) {
		Flight flight = new Flight();
		flight.setFlightNum(flightData[0]);
		flight.setDepartLoc(flightData[1]);
		flight.setArrivalLoc(flightData[2]);
		flight.setDate(flightData[3]);
		flight.setTime(flightData[4]);
		flight.setDuration(Float.parseFloat(flightData[5]));
		flight.setFare(Integer.parseInt(flightData[6]));
		flight.setseat(flightData[7]);
		flight.setClass(flightData[8]);
		return flight;
	}
}
