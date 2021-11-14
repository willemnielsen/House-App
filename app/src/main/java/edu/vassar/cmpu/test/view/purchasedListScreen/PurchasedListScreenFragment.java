package edu.vassar.cmpu.test.view.purchasedListScreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;


import edu.vassar.cmpu.test.databinding.FragmentPurchasedBinding;
import edu.vassar.cmpu.test.domain.LineItem;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentPurchasedBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    public void updatePurchasedList(ArrayList<LineItem> purchasedItems) {
        for (LineItem item: purchasedItems){
        this.binding.purchasedItem.setText("items:" + item.toString());
    }}

   @Override
   public void onViewCreated(View view, Bundle savedInstanceState) {
        this.binding.previousOnPurchasedListScreen.setOnClickListener((View clickedView) -> {
           this.listener.onPreviousOnPurchasedListScreen();
        });
    }
}
