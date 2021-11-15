package edu.vassar.cmpu.test.view.addEventView;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import edu.vassar.cmpu.test.databinding.FragmentAddEventBinding;
import edu.vassar.cmpu.test.domain.Calendar;
import edu.vassar.cmpu.test.domain.Housemate;


public class AddEventFragment extends Fragment implements IAddEventView, AdapterView.OnItemSelectedListener {
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
        Spinner spinnerH = (Spinner) this.binding.sthour;
        Spinner spinnerM = (Spinner) this.binding.stmin;
        Spinner spinnerAP = (Spinner) this.binding.ampm;
        Spinner spinnerH2 = (Spinner) this.binding.ethour;
        Spinner spinnerM2 = (Spinner) this.binding.etmin;
        Spinner spinnerAP2 = (Spinner) this.binding.ampm2;
        Spinner spinnerR = (Spinner) this.binding.recurrence;
        List<String> hours = new ArrayList<String>();
        List<String> mins = new ArrayList<String>();
        List<String> ap = new ArrayList<String>();
        ap.add("AM");
        ap.add("PM");
        List<String> rec = new ArrayList<String>();
        rec.add("Once");
        rec.add("Weekly");
        rec.add("Daily");
        for(int i = 0; i<60; i++) {
            if(i<9){
                String time = "0";
                String time1 = time + (i+1);
                time += i;
                hours.add(time1);
                mins.add(time);
            }
            else if(i<13){
                String time = "";
                time += i;
                hours.add(time);
                mins.add(time);
            }
            else{
                String time = "";
                time += i;
                mins.add(time);
            }
        }
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterH = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, hours);
        ArrayAdapter<String> dataAdapterM = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, mins);
        ArrayAdapter<String> dataAdapterAP = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, ap);
        ArrayAdapter<String> dataAdapterR = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, rec);

        // Drop down layout style - list view with radio button
        dataAdapterH.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapterM.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapterAP.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapterR.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerH.setAdapter(dataAdapterH);
        spinnerM.setAdapter(dataAdapterM);
        spinnerAP.setAdapter(dataAdapterAP);
        spinnerH2.setAdapter(dataAdapterH);
        spinnerM2.setAdapter(dataAdapterM);
        spinnerAP2.setAdapter(dataAdapterAP);
        spinnerR.setAdapter(dataAdapterR);

        spinnerH.setOnItemSelectedListener(this);
        spinnerM.setOnItemSelectedListener(this);
        spinnerAP.setOnItemSelectedListener(this);
        spinnerH2.setOnItemSelectedListener(this);
        spinnerM2.setOnItemSelectedListener(this);
        spinnerAP2.setOnItemSelectedListener(this);
        spinnerR.setOnItemSelectedListener(this);
        return this.binding.getRoot();
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.binding.calendarView2.setOnDateChangeListener((CalendarView clickedView, int year, int month, int dayOfMonth) ->{
            binding.typeMonth.setText(""+(month+1));
            binding.typeDay.setText(""+dayOfMonth);
            binding.typeYear.setText(""+year);
        });

        this.binding.addEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get the item name
                Editable nameEditable = binding.typeEventName.getText();
                String name = nameEditable.toString();

                int sth = Integer.parseInt(binding.hourText.getText().toString());
                int stm = Integer.parseInt(binding.minText.getText().toString());
                Time st = new Time(sth-1, stm, 00);
                if (binding.ampmText.getText().toString().equals("PM")){
                    st.setHours(st.getHours()+12);
                }

                int eth = Integer.parseInt(binding.hourText2.getText().toString());
                int etm = Integer.parseInt(binding.minText2.getText().toString());
                Time et = new Time(eth-1, etm, 00);
                if (binding.ampmText2.getText().toString().equals("PM")){
                    et.setHours(et.getHours()+12);
                }

                int year = Integer.parseInt(binding.typeYear.getText().toString());
                int month = Integer.parseInt(binding.typeMonth.getText().toString());
                int day = Integer.parseInt(binding.typeDay.getText().toString());
                Date date = new Date(year-1900, month-1, day);

                String recur = binding.recText.getText().toString();


                onAddedEvent(name, date, st, et, recur);

                nameEditable.clear();
                String reseth = "01";
                String resetm = "00";
                String resetap = "AM";
                String resetr = "Once";
                String reset = "";
                binding.hourText.setText(reseth);
                binding.minText.setText(resetm);
                binding.hourText2.setText(reseth);
                binding.minText2.setText(resetm);
                binding.ampmText.setText(resetap);
                binding.ampmText2.setText(resetap);
                binding.typeMonth.setText(reset);
                binding.typeDay.setText(reset);
                binding.typeYear.setText(reset);
                binding.recText.setText(resetr);

            }
        });

        this.binding.back.setOnClickListener((View clickedView) -> {
            this.listener.onPreviousInAddEventFragment();
        });

    }

    public void onAddedEvent(String name, Date date, Time st, Time et, String rec) {
        this.listener.onAddedEvent(name, date, st, et, rec,this);
    }


    @Override
    public void updateDisplay(Calendar calendar) {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(adapterView.getId() == this.binding.sthour.getId()){
            this.binding.hourText.setText(this.binding.sthour.getSelectedItem().toString());
        }
        else if(adapterView.getId() == this.binding.stmin.getId()){
            this.binding.minText.setText(this.binding.stmin.getSelectedItem().toString());
        }
        else if(adapterView.getId() == this.binding.ampm.getId()){
            this.binding.ampmText.setText(this.binding.ampm.getSelectedItem().toString());
        }
        else if(adapterView.getId() == this.binding.ethour.getId()){
            this.binding.hourText2.setText(this.binding.ethour.getSelectedItem().toString());
        }
        else if(adapterView.getId() == this.binding.etmin.getId()){
            this.binding.minText2.setText(this.binding.etmin.getSelectedItem().toString());
        }
        else if(adapterView.getId() == this.binding.ampm2.getId()){
            this.binding.ampmText2.setText(this.binding.ampm2.getSelectedItem().toString());
        }
        else{
            this.binding.recText.setText(this.binding.recurrence.getSelectedItem().toString());
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
