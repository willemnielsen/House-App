package edu.vassar.cmpu.test.view.shoppingListScreen;

import java.util.ArrayList;
import java.util.List;

import edu.vassar.cmpu.test.domain.LineItem;
import edu.vassar.cmpu.test.domain.ShoppingList;
import edu.vassar.cmpu.test.view.purchasedListScreen.IPurchasedListScreenFragment;

public interface IShoppingListScreenView {

    interface Listener{
        /**
         * opens the add item screen
         */
        void onAddItem();
        void onPurchaseItems(LineItem lineItem, IShoppingListScreenView shoppingListScreenView);
        void onPreviousOnShoppingListScreen();

    }

    void updateDisplay(ShoppingList shoppingList);
    void purchaseItems(ShoppingList shoppingList);
    void updatePurchasedList(List<LineItem> purchasedItems);

}
