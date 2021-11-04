package edu.vassar.cmpu.test.domain;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class Event {
    private String name;
    private Date date;
    private Time time;
    private ArrayList<Housemate> housemates;

    public Event(String name, Date date, Time time,  ArrayList<Housemate> housemates) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.housemates = housemates;
    }

    public ArrayList<Housemate> gethousemates() {return housemates;}
    public String getname() {return name;}
    public Date getdate() {return date;}
    public Time gettime() {return time;}
}
