package edu.vassar.cmpu.test.domain;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class Event {
    private String name;
    private Date date;
    private Time startTime;
    private Time endTime;
    private ArrayList<Housemate> housemates;

    public Event(String name, Date date, Time startTime, Time endTime,  ArrayList<Housemate> housemates) {
        this.name = name;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.housemates = housemates;
    }

    public ArrayList<Housemate> getHousemates() {return housemates;}
    public String getName() {return name;}
    public Date getDate() {return date;}
    public Time getStartTime() {return startTime;}
    public Time getEndTime() {return endTime;}
    public String toString(){
        return name + " from " + startTime + " to " + endTime;
    }
}
