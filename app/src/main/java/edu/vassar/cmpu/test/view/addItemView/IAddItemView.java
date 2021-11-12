package edu.vassar.cmpu.test.view.addItemView;

import java.util.ArrayList;


import edu.vassar.cmpu.test.domain.ShoppingList;
import edu.vassar.cmpu.test.domain.Housemate;

public interface IAddItemView {

    interface Listener{
        void onAddedItem(String name, int quantity, float price, IAddItemView addItemView);
        void onPreviousInAddItemFragment();
    }
    void getHouseMates(ArrayList<Housemate> housemates);
    void updateDisplay(ShoppingList shoppingList);
}
