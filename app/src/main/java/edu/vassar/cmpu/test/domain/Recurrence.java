package edu.vassar.cmpu.test.domain;
import java.util.Date;


public class Recurrence {
    public final String frequency;
    public final Date endDate;
    public Recurrence(String frequency, Date endDate) {
        this.frequency = frequency;
        this.endDate = endDate;
    }
}
