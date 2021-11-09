package edu.vassar.cmpu.test.view.homeScreen;

import edu.vassar.cmpu.test.domain.ShoppingList;
import edu.vassar.cmpu.test.view.addItemView.IAddItemView;

public interface IShoppingListScreenView {

    interface Listener{
        void onAddItem();
    }

    void updateDisplay(ShoppingList shoppingList);


}
