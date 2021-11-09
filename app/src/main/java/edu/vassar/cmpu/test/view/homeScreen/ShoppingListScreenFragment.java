package edu.vassar.cmpu.test.view.homeScreen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.vassar.cmpu.test.databinding.FragmentShoppingListScreenBinding;
import edu.vassar.cmpu.test.databinding.FragmentShoppingListScreenBinding;
import edu.vassar.cmpu.test.domain.ShoppingList;

public class ShoppingListScreenFragment extends Fragment implements IShoppingListScreenView {

    private Listener listener;
    private FragmentShoppingListScreenBinding binding;

    public ShoppingListScreenFragment() {
        //IMPORTANT
        //lets activity on launch assign fragment
    }

    public ShoppingListScreenFragment(Listener listener) {
        this.listener = listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentShoppingListScreenBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.binding.addButton.setOnClickListener((View clickedView) -> {
            this.listener.onAddItem();
        });
    }

    @Override
    public void updateDisplay(ShoppingList shoppingList) {
        this.binding.shoppingList.setText(shoppingList.toString());
    }
}