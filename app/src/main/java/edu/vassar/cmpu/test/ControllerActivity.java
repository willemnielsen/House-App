package edu.vassar.cmpu.test;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;


//import edu.vassar.cmpu.test.domain.House;
import edu.vassar.cmpu.test.domain.HouseController;
import edu.vassar.cmpu.test.domain.Housemate;
import edu.vassar.cmpu.test.domain.LineItem;
import edu.vassar.cmpu.test.domain.Recurrence;
import edu.vassar.cmpu.test.domain.ShoppingList;
import edu.vassar.cmpu.test.persistence.FirestoreFacade;
import edu.vassar.cmpu.test.persistence.IPersistenceFacade;
import edu.vassar.cmpu.test.view.addEventView.AddEventFragment;
import edu.vassar.cmpu.test.view.addEventView.IAddEventView;
import edu.vassar.cmpu.test.view.addItemView.AddItemFragment;
import edu.vassar.cmpu.test.view.addItemView.IAddItemView;
import edu.vassar.cmpu.test.view.calendarScreen.CalendarScreenFragment;
import edu.vassar.cmpu.test.view.calendarScreen.ICalendarScreenView;
import edu.vassar.cmpu.test.view.homeScreen.HomeScreenFragment;
import edu.vassar.cmpu.test.view.homeScreen.IHomeScreenFragment;
import edu.vassar.cmpu.test.view.housemateListScreen.HousemateListScreenFragment;
import edu.vassar.cmpu.test.view.housemateListScreen.IHousemateListScreenFragment;
import edu.vassar.cmpu.test.view.housemateListScreen.addHousemate.AddHousemateFragment;
import edu.vassar.cmpu.test.view.housemateListScreen.addHousemate.IAddHousemate;
import edu.vassar.cmpu.test.view.housemateListScreen.debtScreen.DebtScreenFragment;
import edu.vassar.cmpu.test.view.housemateListScreen.debtScreen.IDebtScreenFragment;
import edu.vassar.cmpu.test.view.loginScreen.ILoginScreenFragment;
import edu.vassar.cmpu.test.view.loginScreen.LoginScreenFragment;
import edu.vassar.cmpu.test.view.purchasedListScreen.IPurchasedListScreenFragment;
import edu.vassar.cmpu.test.view.purchasedListScreen.PurchasedListScreenFragment;
import edu.vassar.cmpu.test.view.shoppingListScreen.IShoppingListScreenView;
import edu.vassar.cmpu.test.view.shoppingListScreen.ShoppingListScreenFragment;
import edu.vassar.cmpu.test.view.transactionsScreen.ITransactionsScreenFragment;
import edu.vassar.cmpu.test.view.transactionsScreen.TransactionsScreenFragment;

public class ControllerActivity extends AppCompatActivity
        implements IShoppingListScreenView.Listener, IHomeScreenFragment.Listener,
            IAddItemView.Listener, ICalendarScreenView.Listener, IAddEventView.Listener,
            ILoginScreenFragment.Listener, IHousemateListScreenFragment.Listener,
            IPurchasedListScreenFragment.Listener, IAddHousemate.Listener, IDebtScreenFragment.Listener,
            ITransactionsScreenFragment.Listener {
    //extends makes this class an activity

    private ShoppingList shoppingList;
    private LineItem curItem;
    private HouseController houseController;
    private IMainView mainView;

    private IPersistenceFacade persistenceFacade = new FirestoreFacade();

    private static final String CUR_ITEM = "curItem";

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        FragmentFactory fragmentFactory = new HousemateFactory(this);
        this.getSupportFragmentManager().
                setFragmentFactory(fragmentFactory);


        mainView = new MainView(this);
        setContentView(mainView.getRootView());
        Log.i("Housemates", "onCreate activity");

        if (savedInstanceState != null)
            this.curItem = (LineItem) savedInstanceState.getSerializable(CUR_ITEM); // retrieve preexisting line item

        this.shoppingList = new ShoppingList(); //initialize shopping list

        this.persistenceFacade.retrieveShoppingList(new IPersistenceFacade.ShoppingListListener() {
            @Override
            public void onShoppingListReceived(ShoppingList shoppingList) {
                ControllerActivity.this.shoppingList = shoppingList; // set the activity's shopping list to the one retrieved from the database
                // please see the lecture #24 slides for a more elegant solution to the timing issue
                // that necessitates this update
                ControllerActivity.this.mainView.displayFragment(new ShoppingListScreenFragment(ControllerActivity.this));
            }
        });


        this.mainView = new MainView(this);
        this.setContentView(this.mainView.getRootView());
        if (savedInstanceState == null) // means it's the first time we're launching the activity
            this.mainView.displayFragment(new LoginScreenFragment(this));
            //displays the add Item Fragment
    }

    //
    //House
    //

    @Override
    public void onCreateHouse(String houseName, String membersName) {
        houseController = new HouseController(houseName);
        Housemate hm = new Housemate(membersName, "343243");
        houseController.addHousemate(hm);
        houseController.setUser(hm); // sets new user to logging in one
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

    @Override
    public void onOpenHousemateList(){
        openHousemateListScreen();
    }

    @Override
    public void onOpenPurchasedList(){ openPurchasedListScreen();}

    @Override
    public void onOpenTransactions() {
        ITransactionsScreenFragment ts = new TransactionsScreenFragment(this);
        this.mainView.displayFragment((TransactionsScreenFragment) ts);
        ts.updateDisplay(houseController.houseTransactions());
    }

    @Override
    public void onPreviousOnTransactionsScreen() {
        openHomeScreen();
    }

    //
    // Shopping List
    //

    public void openShoppingListScreen() {
        IShoppingListScreenView sl = new ShoppingListScreenFragment(this);
        this.mainView.displayFragment((ShoppingListScreenFragment) sl);
        sl.updateDisplay(houseController.getHouse().getShoppingList());
        sl.purchaseItems(houseController.getHouse().getShoppingList());

    }

    @Override
    public void onAddItem() {
        this.openAddItemScreen();
    }

    @Override
    public void onPreviousOnShoppingListScreen() {
        this.openHomeScreen();
    }
    @Override
    public void onPurchaseItems(LineItem lineitem, IShoppingListScreenView shoppingListScreenView){
        houseController.addToPurchase(lineitem);
        houseController.getHouse().getShoppingList().remove(lineitem);
        shoppingListScreenView.updateDisplay(houseController.getHouse().getShoppingList());
        shoppingListScreenView.updatePurchasedList(houseController.getHouse().getPurchasedItems());
    }



        //
        //add item
        //


    /**
     * opens the add item screen
     */
    public void openAddItemScreen() {
        //this.mainView.displayFragment(new AddItemFragment(this));
        IAddItemView hm = new AddItemFragment(this);
        this.mainView.displayFragment((AddItemFragment) hm);
        hm.getHouseMates(houseController.getHouse().getHousemates());
    }

        @Override
        public void onAddedItem(String name, int quantity, float price,
                                ArrayList<Housemate> interestedHMs, IAddItemView addItemView) {
            houseController.addLineItemToShoppingList(quantity, name, price, interestedHMs);
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


    @Override
    public void onPreviousOnCalendarScreen() {
        this.openHomeScreen();
    }
        //
        // add Event
        //

        public void openAddEventScreen() {
            IAddEventView hm = new AddEventFragment(this);
            this.mainView.displayFragment((AddEventFragment) hm);
            hm.getAddedHouseMates(houseController.getHouse().getHousemates());
        }

        @Override
        public void onAddedEvent(String name, Date date, Time startTime, Time endTime,
                                 ArrayList<Housemate> interestedHMs, String rec,
                                 IAddEventView addEventView) {
            Date startDate = date;
            Date endDate = new Date(startDate.getTime()+31556952000L);
            Recurrence recurrence = new Recurrence(rec, startDate, endDate);
            houseController.addEventToCalendar(name, date, startTime, endTime, interestedHMs,
                    recurrence);

            addEventView.updateDisplay(houseController.getHouse().getCalendar());
        }

        @Override
        public void onSetDate(Date date, ICalendarScreenView calendarScreenView) {
            houseController.getHouse().getCalendar().setCurrentDate(date);
            calendarScreenView.updateDisplay(houseController.getHouse().getCalendar());
        }


    @Override
    public void onPreviousInAddEventFragment() {
        this.openCalendarScreen();
    }


    //
    // Housemate List Screen
    //
    public void openHousemateListScreen() {
        IHousemateListScreenFragment hms = new HousemateListScreenFragment(this);
        this.mainView.displayFragment((HousemateListScreenFragment) hms);
        hms.updateDisplay(houseController.getHousemates());
    }

    @Override
    public void onPreviousOnHousemateListScreen() {
        this.openHomeScreen();
    }

    @Override
    public void onAddHousemateOnHousemateListScreen() {
        this.mainView.displayFragment(new AddHousemateFragment(this));
    }

    @Override
    public void onDebtScreenButton() {
        IDebtScreenFragment df = new DebtScreenFragment(this);
        this.mainView.displayFragment((DebtScreenFragment) df);
        df.updateDisplay(houseController.houseBalance());
    }

    @Override
    public void onPreviousOnDebtScreen() {
        this.openHousemateListScreen();
    }

        //
        // add housemate
        //
        @Override
        public void onAddHousemate(String name) {
            int id = (int) (Math.random() * 1000);
            houseController.addHousemate(new Housemate(name, "" + id));
        }

        @Override
        public void onPreviousOnAddHousemateScreen() {
            this.openHousemateListScreen();
        }


    //
    // Purchased Screen
    //
    public void openPurchasedListScreen() {
        IPurchasedListScreenFragment pl = new PurchasedListScreenFragment(this);
        this.mainView.displayFragment((PurchasedListScreenFragment) pl);
        pl.updatePurchasedList(houseController.getHouse().getPurchasedItems());
    }

    @Override
    public void onPreviousOnPurchasedListScreen(){
        this.openHomeScreen();
    }

    @Override
    public void onPurchaseByUser(String distribution,  IPurchasedListScreenFragment purchasedListScreenFragment) {
        houseController.checkout(distribution, houseController.getLoggedInUser());
        houseController.getHouse().getPurchasedItems().clear();
        purchasedListScreenFragment.updatePurchasedList(houseController.getHouse().getPurchasedItems());
    }
}