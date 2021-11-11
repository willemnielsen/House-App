package edu.vassar.cmpu.test.view.shoppingListScreen;

import edu.vassar.cmpu.test.domain.ShoppingList;

public interface IShoppingListScreenView {

    interface Listener{
        /**
         * opens the add item screen
         */
        void onAddItem();
        void onOpenCalendarScreen();
    }

    void updateDisplay(ShoppingList shoppingList);


}
