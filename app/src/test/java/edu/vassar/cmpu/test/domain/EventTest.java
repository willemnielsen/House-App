package edu.vassar.cmpu.test.domain;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
import java.sql.Time;

class EventTest {

    @org.junit.jupiter.api.Test
    void testToString() {

        Time st = new Time(30600000L);
        Time et = new Time(34200000L);
        Date date = new Date(1637298000000L);
        Event event = new Event("Soccer", date, st, et, null);
        String expectedString = "Soccer from 4:30AM to 5:30AM. \n";
        assertEquals(expectedString, event.toString(), "event toString()");
    }

}