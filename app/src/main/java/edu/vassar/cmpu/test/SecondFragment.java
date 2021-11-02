package edu.vassar.cmpu.test;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.snackbar.Snackbar;

import edu.vassar.cmpu.test.databinding.FragmentSecondBinding;
import edu.vassar.cmpu.test.view.addItemView.AddItemView;
import edu.vassar.cmpu.test.view.addItemView.IAddItemView;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    private IAddItemView addItemsView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.addTitle.setText("WORKS");
            }
        });

        binding.previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}