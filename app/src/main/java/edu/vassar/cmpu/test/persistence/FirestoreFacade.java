package edu.vassar.cmpu.test.persistence;

import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import edu.vassar.cmpu.test.domain.Event;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import edu.vassar.cmpu.test.domain.LineItem;
import edu.vassar.cmpu.test.domain.ShoppingList;
import edu.vassar.cmpu.test.domain.Housemate;
import edu.vassar.cmpu.test.domain.Calendar;
import edu.vassar.cmpu.test.domain.House;

public class FirestoreFacade implements IPersistenceFacade {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public String HOUSE_NAME = "Default";
    private static final String PURCHASE_LIST = "Purchase list";
    private static final String SHOPPING_LIST = "shopping list";
    private static final String CALENDAR = "calendar";
    private static final String HOUSEMATE_LIST = "housemate list";

    @Override
    public void setHouseName(String houseName){
       HOUSE_NAME = houseName;
    }

    @Override
    public void saveHousemate(Housemate housemate) {
        db.collection(HOUSE_NAME).document(HOUSE_NAME).collection(HOUSEMATE_LIST).add(housemate);
    }

    @Override
    public void retrieveHousemateList(HousematesListListener listener) {
        db.collection(HOUSE_NAME).document(HOUSE_NAME).collection(HOUSEMATE_LIST).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot qsnap) {
                List<Housemate> housemateList = new ArrayList<>();
                for (DocumentSnapshot dsnap : qsnap){
                    Housemate housemate = dsnap.toObject(Housemate.class);
                    housemateList.add(housemate);
                }
                listener.onHousemateListReceived(housemateList);
            }
        });
    }

    @Override
    public void saveLineItem(LineItem lineItem) {
        db.collection(HOUSE_NAME).document(HOUSE_NAME).collection(SHOPPING_LIST).add(lineItem);
    }

    @Override
    public void retrieveShoppingList(ShoppingListListener listener) {

        db.collection(HOUSE_NAME).document(HOUSE_NAME).collection(SHOPPING_LIST).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot qsnap) {
                ShoppingList shoppingList = new ShoppingList();
                for (DocumentSnapshot dsnap : qsnap){
                    LineItem lineItem = dsnap.toObject(LineItem.class);
                    shoppingList.addLineItem(lineItem);
                }
                listener.onShoppingListReceived(shoppingList);
            }
        });
    }

    @Override
    public void saveEvent(Event event) {
        db.collection(HOUSE_NAME).document(HOUSE_NAME).collection(CALENDAR).add(event);
    }

    @Override
    public void retrieveCalendar(CalendarListener listener) {

        db.collection(HOUSE_NAME).document(HOUSE_NAME).collection(CALENDAR).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot qsnap) {
                Calendar calendar = new Calendar();
                for (DocumentSnapshot dsnap : qsnap){
                    Log.i("test", dsnap.toString());
                    Event event = dsnap.toObject(Event.class);
                    calendar.addThisEvent(event);
                }
                listener.onCalendarReceived(calendar);
            }
        });
    }

    @Override
    public void saveLineItemPL(LineItem lineItem) {
        db.collection(HOUSE_NAME).document(HOUSE_NAME).collection(PURCHASE_LIST).add(lineItem);
    }
    @Override
    public void retrievePurchaseList(PurchaseListListener listener) {

        db.collection(HOUSE_NAME).document(HOUSE_NAME).collection(PURCHASE_LIST).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot qsnap) {
                List<LineItem> purchaseList = new ArrayList<>();
                for (DocumentSnapshot dsnap : qsnap){
                    LineItem lineItem = dsnap.toObject(LineItem.class);
                    purchaseList.add(lineItem);
                }
                listener.onPurchaseListReceived(purchaseList);
            }
        });
    }
}

