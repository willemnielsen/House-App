package edu.vassar.cmpu.test.view.purchasedListScreen;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;


import edu.vassar.cmpu.test.databinding.FragmentPurchasedBinding;
import edu.vassar.cmpu.test.domain.Housemate;
import edu.vassar.cmpu.test.domain.LineItem;
import edu.vassar.cmpu.test.view.addItemView.AddItemFragment;
import edu.vassar.cmpu.test.view.shoppingListScreen.IShoppingListScreenView;


public class PurchasedListScreenFragment extends Fragment implements IPurchasedListScreenFragment {
    private FragmentPurchasedBinding binding;
    private Listener listener;

    public PurchasedListScreenFragment(){

    }

    public PurchasedListScreenFragment(IPurchasedListScreenFragment.Listener listener) {
        this.listener = listener;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentPurchasedBinding.inflate(inflater);
        return this.binding.getRoot();
    }
    @Override
    public void updatePurchasedList(ArrayList<LineItem> purchasedItems) {
        String toText = "There have been " + purchasedItems.size() + " items purchased \n";
        for (LineItem item: purchasedItems){
            toText = toText + item.toString() + "\n";
        }
        this.binding.purchasedItem.setText(toText);
    }

   @Override
   public void onViewCreated(View view, Bundle savedInstanceState) {
        this.binding.previousOnPurchasedListScreen.setOnClickListener((View clickedView) -> {
           this.listener.onPreviousOnPurchasedListScreen();
        });

       this.binding.purchaseButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               CreateDialog();
           }
       });
    }

    public void CreateDialog(){
        String[] distributions = {"Charge Based on Interested Housemates", "Charge Household", "Charge Me"};
        ArrayList<String> selectedType = new ArrayList<>();
        ArrayList<Housemate> interestedHM = new ArrayList<>();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Charge Distribution").setMultiChoiceItems(distributions, null,
                (dialog, which, isChecked) -> {
                    if(isChecked){
                        selectedType.add(distributions[which]);
                    } else if (selectedType.contains(distributions[which])){
                        selectedType.remove(distributions[which]);
                    }
        });
        builder.setPositiveButton("Done", (dialog, which) -> {
            if (selectedType.isEmpty() || selectedType.size() > 1){
                CreateDialog();
            } else{
                String data = "PaymentMethod: ";
                for (String n : selectedType){
                    data = data + " " + n;
                }
                PurchasedListScreenFragment.this.listener.onPurchaseByUser(selectedType.get(0), PurchasedListScreenFragment.this);
                Toast.makeText(getActivity(), data, Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("Cancel", (dialog, which) -> {

        });

        builder.create();
        builder.show();
    }
}
