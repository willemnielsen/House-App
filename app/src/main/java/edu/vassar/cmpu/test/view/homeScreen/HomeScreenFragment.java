package edu.vassar.cmpu.test.view.homeScreen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.vassar.cmpu.test.R;
import edu.vassar.cmpu.test.databinding.FragmentHomeScreenBinding;

public class HomeScreenFragment extends Fragment implements IHomeScreenFragment {

    private Listener listener;
    private FragmentHomeScreenBinding binding;

    public HomeScreenFragment(Listener listener) {
        this.listener = listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentHomeScreenBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        this.binding.openShoppingListButton.setOnClickListener((View clickedView) ->{
            this.listener.onOpenShoppingList();
        });


        this.binding.openCalenderButton.setOnClickListener((View clickedView) ->{
            this.listener.onOpenCalendar();
        });
    }

}