package edu.vassar.cmpu.test.view.addItemView;

import edu.vassar.cmpu.test.domain.ShoppingList;

public interface IAddItemView {

    interface Listener{
        void onAddedItem(String name, int quantity, float price, IAddItemView addItemView);
        void onPreviousInAddItemFragment();
    }

    void updateDisplay(ShoppingList shoppingList);

}
