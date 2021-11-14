package edu.vassar.cmpu.test.view.addItemView;

import java.util.ArrayList;


import edu.vassar.cmpu.test.domain.ShoppingList;
import edu.vassar.cmpu.test.domain.Housemate;

public interface IAddItemView {

    interface Listener{
        /**
         *
         * @param name
         * @param quantity
         * @param price
         * @param addItemView
         *  adds lineitem to shopping list
         */
        void onAddedItem(String name, int quantity, float price, ArrayList<Housemate> interestedHMs, IAddItemView addItemView);

        /**
         * opens up previous fragment which will be the home screen
         */
        void onPreviousInAddItemFragment();
    }
    void getHouseMates(ArrayList<Housemate> housemates);
    void updateDisplay(ShoppingList shoppingList);
}
