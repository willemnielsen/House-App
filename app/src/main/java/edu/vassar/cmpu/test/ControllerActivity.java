package edu.vassar.cmpu.test;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import edu.vassar.cmpu.test.domain.HouseController;
import edu.vassar.cmpu.test.view.addItemView.AddItemFragment;
import edu.vassar.cmpu.test.view.addItemView.IAddItemView;
import edu.vassar.cmpu.test.view.homeScreen.MainScreenFragment;

public class ControllerActivity extends AppCompatActivity implements IAddItemView.Listener {
    //extends makes this class an activity

    private HouseController houseController;
    private IMainView mainView;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        houseController = new HouseController("TH 84");

        mainView = new MainView(this);
        setContentView(mainView.getRootView());

        this.mainView.displayFragment(new AddItemFragment(this));//displays the add Item Fragment
    }


    @Override
    public void onAddedItem(String name, int quantity, IAddItemView addItemView) {
        houseController.addLineItemToShoppingList(quantity, name, 0.0f, null);
        addItemView.updateDisplay(houseController.getHouse().getShoppingList());
    }

    @Override
    public void onPreviousInAddItemFragment() {
        this.openHomeScreen();
    }


    public void openHomeScreen() {
        this.mainView.displayFragment(new MainScreenFragment());
    }
}
