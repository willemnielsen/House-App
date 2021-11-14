package edu.vassar.cmpu.test.domain;
import java.util.Date;


public class Recurrence {
    private final String frequency;
    private final Date endDate;
    private final Date startDate;

    public Recurrence(String frequency, Date startDate, Date endDate) {
        this.frequency = frequency;
        this.startDate = startDate;
        this.endDate = endDate;

    }
    public String getFrequency(){return this.frequency;}
    public Date getStartDate(){return this.startDate;}
    public Date getEndDate(){return this.endDate;}
}
