package edu.vassar.cmpu.test.view.shoppingListScreen;

import java.util.ArrayList;

import edu.vassar.cmpu.test.domain.LineItem;
import edu.vassar.cmpu.test.domain.ShoppingList;

public interface IShoppingListScreenView {

    interface Listener{
        /**
         * opens the add item screen
         */
        void onAddItem();
        void onPurchaseItems(LineItem lineItem);
        void onPreviousOnShoppingListScreen();
        void updateShoppingPurchasedList(IShoppingListScreenView shoppingListScreenView);
    }

    void updateDisplay(ShoppingList shoppingList);
    void purchaseItems(ShoppingList shoppingList);
    void updatePurchasedList(ArrayList<LineItem> purchasedItems);

}
