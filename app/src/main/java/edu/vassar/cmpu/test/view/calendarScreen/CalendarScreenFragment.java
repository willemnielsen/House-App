package edu.vassar.cmpu.test.view.calendarScreen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.vassar.cmpu.test.databinding.FragmentCalendarMonthBinding;
import edu.vassar.cmpu.test.domain.Calendar;

import java.util.Date;

public class CalendarScreenFragment extends Fragment implements ICalendarScreenView{
    private ICalendarScreenView.Listener listener;
    private FragmentCalendarMonthBinding binding;


    public CalendarScreenFragment(ICalendarScreenView.Listener listener) {
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
        this.binding.calendarView.setOnClickListener((View clickedView) ->{
            Date date = new Date(this.binding.calendarView.getDate());
            this.listener.onSetDate(date, this);
        });

    }


    @Override
    public void updateDisplay(Calendar calendar) {
        this.binding.eventsList.setText(calendar.toString(calendar.getCurrentDate()));
    }
}
