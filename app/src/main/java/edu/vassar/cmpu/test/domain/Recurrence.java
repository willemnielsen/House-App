package edu.vassar.cmpu.test.domain;
import java.util.Date;


public class Recurrence {
    public final String frequency;
    public final Date endDate;
    public final Date startDate;

    public Recurrence(String frequency, Date startDate, Date endDate) {
        this.frequency = frequency;
        this.startDate = startDate;
        this.endDate = endDate;

    }
}
