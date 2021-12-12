package edu.vassar.cmpu.test.view.addEventView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import edu.vassar.cmpu.test.R;
import edu.vassar.cmpu.test.databinding.FragmentAddEventBinding;
import edu.vassar.cmpu.test.domain.Calendar;
import edu.vassar.cmpu.test.domain.Housemate;
import edu.vassar.cmpu.test.view.addItemView.AddItemFragment;


public class AddEventFragment extends Fragment implements IAddEventView,
        AdapterView.OnItemSelectedListener {
    private FragmentAddEventBinding binding;
    private Listener listener;

    public AddEventFragment(Listener listener){ this.listener = listener; }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentAddEventBinding.inflate(inflater);
        Spinner spinnerEyyyy = (Spinner) this.binding.endDateYyyy;
        Spinner spinnerH = (Spinner) this.binding.sthour;
        Spinner spinnerM = (Spinner) this.binding.stmin;
        Spinner spinnerAP = (Spinner) this.binding.ampm;
        Spinner spinnerH2 = (Spinner) this.binding.ethour;
        Spinner spinnerM2 = (Spinner) this.binding.etmin;
        Spinner spinnerAP2 = (Spinner) this.binding.ampm2;
        Spinner spinnerR = (Spinner) this.binding.recurrence;
        Spinner spinnerEdd = (Spinner) this.binding.endDateDd;
        Spinner spinnerEmm = (Spinner) this.binding.endDateMm;
        List<String> days = new ArrayList<>();
        List<String> months = new ArrayList<>();
        List<String> years = new ArrayList<>();
        List<String> hours = new ArrayList<>();
        List<String> mins = new ArrayList<>();
        List<String> ap = new ArrayList<>();
        ap.add("AM");
        ap.add("PM");
        List<String> rec = new ArrayList<>();
        rec.add("Once");
        rec.add("Weekly");
        rec.add("Daily");
        mins.add("00");
        for(int i = 1; i<60; i++) {
            String time = "";
            if(i<13){
                time += String.format("%02d", i);
                hours.add(time);
            }
            else{
                time += i;
            }
            mins.add(time);
        }
        for(int i = 1; i<32; i++){
            String time = "";
            time += i;
            if(i<13){
                months.add(time);
                days.add(time);
            }
            else{
                days.add(time);
            }
        }
        for(int i = 2021; i<2030; i++){
            String time = "";
            time += i;
            years.add(time);
        }
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterH = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, hours);
        ArrayAdapter<String> dataAdapterM = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, mins);
        ArrayAdapter<String> dataAdapterAP = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, ap);
        ArrayAdapter<String> dataAdapterR = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, rec);
        ArrayAdapter<String> dataAdapterEdd = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, days);
        ArrayAdapter<String> dataAdapterEmm = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, months);
        ArrayAdapter<String> dataAdapterEyyyy = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, years);

        // Drop down layout style - list view with radio button
        dataAdapterH.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapterM.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapterAP.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapterR.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapterEdd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapterEmm.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapterEyyyy.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerEyyyy.setAdapter(dataAdapterEyyyy);
        spinnerH.setAdapter(dataAdapterH);
        spinnerM.setAdapter(dataAdapterM);
        spinnerAP.setAdapter(dataAdapterAP);
        spinnerH2.setAdapter(dataAdapterH);
        spinnerM2.setAdapter(dataAdapterM);
        spinnerAP2.setAdapter(dataAdapterAP);
        spinnerR.setAdapter(dataAdapterR);
        spinnerEdd.setAdapter(dataAdapterEdd);
        spinnerEmm.setAdapter(dataAdapterEmm);

        spinnerH.setOnItemSelectedListener(this);
        spinnerM.setOnItemSelectedListener(this);
        spinnerAP.setOnItemSelectedListener(this);
        spinnerH2.setOnItemSelectedListener(this);
        spinnerM2.setOnItemSelectedListener(this);
        spinnerAP2.setOnItemSelectedListener(this);
        spinnerR.setOnItemSelectedListener(this);
        spinnerEdd.setOnItemSelectedListener(this);
        spinnerEmm.setOnItemSelectedListener(this);
        spinnerEyyyy.setOnItemSelectedListener(this);
        return this.binding.getRoot();
    }
    /*
    @Override
    public void onGetEndDate(){
        this.binding.setEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                onCreateEndDateDialog();
            }
        }
        this.binding.setEndDate.setText(););
    }


    @Override
    public Date onCreateEndDateDialog() {
        Date date = new Date();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.end_date_popup, null))
                // Add action buttons
                .setPositiveButton("done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }

                    Spinner spinnerR = (Spinner) this.binding.recurrence;


                });
        return date;
    }
    */

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.binding.calendarView2.setOnDateChangeListener((CalendarView clickedView, int year,
                                                            int month, int dayOfMonth) ->{
            binding.typeMonth.setText(""+(month+1));
            binding.typeDay.setText(""+dayOfMonth);
            binding.typeYear.setText(""+year);
        });

        this.binding.back.setOnClickListener((View clickedView) -> {
            this.listener.onPreviousInAddEventFragment();
        });

    }
    @Override
    public void getAddedHouseMates(List<Housemate> housemates) {
        this.binding.addEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get the item name

                Editable nameEditable = binding.typeEventName.getText();
                String name = nameEditable.toString();
                if (name.isEmpty()) {binding.typeEventName.setError("Please Enter an Event Name");}

                if (binding.typeYear.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Please Select a Date.", Toast.LENGTH_SHORT).show();}

                try {
                    int sth = Integer.parseInt(binding.hourText.getText().toString());
                    int stm = Integer.parseInt(binding.minText.getText().toString());
                    Time st = new Time(sth - 1, stm, 00);
                    if (binding.ampmText.getText().toString().equals("PM")) {
                        st.setHours(st.getHours() + 12);
                    }

                    int eth = Integer.parseInt(binding.hourText2.getText().toString());
                    int etm = Integer.parseInt(binding.minText2.getText().toString());
                    Time et = new Time(eth - 1, etm, 00);
                    if (binding.ampmText2.getText().toString().equals("PM")) {
                        et.setHours(et.getHours() + 12);
                    }
                    int year = Integer.parseInt(binding.typeYear.getText().toString());
                    int month = Integer.parseInt(binding.typeMonth.getText().toString());
                    int day = Integer.parseInt(binding.typeDay.getText().toString());
                    Date date = new Date(year - 1900, month - 1, day);
                    int edYear = Integer.parseInt(binding.EDYearText.getText().toString());
                    int edMonth = Integer.parseInt(binding.EDMonthText.getText().toString());
                    int edDay = Integer.parseInt(binding.EDDayText.getText().toString());
                    Date endDate = new Date(edYear - 1900, edMonth - 1, edDay);
                    String recur = binding.recText.getText().toString();
                    if (!name.isEmpty()) CreateDialog(housemates, name, date, st, et, recur, endDate);


                } catch (NumberFormatException e) {

                }

                nameEditable.clear();
                String reseth = "01";
                String resetm = "00";
                String resetap = "AM";
                String resetr = "Once";
                String reset = "";
                binding.hourText.setText(reseth);
                binding.sthour.setSelection(0);
                binding.minText.setText(resetm);
                binding.stmin.setSelection(0);
                binding.hourText2.setText(reseth);
                binding.ethour.setSelection(0);
                binding.minText2.setText(resetm);
                binding.etmin.setSelection(0);
                binding.ampmText.setText(resetap);
                binding.ampm.setSelection(0);
                binding.ampmText2.setText(resetap);
                binding.ampm2.setSelection(0);
                binding.recText.setText(resetr);
                binding.recurrence.setSelection(0);
                binding.typeMonth.setText(reset);
                binding.typeDay.setText(reset);
                binding.typeYear.setText(reset);
                binding.EDMonthText.setText(reset);
                binding.EDDayText.setText(reset);
                binding.EDYearText.setText(reset);
            }
        });
    }

    private List<Housemate> CreateDialog(List<Housemate> housemates, String name,
                                              Date date, Time st, Time et, String recur, Date endDate){
        List<String> selectedHM = new ArrayList<>();
        List<Housemate> interestedHM = new ArrayList<>();
        String[] names = new String[housemates.size()];
        for(int i = 0 ; i < names.length; i++){
            names[i] = housemates.get(i).getName();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Add Housemates to Event")
                .setMultiChoiceItems(names, null, (dialog, which, isChecked) -> {


                    if(isChecked){
                        selectedHM.add(names[which]);
                        interestedHM.add(housemates.get(which));
                    }
                    else if (selectedHM.contains(names[which])){
                        selectedHM.remove(names[which]);
                        interestedHM.remove(housemates.get(which));
                    }
        }).setPositiveButton("Done", (dialog, which) -> {
            if (selectedHM.isEmpty()){
                CreateDialog(housemates, name, date, st, et, recur, endDate);
                String data = "Please Select Housemates to Add to the Event.";
                Toast.makeText(getActivity(), data, Toast.LENGTH_SHORT).show();
            } else{
                String data = "Housemates Added to Event:";
                for (String n : selectedHM){
                    data = data + " " + n;
                }
                AddEventFragment.this.listener.onAddedEvent(name, date, st, et, interestedHM, recur,
                        endDate, AddEventFragment.this);
                Toast.makeText(getActivity(), data, Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("Cancel", (dialog, which) -> {
            String data = "Event Cancelled.";
            Toast.makeText(getActivity(), data, Toast.LENGTH_SHORT).show();
        });

        builder.create();
        builder.show();

        return interestedHM;
    }

    public void onAddedEvent(String name, Date date, Time st, Time et,
                             ArrayList<Housemate> interestedHMs, String rec, Date endDate) {
        this.listener.onAddedEvent(name, date, st, et, interestedHMs, rec, endDate,this);
    }


    @Override
    public void updateDisplay(Calendar calendar) {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView.getId() == this.binding.sthour.getId()) {
            this.binding.hourText.setText(this.binding.sthour.getSelectedItem().toString());
        } else if (adapterView.getId() == this.binding.endDateYyyy.getId()) {
            this.binding.EDYearText.setText(this.binding.endDateYyyy.getSelectedItem().toString());
        } else if (adapterView.getId() == this.binding.stmin.getId()) {
            this.binding.minText.setText(this.binding.stmin.getSelectedItem().toString());
        } else if (adapterView.getId() == this.binding.ampm.getId()) {
            this.binding.ampmText.setText(this.binding.ampm.getSelectedItem().toString());
        } else if (adapterView.getId() == this.binding.ethour.getId()) {
            this.binding.hourText2.setText(this.binding.ethour.getSelectedItem().toString());
        } else if (adapterView.getId() == this.binding.etmin.getId()) {
            this.binding.minText2.setText(this.binding.etmin.getSelectedItem().toString());
        } else if (adapterView.getId() == this.binding.ampm2.getId()) {
            this.binding.ampmText2.setText(this.binding.ampm2.getSelectedItem().toString());
        } else if (adapterView.getId() == this.binding.recurrence.getId()) {
            this.binding.recText.setText(this.binding.recurrence.getSelectedItem().toString());
        } else if (adapterView.getId() == this.binding.endDateDd.getId()) {
            this.binding.EDDayText.setText(this.binding.endDateDd.getSelectedItem().toString());
        } else if (adapterView.getId() == this.binding.endDateMm.getId()) {
            this.binding.EDMonthText.setText(this.binding.endDateMm.getSelectedItem().toString());
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
