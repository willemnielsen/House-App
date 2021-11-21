package edu.vassar.cmpu.test.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

class CalendarTest {

    /**
     * Tests calendar toString() by comparing expected string of a given calendar to the toString()
     * output of that calendar
     */
    @Test
    void testToString() {
        ArrayList<Event> events = new ArrayList<Event>();
        String name = "Basketball";
        String name2 = "Hockey";
        Time st = new Time(30600000L);
        Time et = new Time(34200000L);
        Date date1 = new Date(1637298000000L);
        ArrayList<Housemate> hms = new ArrayList<Housemate>();
        Housemate willy = new Housemate("willy", "555555");
        Housemate tom = new Housemate("tom", "666666");
        hms.add(willy);
        hms.add(tom);
        Recurrence rec = new Recurrence("n", date1, date1);
        Calendar cal = new Calendar();
        cal.setCurrentDate(date1);
        cal.addEvent(name, date1, st, et, hms, rec);
        cal.addEvent(name2, date1, st, et, hms, rec);
        String actual = cal.toString();
        assertEquals("" + date1 + "\n" + "willy, tom has Basketball from 4:30AM to 5:30AM. \n\n" +
                "willy, tom has Hockey from 4:30AM to 5:30AM. \n\n", actual);
    }

    /**
     * Tests addEvent() method by comparing expected and actual calendars. Expected value obtained
     * by adding 3 events on consecutive days using ArrayList.add() method. Obtains actual value by
     * calling addEvent with "Daily" recurrence for those three days.
     */
    @Test
    void addEvent() {
        ArrayList<Event> events = new ArrayList<Event>();
        String name = "Basketball";
        Time st = new Time(8, 30, 0);
        Time et = new Time(9, 30, 0);
        Date date1 = new Date(1637298000000L);
        Date date2 = new Date(1637384400000L);
        Date date3 = new Date(1637470800000L);
        Date date4 = new Date(1637557200000L);
        ArrayList<Housemate> hms = new ArrayList<Housemate>();
        Housemate willy = new Housemate("willy", "555555");
        Housemate tom = new Housemate("tom", "666666");
        hms.add(willy);
        hms.add(tom);
        Recurrence rec = new Recurrence("Daily", date1, date4);
        Event event1 = new Event(name, date1, st, et, hms);
        Event event2 = new Event(name, date2, st, et, hms);
        Event event3 = new Event(name, date3, st, et, hms);
        events.add(event1);
        events.add(event2);
        events.add(event3);
        Date cur = new Date();
        Calendar calExpected = new Calendar();
        calExpected.setEvents(events);
        Calendar calActual = new Calendar();
        calActual.addEvent(name, date1, st, et, hms, rec);
        assertEquals(calExpected.toString(), calActual.toString(), "test addEvent");


    }
}