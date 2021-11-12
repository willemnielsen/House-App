package edu.vassar.cmpu.test.view.addItemView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

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

    private FragmentAddItemBinding binding;
    private Listener listener;
    private HouseController house;


    public AddItemFragment(Listener listener) {
        this.listener = listener;
    }

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
                    } catch (NumberFormatException e) {
                    }

                    int qtyVal = Integer.parseInt(qtyString);
                    onAddedItem(name, qtyVal, price);
                } catch (NumberFormatException e) {
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

    private ArrayList CreateDialog(ArrayList<Housemate> housemates){

        ArrayList<String> selectedHM = new ArrayList<>();

        String[] names = new String[housemates.size()];
        for(int i = 0 ; i < names.length; i++){
           names[i] = housemates.get(i).getName();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Interested Housemates")
                .setMultiChoiceItems(names, null, (dialog, which, isChecked) -> {


                    if(isChecked){
                     selectedHM.add(names[which]);
                  }
                  else if (selectedHM.contains(names[which])){
                      selectedHM.remove(names[which]);

                  }
                })
                .setPositiveButton("Done", (dialog, which) -> {
                        String data = "Interested Housemate Added:";
                        for (String name : selectedHM){
                            data = data + " " + name;
                        }
                        Toast.makeText(getActivity(), data, Toast.LENGTH_SHORT).show();
                    })
                .setNegativeButton("Cancel", (dialog, which) -> {
                    //getActivity();
                });

        builder.create();
        builder.show();

        return selectedHM;
    }

    public void onAddedItem(String name, int quantity, float price) {
        this.listener.onAddedItem(name, quantity, price, this);
    }

    @Override
    public void updateDisplay(ShoppingList shoppingList) {
    }
    @Override
    public void getHouseMates(ArrayList<Housemate> housemates){
        this.binding.interestedHM.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                CreateDialog(housemates);
            }
        });
    }
}