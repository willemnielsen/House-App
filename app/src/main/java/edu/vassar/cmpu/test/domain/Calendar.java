package edu.vassar.cmpu.test.domain;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class Calendar {
    private ArrayList<Event> events = new ArrayList<Event>();

    public Calendar() {
    }

    public boolean addEvent(String name, Date date, Time time, ArrayList<Housemate> housemates) {
        Event newevent;
        if (housemates == null) {
            newevent = new Event(name, date, time, new ArrayList<Housemate>());

        } else {
            newevent = new Event(name, date, time, housemates);
        }
        events.add(newevent);
        return true;
    }

    public void remove(Event event){events.remove(event);}

    public Event getThisEvent(Event event){return events.get(events.indexOf(event));}
}
