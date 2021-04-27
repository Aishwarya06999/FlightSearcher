package com.nagarro.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * Flight class is used to store information about the flights which is in CSVFile that is read
 * already to avoid multiple read of a same file or a entry in that file. FileName is name of CSV file.
 */
@Entity
@Table(name="airline_table")
public class Flight {
	
	@Id
	@GeneratedValue
	private int flightId;
//	@GeneratedValue(generator="system-uuid")
//    @GenericGenerator(name="system-uuid", strategy = "uuid")
	private String flightNum;
    private String departLoc;
    private String arrivalLoc;
    private String date;
    private String time;
    private int fare;
    private float duration;
    private String seat;
    private String Class;
    
//    public Model() {
//        this.flightNum = flightNum;
//        this.departLoc = departLoc;
//        this.arrivalLoc = arrivalLoc;
//        this.date = date;
//        this.time = time;
//        this.seat = seat;
//        this.Class = Class;
//        this.fare = fare;
//        this.duration = duration;
//    }

    public String getFlightNum()
    {
        return flightNum;
    }
    public void setFlightNum(String flightNum)
    {
        this.flightNum=flightNum;
    }

    public String getDepartLoc()
    {
        return departLoc;
    }
    public void setDepartLoc(String departLoc)
    {
        this.departLoc=departLoc;
    }

    public String getArrivalLoc()
    {
        return arrivalLoc;
    }
    public void setArrivalLoc(String arrivalLoc)
    {
        this.arrivalLoc=arrivalLoc;
    }
    public String getDate()
    {
        return date;
    }
    public void setDate(String date)
    {
        this.date=date;
    }
    
    public String getTime()
    {
        return time;
    }
    public void setTime(String time)
    {
        this.time=time;
    }

    public int getFare()
    {
        return fare;
    }
    public void setFare(int fare)
    {
        this.fare=fare;
    }
    
    public String getclass()
    {
        return Class;
    }
    public void setClass(String Class)
    {
        this.Class= Class;
    }

    public float getDuration()
    {
        return duration;
    }
    public void setDuration(float duration)
    {
        this.duration=duration;
    }
    
    public String getseat()
    {
        return seat;
    }
    public void setseat(String seat)
    {
        this.seat= seat;
    }
    
	@Override
	public String toString() {
		return "Model [flightNum=" + flightNum + ", departLoc=" + departLoc + ", arrivalLoc=" + arrivalLoc + ", date="
				+ date + ", time=" + time + ", fare=" + fare + ", duration=" + duration + ", seat=" + seat + ", Class="
				+ Class + "]";
	}

}
