package edu.vassar.cmpu.test.view.housemateListScreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import edu.vassar.cmpu.test.databinding.FragmentHousematesBinding;
import edu.vassar.cmpu.test.domain.Calendar;
import edu.vassar.cmpu.test.domain.Housemate;

public class HousemateListScreenFragment extends Fragment implements IHousemateListScreenFragment {
    private Listener listener;
    private FragmentHousematesBinding binding;
    public HousemateListScreenFragment(){

    }

    public HousemateListScreenFragment(Listener listener) {
        this.listener = listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentHousematesBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.binding.previousOnHousemateListScreen.setOnClickListener((View clickedView) ->{
            this.listener.onPreviousOnHousemateListScreen();
        });

        this.binding.addHousemateButton.setOnClickListener((View clickedView) -> {
            this.listener.onAddHousemateOnHousemateListScreen();
        });

        this.binding.DebtButton.setOnClickListener((View viewClicked) -> {
            this.listener.onDebtScreenButton();
        });
    }

    @Override
    public void updateDisplay(ArrayList<Housemate> housemates) {
        String names= "";
        for (Housemate name: housemates){
           names= names + name.toString() + "\n" ;
       }
        this.binding.housemates.setText(names);

    }
}
