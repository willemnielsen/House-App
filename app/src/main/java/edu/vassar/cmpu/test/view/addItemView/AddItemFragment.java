package edu.vassar.cmpu.test.view.addItemView;

import android.app.AlertDialog;
//import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


import edu.vassar.cmpu.test.ControllerActivity;
import edu.vassar.cmpu.test.R;
import edu.vassar.cmpu.test.databinding.FragmentAddItemBinding;
import edu.vassar.cmpu.test.domain.House;
import edu.vassar.cmpu.test.domain.HouseController;
import edu.vassar.cmpu.test.domain.Housemate;
import edu.vassar.cmpu.test.domain.ShoppingList;

public class AddItemFragment extends Fragment implements IAddItemView {

    private final static String ITEM_NAME = "itemName";
    private final static String ITEM_QUANTITY = "itemQuantity";
    private final static String ITEM_PRICE = "itemPrice";


    private FragmentAddItemBinding binding;
    private Listener listener;
    private HouseController house;

    public AddItemFragment(Listener listener) {
        this.listener = listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //if things were there before adds them back
        Bundle args = this.getArguments();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentAddItemBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.binding.previousOnAddItem.setOnClickListener((View clickedView) -> {
            this.listener.onPreviousInAddItemFragment();
        });



    }

    private void CreateDialog(ArrayList<Housemate> housemates, String name, int qtyVal,
                              float price){
        ArrayList<String> selectedHM = new ArrayList<>();
        ArrayList<Housemate> interestedHM = new ArrayList<>();
        String[] names = new String[housemates.size()];
        for(int i = 0 ; i < names.length; i++){
           names[i] = housemates.get(i).getName();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Interested Housemates").setMultiChoiceItems(names, null,
                (dialog, which, isChecked) -> {
            if(isChecked){
                     selectedHM.add(names[which]);
                     interestedHM.add(housemates.get(which));
            } else if (selectedHM.contains(names[which])){
                      selectedHM.remove(names[which]);
                      interestedHM.remove(housemates.get(which));
            }
        }).setPositiveButton("Done", (dialog, which) -> {
            if (selectedHM.isEmpty()){
                CreateDialog(housemates, name, qtyVal, price);
            } else{
                String data = "Interested Housemate Added:";
                for (String n : selectedHM){
                    data = data + " " + n;
                }
                AddItemFragment.this.listener.onAddedItem(name, qtyVal, price, interestedHM,
                        AddItemFragment.this);
                Toast.makeText(getActivity(), data, Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("Cancel", (dialog, which) -> {

        });

        builder.create();
        builder.show();

    }

    @Override
    public void updateDisplay(ShoppingList shoppingList) {
    }

    @Override
    public void getHouseMates(ArrayList<Housemate> housemates){
        this.binding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get the item name
                Editable nameEditable = binding.typeItemName.getText();
                String name = nameEditable.toString();

                Editable priceEditable = binding.typePrice.getText();
                String priceString = priceEditable.toString();

                Editable qtyEditable = binding.typeQt.getText();
                String qtyString = qtyEditable.toString();

                if (name.isEmpty()) {binding.typeItemName.setError("Please Enter a name");}

                if (qtyString.isEmpty()) {binding.typeQt.setError("Please Enter a Quantity");}

                try {
                    //price default --> 0
                    float price = 0.0f;
                    try {
                        price = Float.parseFloat(priceString);
                    } catch (NumberFormatException e) {
                    }

                    int qtyVal = Integer.parseInt(qtyString);
                    CreateDialog(housemates, name, qtyVal, price);
                } catch (NumberFormatException e) {

                    binding.typeItemName.setText("");
                    binding.typeQt.setText("");
                    binding.typePrice.setText("");
                }
                nameEditable.clear();
                priceEditable.clear();
                qtyEditable.clear();
            }
        });


    }

    /**
     * This method is used to give the fragment a chance to save state information before
     * being destroyed to free up system resources.
     * @param outState the bundle to save state to
     */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState); // will save some widget features automatically
        //gets values from text field
        outState.putString(AddItemFragment.ITEM_NAME, binding.typeItemName.getText().toString());
        outState.putString(AddItemFragment.ITEM_QUANTITY, binding.typeQt.getText().toString());
        outState.putString(AddItemFragment.ITEM_PRICE, binding.typePrice.getText().toString());
    }

    /**
     * This method is used to give the fragment a chance to restore state information before
     * being destroyed to free up system resources.
     * @param savedInstanceState the bundle to read state from
     */
    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState); // will reinstate widget features automatically

        if (savedInstanceState != null) { // it can be null if it's the first time we're creating the fragment
            //puts back stored values
            binding.typeItemName.setText(savedInstanceState.getString(AddItemFragment.ITEM_NAME));
            binding.typeQt.setText(savedInstanceState.getString(AddItemFragment.ITEM_QUANTITY));
            binding.typePrice.setText(savedInstanceState.getString(AddItemFragment.ITEM_PRICE));
        }
    }
}