package edu.vassar.cmpu.test.view.transactionsScreen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.vassar.cmpu.test.R;
import edu.vassar.cmpu.test.databinding.FragmentTransactionsScreenBinding;

public class TransactionsScreenFragment extends Fragment implements ITransactionsScreenFragment{


    FragmentTransactionsScreenBinding binding;
    Listener listener;

    public TransactionsScreenFragment(Listener listener) {
        this.listener = listener;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentTransactionsScreenBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.binding.previousButtonOnTransactionsScreen.setOnClickListener((View clickedView) -> {
            this.listener.onPreviousOnTransactionsScreen();
        });
    }

    @Override
    public void updateDisplay(String transactions) {
        this.binding.transactionsText.setText(transactions);
    }
}