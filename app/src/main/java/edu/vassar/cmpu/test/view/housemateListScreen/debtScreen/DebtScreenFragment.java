package edu.vassar.cmpu.test.view.housemateListScreen.debtScreen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.vassar.cmpu.test.R;
import edu.vassar.cmpu.test.databinding.FragmentDebtScreenBinding;


public class DebtScreenFragment extends Fragment implements IDebtScreenFragment{

    FragmentDebtScreenBinding binding;
    Listener listener;

    public DebtScreenFragment(Listener listener) {
        this.listener = listener;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDebtScreenBinding.inflate(inflater);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.binding.previousButtonOnDebtScreen.setOnClickListener((View clickedView) -> {
            this.listener.onPreviousOnDebtScreen();
        });
    }

    @Override
    public void updateDisplay(String debt) {
        this.binding.debtText.setText(debt);
    }
}