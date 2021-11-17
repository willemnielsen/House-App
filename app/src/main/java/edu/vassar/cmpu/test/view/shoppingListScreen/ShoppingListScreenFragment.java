package edu.vassar.cmpu.test.view.shoppingListScreen;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import edu.vassar.cmpu.test.databinding.FragmentShoppingListScreenBinding;
import edu.vassar.cmpu.test.domain.Housemate;
import edu.vassar.cmpu.test.domain.LineItem;
import edu.vassar.cmpu.test.domain.ShoppingList;
import edu.vassar.cmpu.test.view.purchasedListScreen.IPurchasedListScreenFragment;
import edu.vassar.cmpu.test.view.purchasedListScreen.PurchasedListScreenFragment;

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

        this.binding.previousOnShoppingListScreen.setOnClickListener((View clickedView) -> {
            this.listener.onPreviousOnShoppingListScreen();
        });

    }


    private ArrayList<LineItem> CreateDialog(ShoppingList shoppingList){
        ArrayList<String> purchasedInString = new ArrayList<>();
        ArrayList<LineItem> purchasedItems = new ArrayList<>();
        String[] items = new String[shoppingList.size()];
        for(int i = 0 ; i < items.length; i++){
            items[i] = shoppingList.getShoppingListLineItem(i).toString();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("What items were purchased?")
                .setMultiChoiceItems(items, null, (dialog, which, isChecked) -> {

                    if(isChecked){
                        LineItem item = shoppingList.getShoppingListLineItem(which);
                        purchasedItems.add(item);
                        purchasedInString.add(items[which]);
                    }
                    else if (purchasedInString.contains(items[which])){
                        LineItem item = shoppingList.getShoppingListLineItem(which);
                        purchasedItems.remove(item);
                        purchasedInString.remove(items[which]);
                    }
                })
                .setPositiveButton("Done", (dialog, which) -> {
                    String data = "Items Purchased:";
                    for (String name : purchasedInString){
                        data = data + " " + name;
                    }
                    Toast.makeText(getActivity(), data, Toast.LENGTH_SHORT).show();
                    for(LineItem item : purchasedItems) {
                        onPurchaseItems(item);
                    }

                })
                .setNegativeButton("Cancel", (dialog, which) -> {

                });

        builder.create();
        builder.show();
        return purchasedItems;
    }
    @Override
    public void updateDisplay(ShoppingList shoppingList) {
        this.binding.shoppingList.setText(shoppingList.toString());
    }

    public void onPurchaseItems(LineItem lineItem){
        this.listener.onPurchaseItems(lineItem, this);
    }
    @Override
    public void updatePurchasedList(ArrayList<LineItem> purchasedItems) {
    }

    @Override
    public void purchaseItems(ShoppingList shoppingList){
        this.binding.purchaseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(shoppingList.size()==0){
                    Toast.makeText(getActivity(), "No Items in the ShoppingList", Toast.LENGTH_SHORT).show();
                }
                else{
                    CreateDialog(shoppingList);
                }


            }
        });
    }
}