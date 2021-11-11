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
    private IAddEventView.Listener listener;

    public AddEventFragment(IAddEventView.Listener listener){ this.listener = listener; }

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

                Editable stEditable = binding.typeSt.getText();
                Time st = Time.valueOf(stEditable.toString());

                Editable etEditable = binding.typeEt.getText();
                Time et = Time.valueOf(etEditable.toString());

                Editable dateEditable = binding.typeDate.getText();
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
                try {
                   Date date = dateFormat.parse(dateEditable.toString());
                    onAddedEvent(name, date, st, et);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                nameEditable.clear();
                dateEditable.clear();
                stEditable.clear();
                etEditable.clear();
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
