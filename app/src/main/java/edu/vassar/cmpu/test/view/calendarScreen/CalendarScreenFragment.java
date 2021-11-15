package edu.vassar.cmpu.test.view.calendarScreen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import edu.vassar.cmpu.test.databinding.FragmentCalendarMonthBinding;
import edu.vassar.cmpu.test.domain.Calendar;

import java.util.Date;

public class CalendarScreenFragment extends Fragment implements ICalendarScreenView{
    private Listener listener;
    private FragmentCalendarMonthBinding binding;


    public CalendarScreenFragment(Listener listener) {
        this.listener = listener;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentCalendarMonthBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.binding.addEventButton.setOnClickListener((View clickedView) -> {
            this.listener.onAddEvent();
        });

        this.binding.calendarView.setOnDateChangeListener((CalendarView clickedView, int year, int month, int dayOfMonth) ->{
            Date date = new Date(year-1900, month, dayOfMonth);
            this.listener.onSetDate(date, this);
        });

        this.binding.previousOnCalendarScreen.setOnClickListener((View clickedView) ->{
            this.listener.onPreviousOnCalendarScreen();
        });
    }


    @Override
    public void updateDisplay(Calendar calendar) {
        this.binding.eventsList.setText(calendar.toString());
    }
}
