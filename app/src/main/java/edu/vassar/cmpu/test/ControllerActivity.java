package edu.vassar.cmpu.test;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


//import edu.vassar.cmpu.test.domain.House;
import edu.vassar.cmpu.test.domain.Calendar;
import edu.vassar.cmpu.test.domain.Debt;
import edu.vassar.cmpu.test.domain.Event;
import edu.vassar.cmpu.test.domain.House;
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
import edu.vassar.cmpu.test.view.authScreen.AuthFragment;
import edu.vassar.cmpu.test.view.authScreen.IAuthView;
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
            ITransactionsScreenFragment.Listener, IPersistenceFacade.ShoppingListListener, IPersistenceFacade.CalendarListener, IPersistenceFacade.DebtListListener, IAuthView.Listener {
    //extends makes this class an activity

    private LineItem curItem;
    private HouseController houseController = new HouseController();
    private IMainView mainView;

    private IPersistenceFacade persistenceFacade = new FirestoreFacade();

    private static final String CUR_ITEM = "curItem";
    private static final String CUR_USER = "curUser";
    private static final String CUR_HOUSE = "curHouse";

    private int debtlistSize = 0; //used to not double add
    private Housemate curUser;       // current user

    protected void onCreate(Bundle savedInstanceState){

        FragmentFactory fragmentFactory = new HousemateFactory(this);
        this.getSupportFragmentManager().
                setFragmentFactory(fragmentFactory);

        super.onCreate(savedInstanceState);


        mainView = new MainView(this);
        setContentView(mainView.getRootView());
        //Log.i("Housemates", "onCreate activity");

        if (savedInstanceState != null)
            this.curItem = (LineItem) savedInstanceState.getSerializable(CUR_ITEM); // retrieve preexisting line item

        if (savedInstanceState == null) // means it's the first time we're launching the activity*/
            this.mainView.displayFragment(new LoginScreenFragment(this));
        //displays the Login Screen Fragment
    }



    //
    //House
    //


/*    @Override
    public void onCreateHouse(String houseName, String housePassword) {
        houseController = new HouseController(houseName, housePassword);
        houseController.setUser(hm); // sets new user to logging in one
        this.persistenceFacade.setHouseName(houseController.getHouse().getName());
        this.persistenceFacade.saveHousemate(hm);
        openHomeScreen();

       // this.persistenceFacade.setHouseName(houseName);


    }*/

    public void openHomeScreen(){
        this.mainView.displayFragment(new HomeScreenFragment(this));
    }

    //
    // Home Screen
    //

    /* ILoginScreenFragment.Listener realization start */
    @Override
    public void onRegisterHouse(String houseName, String housePassword, ILoginScreenFragment loginScreenFragment) {
        HouseController house = new HouseController(houseName, housePassword); // our tentative house
        ControllerActivity.this.houseController = house;
        this.persistenceFacade.setHouseName(houseName);
        this.persistenceFacade.createHouseIfNotExists(house.getHouse(), new IPersistenceFacade.BinaryResultListener() {
            @Override
            public void onYesResult() { loginScreenFragment.onRegisterHouseSuccess(); }

            @Override
            public void onNoResult() { loginScreenFragment.onHouseAlreadyExists(); }
        });
    }

    @Override
    public void onHouseSigninAttempt(String houseName, String housePassword, ILoginScreenFragment loginScreenFragment) {
        this.persistenceFacade.setHouseName(houseName);
        this.persistenceFacade.retrieveHouse(houseName, new IPersistenceFacade.DataListener<House>() {
            @Override
            public void onDataReceived(@NonNull House houseName) {
                if (houseName.validatePassword(housePassword)){ // password matches
                    ControllerActivity.this.houseController.getHouse().setAuthKey(houseName.getAuthKey());
                    ControllerActivity.this.houseController.getHouse().setAuthKey(houseName.getAuthKey());
                    Log.e("TEST", "loaded House!!!!! ");

                    // navigate to ledger screen
                    ControllerActivity.this.mainView.displayFragment(new AuthFragment(ControllerActivity.this));

                } else loginScreenFragment.onInvalidHouseCredentials(); // let the view know things didn't work out
            }

            @Override
            public void onNoDataFound() { // means username does not exist
                loginScreenFragment.onInvalidHouseCredentials(); // let the view know things didn't work out
            }
        });

        this.persistenceFacade.retrieveShoppingList(new IPersistenceFacade.ShoppingListListener() {
            @Override
            public void onShoppingListReceived(ShoppingList shoppingList) {
                ControllerActivity.this.houseController.getHouse().loadShoppingList(shoppingList);
                Log.e("TEST", "loaded ShoppingLIST ");
                // set the activity's shopping list to the one retrieved from the database

            }
        });

        this.persistenceFacade.retrieveCalendar(new IPersistenceFacade.CalendarListener() {
            @Override
            public void onCalendarReceived(Calendar calendar) {
                ControllerActivity.this.houseController.getHouse().loadCalendar(calendar); // set the activity's calendar to the one retrieved from the database
                Log.e("TEST", "loaded CALENDER ");

            }
        });

        this.persistenceFacade.retrieveHousemateList(new IPersistenceFacade.HousematesListListener() {
            @Override
            public void onHousemateListReceived(List<Housemate> housemateList) {
                ControllerActivity.this.houseController.getHouse().loadHousemates(housemateList);
            }
        });

        this.persistenceFacade.retrievePurchaseList(new IPersistenceFacade.PurchaseListListener() {
            @Override
            public void onPurchaseListReceived(List<LineItem> purchaseList) {
                ControllerActivity.this.houseController.getHouse().loadPurchasedList(purchaseList);
                // set the activity's purchase list to the one retrieved from the database
            }
        });

        this.persistenceFacade.retrieveDebtList(new IPersistenceFacade.DebtListListener() {
            @Override
            public void onDebtListReceived(List<Debt> debtList) {
                ControllerActivity.this.houseController.getHouse().loadDebtList(debtList);
                ControllerActivity.this.debtlistSize = debtList.size();
                //ControllerActivity.this.persistenceFacade.resetDebtList();
            }
        });
    }
    /* ILoginScreenFragment.Listener realization end */

    /* IAuthView.Listener realization start */
    @Override
    public void onRegister(String username, String password, IAuthView authView) {
        Housemate user = new Housemate(username, password); // our tentative user
        this.persistenceFacade.createUserIfNotExists(user, new IPersistenceFacade.BinaryResultListener() {
            @Override
            public void onYesResult() {
                houseController.getHouse().getHousemates().add(user);
                authView.onRegisterSuccess(); }

            @Override
            public void onNoResult() { authView.onUserAlreadyExists(); }
        });
    }

    @Override
    public void onSigninAttempt(String username, String password, IAuthView authView) {

        this.persistenceFacade.retrieveUser(username, new IPersistenceFacade.DataListener<Housemate>() {
            @Override
            public void onDataReceived(@NonNull Housemate user) {
                if (user.validatePassword(password)){ // password matches
                    ControllerActivity.this.curUser = user; // we have a new user
                    houseController.setUser(user);
                    // navigate to ledger screen
                    ControllerActivity.this.mainView.displayFragment(new HomeScreenFragment(ControllerActivity.this));


                } else authView.onInvalidCredentials(); // let the view know things didn't work out
            }

            @Override
            public void onNoDataFound() { // means username does not exist
                authView.onInvalidCredentials(); // let the view know things didn't work out
            }
        });
    }
    /* IAuthView.Listener realization end */


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
        Log.e("TEST", "SL AT OPEN SCREEN:" + houseController.getHouse().getShoppingList());
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

        //remove from data base
        houseController.getHouse().getShoppingList().remove(lineitem);
        shoppingListScreenView.updateDisplay(houseController.getHouse().getShoppingList());
        shoppingListScreenView.updatePurchasedList(houseController.getHouse().getPurchasedItems());
        this.persistenceFacade.saveLineItemPL(lineitem);


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
                                List<Housemate> interestedHMs, IAddItemView addItemView) {
            houseController.addLineItemToShoppingList(quantity, name, price, interestedHMs);
            addItemView.updateDisplay(houseController.getHouse().getShoppingList());
            this.persistenceFacade.saveLineItem(new LineItem(quantity, name, price, interestedHMs));
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
                                 List<Housemate> interestedHMs, String rec, Date endDate,
                                 IAddEventView addEventView) {
            Date startDate = date;
            Recurrence recurrence = new Recurrence(rec, startDate, endDate);
            houseController.addEventToCalendar(name, date, startTime, endTime, interestedHMs,
                    recurrence);

            addEventView.updateDisplay(houseController.getHouse().getCalendar());
            addEventToDatabase(name, date, startTime, endTime, interestedHMs,
                    recurrence);
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

    }

/*    @Override
    public void onAddHousemate() {
        this.mainView.displayFragment(new AddHousemateFragment(this));
    }*/

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
 /*
    @Override
        public void onAddHousemateOnHousemateListScreen(String name, String password) {
           houseController.addHousemate(new Housemate(name, password));
           this.persistenceFacade.saveHousemate(new Housemate(name, password));
       }
      */


    @Override
    public void onAddHousemate(String name) {

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
        //remove all items from data base
        this.persistenceFacade.onCheckOut();
        //update housemates debt list
        this.persistenceFacade.updateHousemateDebt(houseController.getHousemates());
        //save debt list -> transaction screen
        this.persistenceFacade.saveDebtList(houseController.getHouse().getHousedebt(), this.debtlistSize);
        this.debtlistSize = houseController.getHouse().getHousedebt().size();
    }

    @Override
    public void onShoppingListReceived(ShoppingList shoppingList) {
        houseController.loadShoppList(shoppingList);
    }

    @Override
    public void onCalendarReceived(Calendar calendar) {
        houseController.loadCalendar(calendar);
    }

    public void addEventToDatabase(String name, Date date, Time startTime, Time endTime,
                                  List<Housemate> interestedHMs, Recurrence recurrence) {
        Event newevent;
        Date incDate = recurrence.getStartDate();
        Long longDate;
        switch (recurrence.getFrequency()) {
            case "Daily":
                while (incDate.before(recurrence.getEndDate())) {
                    if (interestedHMs == null) {
                        newevent = new Event(name, incDate, startTime, endTime,
                                new ArrayList<Housemate>());
                    } else {
                        newevent = new Event(name, incDate, startTime, endTime, interestedHMs);
                    }
                    this.persistenceFacade.saveEvent(newevent);
                    longDate = incDate.getTime() + 86400000L;
                    incDate = new Date(longDate);
                }
                break;
            case "Weekly":
                while (incDate.before(recurrence.getEndDate())) {
                    if (interestedHMs == null) {
                        newevent = new Event(name, incDate, startTime, endTime,
                                new ArrayList<Housemate>());
                    } else {
                        newevent = new Event(name, incDate, startTime, endTime, interestedHMs);
                    }
                    this.persistenceFacade.saveEvent(newevent);
                    longDate = incDate.getTime() + 604800000L;
                    incDate = new Date(longDate);
                }
                break;
            default:
                if (interestedHMs == null) {
                    newevent = new Event(name, date, startTime, endTime,
                            new ArrayList<Housemate>());
                } else {
                    newevent = new Event(name, date, startTime, endTime, interestedHMs);
                }
                this.persistenceFacade.saveEvent(newevent);
        }
    }

    @Override
    public void onDebtListReceived(List<Debt> debtList) {
        this.houseController.loadDebtList(debtList);
        ControllerActivity.this.debtlistSize = debtList.size();
        //this.persistenceFacade.resetDebtList();
    }
}