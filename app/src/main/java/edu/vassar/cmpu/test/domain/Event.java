package edu.vassar.cmpu.test.domain;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Event implements Serializable {
    private String name;
    private Date date;
    private Time startTime;
    private Time endTime;
    private List<Housemate> housemates;

    public Event() {
        this.name = "Event";
        this.date = new Date();
        this.startTime = new Time(0);
        this.endTime = new Time(0);
        this.housemates = new ArrayList<>();
    }

    public Event(String name, Date date, Time startTime, Time endTime,
                 List<Housemate> housemates) {
        this.name = name;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.housemates = housemates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = new Time(startTime.getTime());
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = new Time(endTime.getTime());
    }

    public List<Housemate> getHousemates() {
        return housemates;
    }

    public void setHousemates(List<Housemate> housemates) {
        this.housemates = housemates;
    }

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
