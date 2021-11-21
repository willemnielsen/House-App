package edu.vassar.cmpu.test.domain;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class Calendar {
    private ArrayList<Event> events;
    private Date currentDate;

    public Calendar() {
        events = new ArrayList<Event>();
        currentDate = new Date();
    }

    public boolean addEvent(String name, Date date, Time startTime, Time endTime,
                            ArrayList<Housemate> housemates, Recurrence recurrence) {
        Event newevent;
        Date incDate = recurrence.getStartDate();
        Long longDate;
        switch (recurrence.getFrequency()) {
            case "Daily":
                while (incDate.before(recurrence.getEndDate())) {
                    if (housemates == null) {
                        newevent = new Event(name, incDate, startTime, endTime,
                                new ArrayList<Housemate>());
                    } else {
                        newevent = new Event(name, incDate, startTime, endTime, housemates);
                    }
                    events.add(newevent);
                    longDate = incDate.getTime() + 86400000L;
                    incDate = new Date(longDate);
                }
                break;
            case "Weekly":
                while (incDate.before(recurrence.getEndDate())) {
                    if (housemates == null) {
                        newevent = new Event(name, incDate, startTime, endTime,
                                new ArrayList<Housemate>());
                    } else {
                        newevent = new Event(name, incDate, startTime, endTime, housemates);
                    }
                    events.add(newevent);
                    longDate = incDate.getTime() + 604800000L;
                    incDate = new Date(longDate);
                }
                break;
            default:
                if (housemates == null) {
                    newevent = new Event(name, date, startTime, endTime,
                            new ArrayList<Housemate>());
                } else {
                    newevent = new Event(name, date, startTime, endTime, housemates);
                }
                events.add(newevent);

                sort(events);
                return true;
        }
        sort(events);
        return true;
    }


    public void remove(Event event) {
        events.remove(event);
    }

    public void setEvents(ArrayList<Event> events){this.events = events;}
    public ArrayList<Event> getEvents(){return this.events;}

    public Event getThisEvent(Event event) {
        return events.get(events.indexOf(event));
    }

    public boolean setCurrentDate(Date date) {
        this.currentDate = date;
        return true;
    }

    public static void sort(ArrayList<Event> eventlist)
    {
        eventlist.sort(Comparator.comparing(Event::getStartTime));
    }


    public String toString() {
        String list = "" + currentDate + "\n";
        for (Event event : events) {
            if (currentDate.getYear() == event.getDate().getYear() && currentDate.getMonth() ==
                    event.getDate().getMonth()
                    && currentDate.getDate() == event.getDate().getDate()) {
                for (int i = 0; i < event.getHousemates().size(); i++) {
                    if (i < event.getHousemates().size() - 1)
                        list += event.getHousemates().get(i).getName() + ", ";
                    else
                        list += event.getHousemates().get(i).getName() + " ";
                }
                list += "has " + event.toString() + "\n";
            }
        }
            return list;
        }
    }

