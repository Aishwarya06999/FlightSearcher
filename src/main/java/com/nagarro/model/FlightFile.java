package com.nagarro.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * FlightFile class is used to store information about the CSVFile that is read already to avoid 
 * multiple read of a same file or a entry in that file. FileName is name of CSV file.
 */
@Entity
@Table(name = "Flight_Files")
public class FlightFile {

	@Id
	private String fileName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public boolean equals(Object obj) {
		FlightFile flightFile = (FlightFile) obj;
		return this.getFileName().equals(flightFile.getFileName());
	}
}