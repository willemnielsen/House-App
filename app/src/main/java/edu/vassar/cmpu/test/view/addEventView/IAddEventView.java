package edu.vassar.cmpu.test.view.addEventView;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.vassar.cmpu.test.domain.Calendar;
import edu.vassar.cmpu.test.domain.Housemate;

public interface IAddEventView {
    interface Listener{
        void onAddedEvent(String name, Date date, Time startTime, Time endTime,
                          List<Housemate> interestedHMs, String rec, Date endDate,
                          IAddEventView addEventView);
        void onPreviousInAddEventFragment();
    }
    void getAddedHouseMates(List<Housemate> housemates);
    void updateDisplay(Calendar calendar);
}
