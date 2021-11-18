package edu.vassar.cmpu.test.view.purchasedListScreen;

import java.util.ArrayList;

import edu.vassar.cmpu.test.domain.LineItem;
import edu.vassar.cmpu.test.domain.House;

public interface IPurchasedListScreenFragment {

    interface Listener {
        void onPreviousOnPurchasedListScreen();

        void onPurchaseByUser(String distribution, IPurchasedListScreenFragment purchasedListScreenFragment);
    }
    void updatePurchasedList(ArrayList<LineItem> purchasedItems);
}
