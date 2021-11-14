package edu.vassar.cmpu.test.view.purchasedListScreen;

import java.util.ArrayList;

import edu.vassar.cmpu.test.domain.LineItem;

public interface IPurchasedListScreenFragment {

    interface Listener {
        void onPreviousOnPurchasedListScreen();
    }
    void updatePurchasedList(ArrayList<LineItem> purchasedItems);
}
