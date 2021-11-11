package edu.vassar.cmpu.test;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Time;
import java.util.Date;

import edu.vassar.cmpu.test.domain.House;
import edu.vassar.cmpu.test.domain.HouseController;
import edu.vassar.cmpu.test.domain.Housemate;
import edu.vassar.cmpu.test.view.addEventView.AddEventFragment;
import edu.vassar.cmpu.test.view.addEventView.IAddEventView;
import edu.vassar.cmpu.test.view.addItemView.AddItemFragment;
import edu.vassar.cmpu.test.view.addItemView.IAddItemView;
import edu.vassar.cmpu.test.view.calendarScreen.CalendarScreenFragment;
import edu.vassar.cmpu.test.view.calendarScreen.ICalendarScreenView;
import edu.vassar.cmpu.test.view.homeScreen.HomeScreenFragment;
import edu.vassar.cmpu.test.view.homeScreen.IHomeScreenFragment;
import edu.vassar.cmpu.test.view.loginScreen.ILoginScreenFragment;
import edu.vassar.cmpu.test.view.loginScreen.LoginScreenFragment;
import edu.vassar.cmpu.test.view.shoppingListScreen.IShoppingListScreenView;
import edu.vassar.cmpu.test.view.shoppingListScreen.ShoppingListScreenFragment;

public class ControllerActivity extends AppCompatActivity
        implements IShoppingListScreenView.Listener, IHomeScreenFragment.Listener, IAddItemView.Listener, ICalendarScreenView.Listener,
                   IAddEventView.Listener, ILoginScreenFragment.Listener {
    //extends makes this class an activity

    private HouseController houseController;
    private IMainView mainView;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        mainView = new MainView(this);
        setContentView(mainView.getRootView());

        this.mainView.displayFragment(new LoginScreenFragment(this));//displays the add Item Fragment
    }

    //
    //House
    //

    @Override
    public void onCreateHouse(String houseName, String membersName) {
        houseController = new HouseController(houseName);
        houseController.addHousemate(new Housemate(membersName, "343243"));
        openHomeScreen();
    }

    public void openHomeScreen(){
        this.mainView.displayFragment(new HomeScreenFragment(this));
    }

    //
    // Home Screen
    //

    @Override
    public void onOpenShoppingList() {
        openShoppingListScreen();
    }

    @Override
    public void onOpenCalendar() {
        openCalendarScreen();
    }

    //
    // Shopping List
    //

    public void openShoppingListScreen() {
        IShoppingListScreenView sl = new ShoppingListScreenFragment(this);
        this.mainView.displayFragment((ShoppingListScreenFragment) sl);
        sl.updateDisplay(houseController.getHouse().getShoppingList());
    }

    @Override
    public void onAddItem() {
        this.openAddItemScreen();
    }

    @Override
    public void onPreviousOnShoppingListScreen() {
        this.openHomeScreen();
    }

        //
        //add item
        //

        public void openAddItemScreen() {
            this.mainView.displayFragment(new AddItemFragment(this));
        }

        @Override
        public void onAddedItem(String name, int quantity, float price, IAddItemView addItemView) {
            houseController.addLineItemToShoppingList(quantity, name, price, null);
            addItemView.updateDisplay(houseController.getHouse().getShoppingList());
        }

        @Override
        public void onPreviousInAddItemFragment() {
        this.openShoppingListScreen();
    }


    //
    // Calender
    //
    public void openCalendarScreen() {
        ICalendarScreenView cs = new CalendarScreenFragment(this);
        this.mainView.displayFragment((CalendarScreenFragment) cs);
        cs.updateDisplay(houseController.getHouse().getCalendar());
    }

    @Override
    public void onAddEvent() {
        this.openAddEventScreen();
    }

        //
        // add Event
        //

        public void openAddEventScreen() {
            this.mainView.displayFragment(new AddEventFragment(this));
        }

        @Override
        public void onAddedEvent(String name, Date date, Time startTime, Time endTime, IAddEventView addEventView) {
            houseController.addEventToCalendar(name, date, startTime, endTime, null, "Daily");
            addEventView.updateDisplay(houseController.getHouse().getCalendar());
        }

        @Override
        public void onSetDate(Date date, ICalendarScreenView calendarScreenView) {
            houseController.setDate(date);
            calendarScreenView.updateDisplay(houseController.getHouse().getCalendar());
        }

    @Override
    public void onPreviousOnCalendarScreen() {
        this.openHomeScreen();
    }

    @Override
        public void onPreviousInAddEventFragment() {
            this.openCalendarScreen();
        }
}