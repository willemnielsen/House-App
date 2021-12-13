package edu.vassar.cmpu.test.domain;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Event class for events of the house. This class contains attributes <code>name</code>,
 * <code>date</code>, <code>startTime</code>, <code>endTime</code>, <code>housemates</code>
 * (interested housemates)
 */
public class Event implements Serializable {
    private String name;
    private Date date;
    private Time startTime;
    private Time endTime;
    private List<Housemate> housemates;

    /**
     * Creates an event object. By default, name is "Event", date is today, startTime and endTime
     * are Unix epoch, housemates is an empty array list.
     */
    public Event() {
        this.name = "Event";
        this.date = new Date();
        this.startTime = new Time(0);
        this.endTime = new Time(0);
        this.housemates = new ArrayList<>();
    }

    /**
     * Creates an event object with the given parameters
     * @param name          event name
     * @param date          event date
     * @param startTime     event starTime
     * @param endTime       event endTime
     * @param housemates    list of interested housemates
     */
    public Event(String name, Date date, Time startTime, Time endTime,
                 List<Housemate> housemates) {
        this.name = name;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.housemates = housemates;
    }

    /**
     * Returns the name of the event the user added
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the name of the event the user added
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the date of the event added
     */
    public Date getDate() {
        return date;
    }

    /**
     * Setter for the date of the event added
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Returns the start time of the event added
     */
    public Time getStartTime() {
        return startTime;
    }

    /**
     * Setter for the start time of the event added
     */
    public void setStartTime(Date startTime) {
        this.startTime = new Time(startTime.getTime());
    }

    /**
     * Returns the end time of the event added
     */
    public Time getEndTime() {
        return endTime;
    }

    /**
     * Setter for the end time of the event added
     */
    public void setEndTime(Date endTime) {
        this.endTime = new Time(endTime.getTime());
    }

    /**
     * Returns the housemates that are associated to the event
     */
    public List<Housemate> getHousemates() {
        return housemates;
    }

    /**
     * Setter for the housemates that are associated to the event
     */
    public void setHousemates(List<Housemate> housemates) {
        this.housemates = housemates;
    }

    /**
     * Converts the event to a string.
     * @return String with event name, start time and end time, and housemates.
     */
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
