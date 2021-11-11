package edu.vassar.cmpu.test.view.calendarScreen;
import edu.vassar.cmpu.test.domain.Calendar;

import java.util.Date;


public interface ICalendarScreenView{

    interface Listener{
        void onAddEvent();
        void onSetDate(Date date);
    }

    void updateDisplay(Calendar calendar);

}

