package edu.vassar.cmpu.test.view.addItemView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import edu.vassar.cmpu.test.databinding.ActivityMainBinding;

public class AddItemView implements IAddItemView {

    private Listener listener;
    private ActivityMainBinding binding;

    public AddItemView(Context context, Listener listener){
        binding = ActivityMainBinding.inflate(LayoutInflater.from(context));
        this.listener = listener;


    }


    @Override
    public View getRootReView() {
        return binding.getRoot();
    }
}
