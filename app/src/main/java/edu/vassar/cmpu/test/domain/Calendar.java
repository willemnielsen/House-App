package edu.vassar.cmpu.test.domain;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
/**
 * Calendar class used to store the events of a particular house. The events attribute stores the
 * list of events on the calendar. The currentDate attribute is the date on the calendar selected by
 * the user.
*/
public class Calendar implements Serializable {
    private List<Event> events;
    private Date currentDate;

    /**
     * Creates a calendar object, which sets <code>events</code> to an empty ArrayList and
     * <code>currentDate</code> to today.
     */
    public Calendar() {
        this.events = new ArrayList<Event>();
        this.currentDate = new Date();
    }

    /**
     * This method adds an event to the calendar. If the recurrence frequency is daily or weekly it
     * adds multiple different instances of events to the calendar on the recurring dates.
     * @param name              name of event
     * @param date              first date that event occurs
     * @param startTime         start time of event
     * @param endTime           end time of event
     * @param housemates        list of housemates participating in event
     * @param recurrence        type of recurrence associated with this event
     * @return <code>true</code>
     */
    public boolean addEvent(String name, Date date, Time startTime, Time endTime,
                            List<Housemate> housemates, Recurrence recurrence) {
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

    public void addThisEvent(Event event){
        this.events.add(event);
    }

    public void remove(Event event) {
        events.remove(event);
    }

    public void setEvents(List<Event> events){this.events = events;}
    public List<Event> getEvents(){return this.events;}

    public Event getThisEvent(Event event) {
        return events.get(events.indexOf(event));
    }

    public boolean setCurrentDate(Date date) {
        this.currentDate = date;
        return true;
    }

    public static void sort(List<Event> eventlist)
    {
        eventlist.sort(Comparator.comparing(Event::getStartTime));
    }

    /**
     * This method converts the calendar to a string
     * @return <code>list</code>, a string of all events on the calendar
     */
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

