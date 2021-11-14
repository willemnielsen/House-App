package edu.vassar.cmpu.test.view.addEventView;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import java.sql.Time;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;


import edu.vassar.cmpu.test.databinding.FragmentAddEventBinding;
import edu.vassar.cmpu.test.domain.Calendar;

public class AddEventFragment extends Fragment implements IAddEventView {
    private FragmentAddEventBinding binding;
    private Listener listener;

    public AddEventFragment(Listener listener){ this.listener = listener; }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentAddEventBinding.inflate(inflater);
        return this.binding.getRoot();
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.binding.addEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get the item name
                Editable nameEditable = binding.typeEventName.getText();
                String name = nameEditable.toString();

                Editable sthourEditable = binding.typeSthour.getText();
                int sth = Integer.parseInt(sthourEditable.toString());
                Editable stminEditable = binding.typeStmin.getText();
                int stm = Integer.parseInt(stminEditable.toString());
                Time st = new Time(sth, stm, 00);

                Editable ethourEditable = binding.typeEthour.getText();
                int eth = Integer.parseInt(ethourEditable.toString());
                Editable etminEditable = binding.typeEtmin.getText();
                int etm = Integer.parseInt(etminEditable.toString());
                Time et = new Time(eth, etm, 00);

                Editable monthEditable = binding.typeMonth.getText();
                int month = Integer.parseInt(monthEditable.toString());
                Editable dayEditable = binding.typeDay.getText();
                int day = Integer.parseInt(dayEditable.toString());
                Editable yearEditable = binding.typeYear.getText();
                int year = Integer.parseInt(yearEditable.toString());
                Date date = new Date(year, month, day);

                onAddedEvent(name, date, st, et);

                nameEditable.clear();
                ethourEditable.clear();
                etminEditable.clear();
                sthourEditable.clear();
                stminEditable.clear();
                monthEditable.clear();
                dayEditable.clear();
                yearEditable.clear();
            }
        });

        this.binding.back.setOnClickListener((View clickedView) -> {
            this.listener.onPreviousInAddEventFragment();
        });
    }
    public void onAddedEvent(String name, Date date, Time startTime, Time endTime){
        this.listener.onAddedEvent(name, date, startTime, endTime,this);
    }


    @Override
    public void updateDisplay(Calendar calendar) {

    }
}
