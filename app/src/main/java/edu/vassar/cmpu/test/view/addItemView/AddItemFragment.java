package edu.vassar.cmpu.test.view.addItemView;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.vassar.cmpu.test.R;
import edu.vassar.cmpu.test.databinding.FragmentAddItemBinding;
import edu.vassar.cmpu.test.domain.ShoppingList;

public class AddItemFragment extends Fragment implements IAddItemView{

    private FragmentAddItemBinding binding;
    private Listener listener;

    public AddItemFragment(Listener listener){ this.listener = listener; }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentAddItemBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.binding.addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //code for when button is clicked
            }
        });


        this.binding.previous.setOnClickListener((View clickedView) -> {
            AddItemFragment.this.listener.onPreviousInAddItemFragment();
        });
    }

    @Override
    public void updateDisplay(ShoppingList shoppingList) {

    }
}