package edu.vassar.cmpu.test.domain;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class Calendar {
    private ArrayList<Event> events;
    private Date currentDate;

    public Calendar() {
        events = new ArrayList<Event>();
        currentDate = new Date();
    }

    public boolean addEvent(String name, Date date, Time startTime, Time endTime, ArrayList<Housemate> housemates, String recurrence) {
        Event newevent;
        if (housemates == null) {
            newevent = new Event(name, date, startTime, endTime, new ArrayList<Housemate>(), recurrence);

        } else {
            newevent = new Event(name, date, startTime, endTime, housemates, recurrence);
        }
        events.add(newevent);
        return true;
    }

    public void remove(Event event){events.remove(event);}

    public Event getThisEvent(Event event){return events.get(events.indexOf(event));}

    public boolean setCurrentDate(Date date){this.currentDate = date;
    return true;}

    public Date getCurrentDate(){return this.currentDate;}

    public String toString(){
        String list = "";
        for(Event event : events) {
            long adjustedtime = (event.getDate().getTime()+59958144000000L);
            if(this.currentDate.getTime() == adjustedtime) list += event.toString() + "\n";
        }
        return list;
    }
}
