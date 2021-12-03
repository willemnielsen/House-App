package edu.vassar.cmpu.test.domain;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Event {
    private String name;
    private Date date;
    private Time startTime;
    private Time endTime;
    private List<Housemate> housemates;

    public Event(String name, Date date, Time startTime, Time endTime,
                 List<Housemate> housemates) {
        this.name = name;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.housemates = housemates;
    }

    public List<Housemate> getHousemates() {return housemates;}
    public String getName() {return name;}
    public Date getDate() {return date;}
    public Time getStartTime() {return startTime;}
    public Time getEndTime() {return endTime;}
    public String toString() {
        String print = "";
        print += name + " from ";
        if (startTime.getHours() == 23) {
            print += 12 + ":" + String.format("%02d", startTime.getMinutes()) + "AM to ";
        } else if (startTime.getHours() < 12) {
            print += (startTime.getHours()+1) + ":" + String.format("%02d", startTime.getMinutes())
                    + "AM to ";
        } else print += (startTime.getHours()-11) + ":" +
                String.format("%02d", startTime.getMinutes()) + "PM to ";
        if (endTime.getHours() == 23) {
            print += 12 + ":" + String.format("%02d", endTime.getMinutes()) + "AM.";
        } else if (endTime.getHours() < 12) {
            print += (endTime.getHours()+1) + ":" + String.format("%02d", endTime.getMinutes())
                    + "AM. \n";
        } else print += (endTime.getHours()-11) + ":" + String.format("%02d", endTime.getMinutes())
                + "PM. \n";
            return print;
    }
}
