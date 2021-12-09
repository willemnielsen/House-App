package edu.vassar.cmpu.test.domain;
import java.io.Serializable;
import java.util.Date;

/**
 * Recurrence class, which stores the recurrence start date, recurrence end date, and recurrence
 * frequency for an event (Once, Daily, Weekly).
 */
public class Recurrence implements Serializable {
    private String frequency;
    private Date endDate;
    private Date startDate;

    public Recurrence() {
        this.frequency = "Once";
        this.startDate = new Date();
        this.endDate = new Date();
    }

    /**
     * Creates a recurrence object. Instantiates the startDate, endDate, and frequency attributes.
     * Uses the input parameters for attribute values.
     * @param frequency         frequency of recurrence to be set
     * @param startDate         start date of recurrence to be set
     * @param endDate           end date of recurrence to be set
     */
    public Recurrence(String frequency, Date startDate, Date endDate) {
        this.frequency = frequency;
        this.startDate = startDate;
        this.endDate = endDate;

    }

    public String getFrequency() {
        return frequency;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
