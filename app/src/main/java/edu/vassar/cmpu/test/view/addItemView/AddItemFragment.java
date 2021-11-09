package edu.vassar.cmpu.test.view.addItemView;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
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
                // get the item name
                Editable nameEditable = binding.typeItemName.getText();
                String name = nameEditable.toString();
                nameEditable.clear();


                Editable priceEditable = binding.typePrice.getText();
                String priceString = priceEditable.toString();


                Editable qtyEditable = binding.typeQt.getText();
                String qtyString = qtyEditable.toString();
                try {
                    //price default --> 0
                    float price = 0.0f;
                    try {
                        price = Float.parseFloat(priceString);
                    } catch (NumberFormatException e) { }

                    int qtyVal = Integer.parseInt(qtyString);

                    onAddedItem(name, qtyVal, price);
                } catch (NumberFormatException e){
                   binding.typeItemName.setText("");
                   binding.typeQt.setText("");
                   binding.typePrice.setText("");
               }
                priceEditable.clear();
                qtyEditable.clear();
            }
        });


        this.binding.previous.setOnClickListener((View clickedView) -> {
            this.listener.onPreviousInAddItemFragment();
        });
    }

    public void onAddedItem(String name, int quantity, float price){
        this.listener.onAddedItem(name, quantity, price, this);
    }


    @Override
    public void updateDisplay(ShoppingList shoppingList) {

    }
}