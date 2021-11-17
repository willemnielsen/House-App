package edu.vassar.cmpu.test.view.addEventView;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

import edu.vassar.cmpu.test.domain.Calendar;
import edu.vassar.cmpu.test.domain.Housemate;

public interface IAddEventView {
    interface Listener{
        void onAddedEvent(String name, Date date, Time startTime, Time endTime,
                          ArrayList<Housemate> interestedHMs, String rec,
                          IAddEventView addEventView);
        void onPreviousInAddEventFragment();
    }
    void getAddedHouseMates(ArrayList<Housemate> housemates);
    void updateDisplay(Calendar calendar);
}
