package edu.vassar.cmpu.test.view.addEventView;

import java.sql.Time;
import java.util.Date;

import edu.vassar.cmpu.test.domain.Calendar;

public interface IAddEventView {
    interface Listener{
        void onAddedEvent(String name, Date date, Time startTime, Time endTime, IAddEventView addEventView);
        void onPreviousInAddEventFragment();
    }

    void updateDisplay(Calendar calendar);
}
