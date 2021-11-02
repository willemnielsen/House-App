package edu.vassar.cmpu.test.view.addItemView;

import android.view.View;

public interface IAddItemView {

    interface Listener{ void onAddedItem(String name, int quantity); }

    public View getRootReView();

}
