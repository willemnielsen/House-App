package edu.vassar.cmpu.test;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import edu.vassar.cmpu.test.domain.HouseController;
import edu.vassar.cmpu.test.view.addItemView.AddItemFragment;
import edu.vassar.cmpu.test.view.addItemView.IAddItemView;
import edu.vassar.cmpu.test.view.homeScreen.IShoppingListScreenView;
import edu.vassar.cmpu.test.view.homeScreen.ShoppingListScreenFragment;

public class ControllerActivity extends AppCompatActivity implements IShoppingListScreenView.Listener, IAddItemView.Listener {
    //extends makes this class an activity

    private HouseController houseController;
    private IMainView mainView;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        houseController = new HouseController("TH 84");

        mainView = new MainView(this);
        setContentView(mainView.getRootView());

        this.mainView.displayFragment(new ShoppingListScreenFragment(this));//displays the add Item Fragment
    }


    @Override
    public void onAddItem() {
        this.openAddItemScreen();
    }

    @Override
    public void onAddedItem(String name, int quantity, float price, IAddItemView addItemView) {
        houseController.addLineItemToShoppingList(quantity, name, price, null);
        addItemView.updateDisplay(houseController.getHouse().getShoppingList());
    }

    @Override
    public void onPreviousInAddItemFragment() {
        this.openHomeScreen();
    }


    public void openHomeScreen() {
        IShoppingListScreenView sl = new ShoppingListScreenFragment(this);
        this.mainView.displayFragment((ShoppingListScreenFragment) sl);
        sl.updateDisplay(houseController.getHouse().getShoppingList());
    }

    public void openAddItemScreen() {
        this.mainView.displayFragment(new AddItemFragment(this));
    }


}
