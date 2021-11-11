package edu.vassar.cmpu.test;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import edu.vassar.cmpu.test.domain.HouseController;
import edu.vassar.cmpu.test.domain.Housemate;
import edu.vassar.cmpu.test.view.addItemView.AddItemFragment;
import edu.vassar.cmpu.test.view.addItemView.IAddItemView;
import edu.vassar.cmpu.test.view.homeScreen.IHomeScreenFragment;
import edu.vassar.cmpu.test.view.shoppingListScreen.IShoppingListScreenView;
import edu.vassar.cmpu.test.view.shoppingListScreen.ShoppingListScreenFragment;
import edu.vassar.cmpu.test.view.loginScreen.ILoginScreenFragment;
import edu.vassar.cmpu.test.view.loginScreen.LoginScreenFragment;


public class ControllerActivity extends AppCompatActivity
        implements ILoginScreenFragment.Listener, IHomeScreenFragment.Listener, IShoppingListScreenView.Listener, IAddItemView.Listener {
    //extends makes this class an activity

    private HouseController houseController;
    private IMainView mainView;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        mainView = new MainView(this);
        setContentView(mainView.getRootView());

        this.mainView.displayFragment(new LoginScreenFragment(this));//displays the add Item Fragment
    }


    @Override
    public void onCreateHouse(String houseName, String membersName) {
        houseController = new HouseController(houseName);
        houseController.addHousemate(new Housemate(membersName, "343243"));

    }

    @Override
    public void onOpenShoppingList() {
        openShoppingListScreen();
    }

    @Override
    /**
     * opens the add item screen
     */
    public void onAddItem() {
        this.openAddItemScreen();
    }

    @Override
    public void onAddedItem(String name, int quantity, float price, IAddItemView addItemView) {
        houseController.addLineItemToShoppingList(quantity, name, price, null);
        addItemView.updateDisplay(houseController.getHouse().getShoppingList());
    }

    @Override
    /**
    * opens up previous fragment which will be the home screen
    */
    public void onPreviousInAddItemFragment() {
        this.openShoppingListScreen();
    }


    /**
     * opens ShoppingList screen with updated Shopping List
     */
    public void openShoppingListScreen() {
        IShoppingListScreenView sl = new ShoppingListScreenFragment(this);
        this.mainView.displayFragment((ShoppingListScreenFragment) sl);
        sl.updateDisplay(houseController.getHouse().getShoppingList());
    }

    /**
     * opens the add item screen
     */
    public void openAddItemScreen() {
        this.mainView.displayFragment(new AddItemFragment(this));
    }
}
