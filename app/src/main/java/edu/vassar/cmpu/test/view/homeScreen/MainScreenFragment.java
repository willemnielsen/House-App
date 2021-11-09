package edu.vassar.cmpu.test.view.homeScreen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.vassar.cmpu.test.R;
import edu.vassar.cmpu.test.databinding.FragmentAddItemBinding;
import edu.vassar.cmpu.test.databinding.FragmentMainScreenBinding;
import edu.vassar.cmpu.test.domain.ShoppingList;
import edu.vassar.cmpu.test.view.addItemView.AddItemFragment;

public class MainScreenFragment extends Fragment implements IMainScreenView{

    private Listener listener;
    private FragmentMainScreenBinding binding;

    public MainScreenFragment() {
        //IMPORTANT
        //lets activity on launch assign fragment
    }

    public MainScreenFragment(Listener listener) {
        this.listener = listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentMainScreenBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.binding.addButton.setOnClickListener((View clickedView) -> {
            MainScreenFragment.this.listener.onAddItem();
        });
    }

    @Override
    public void updateDisplay(ShoppingList shoppingList) {
    }
}