package edu.vassar.cmpu.test.persistence;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
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
    private static final String HOUSEMATES = "housemates";

    @Override
    public void setHouseName(String houseName){
       HOUSE_NAME = houseName;
    }

    @Override
    public void saveHousemate(Housemate housemate) {
        this.db.collection(HOUSE_NAME).document(HOUSE_NAME).collection(HOUSEMATE_LIST).add(housemate);
    }

    @Override
    public void retrieveHousemateList(HousematesListListener listener) {
        this.db.collection(HOUSE_NAME).document(HOUSE_NAME).collection(HOUSEMATE_LIST).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
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
        this.db.collection(HOUSE_NAME).document(HOUSE_NAME).collection(SHOPPING_LIST).add(lineItem);
    }



    @Override
    public void retrieveShoppingList(ShoppingListListener listener) {

        this.db.collection(HOUSE_NAME).document(HOUSE_NAME).collection(SHOPPING_LIST).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot qsnap) {
                ShoppingList shoppingList = new ShoppingList();
                for (DocumentSnapshot dsnap : qsnap){
                    LineItem lineItem = dsnap.toObject(LineItem.class);
                    shoppingList.addLineItem(lineItem);
                    Log.e("TEST", shoppingList.getShoppingList().toString());
                }
                listener.onShoppingListReceived(shoppingList);
            }
        });
    }

    @Override
    public void saveEvent(Event event) {
        this.db.collection(HOUSE_NAME).document(HOUSE_NAME).collection(CALENDAR).add(event);
    }

    @Override
    public void retrieveCalendar(CalendarListener listener) {

        this.db.collection(HOUSE_NAME).document(HOUSE_NAME).collection(CALENDAR).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
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
        this.db.collection(HOUSE_NAME).document(HOUSE_NAME).collection(PURCHASE_LIST).add(lineItem);

        this.db.collection(HOUSE_NAME).document(HOUSE_NAME).collection(SHOPPING_LIST).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {

            @Override
            public void onSuccess(QuerySnapshot qsnap) {
                String ref = "";
                for(DocumentSnapshot dsnap : qsnap){
                    //finds corresponds lineitem
                    if(dsnap.toObject(LineItem.class).getName().equals(lineItem.getName())){
                        ref = dsnap.getId();
                        db.collection(HOUSE_NAME).document(HOUSE_NAME).collection(SHOPPING_LIST).document(ref).delete();
                        break;
                    }
                }
            }
        });

    }

    /**
     * clear all data from purchased list
     */
    @Override
    public void onCheckOut(){
        this.db.collection(HOUSE_NAME).document(HOUSE_NAME).collection(PURCHASE_LIST).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot qsnap) {
                for(DocumentSnapshot dsnap : qsnap){
                    //removes all lineitems from purchased list
                    db.collection(HOUSE_NAME).document(HOUSE_NAME).collection(PURCHASE_LIST).document(dsnap.getId()).delete();
                }
            }
        });
    }

    /**
     *
     * @param housemates
     * updated debt list for all housemates
     */
    public void updateHousemateDebt(List<Housemate> housemates){
        this.db.collection(HOUSE_NAME).document(HOUSE_NAME).collection(HOUSEMATE_LIST).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot qsnap) {
                for(DocumentSnapshot dsnap : qsnap){
                    for(Housemate hm : housemates){
                        if(dsnap.toObject(Housemate.class).equals(hm)){
                            db.collection(HOUSE_NAME).document(HOUSE_NAME).collection(PURCHASE_LIST)
                                    .document(dsnap.getId()).set(hm);
                            Log.e("TEST for updates" , "came here" + hm.getDebtlist());
                            break;
                        }
                    }
                }
            }
        });
    }

    @Override
    public void retrieveDebtList(DebtListListener listener) {

    }

    @Override
    public void retrievePurchaseList(PurchaseListListener listener) {

        this.db.collection(HOUSE_NAME).document(HOUSE_NAME).collection(PURCHASE_LIST).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
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
    @Override
    public void createUserIfNotExists(@NonNull Housemate user, @NonNull BinaryResultListener listener) {

        this.retrieveUser(user.getUsername(), new DataListener<Housemate>() {
                    @Override
                    public void onDataReceived(@NonNull Housemate user) { // there's data there, so no go
                        listener.onNoResult();
                    }

                    @Override
                    public void onNoDataFound() { // there's no data there, so we can add the user
                        FirestoreFacade.this.setUser(user, listener);
                    }
                }
        );
    }

    private void setUser(@NonNull Housemate user, @NonNull BinaryResultListener listener){
        this.db.collection(HOUSE_NAME).document(HOUSE_NAME).collection(HOUSEMATE_LIST)
                .document(user.getUsername())
                .set(user)
                .addOnSuccessListener( avoid -> listener.onYesResult())
                .addOnFailureListener(e ->
                        Log.w("NextGenPos", "Error retrieving ledger from database",e));
    }

    @Override
    public void retrieveUser(@NonNull String username, @NonNull DataListener<Housemate> listener) {
        this.db.collection(HOUSE_NAME).document(HOUSE_NAME).collection(HOUSEMATE_LIST).
                document(username).get()
                .addOnSuccessListener(dsnap -> {
                    if (dsnap.exists()) { // got some data back
                        Housemate user = dsnap.toObject(Housemate.class);
                        assert (user != null);
                        listener.onDataReceived(user);
                    } else listener.onNoDataFound();  // no username match
                })
                .addOnFailureListener(e ->
                        Log.w("Housemates", "Error retrieving user from database",e));
    }

    @Override
    public void createHouseIfNotExists(@NonNull House house, @NonNull BinaryResultListener listener) {
        setHouseName(house.getName());
        this.retrieveHouse(HOUSE_NAME, new DataListener<House>() {
                    @Override
                    public void onDataReceived(@NonNull House house) { // there's data there, so no go
                        listener.onNoResult();
                    }

                    @Override
                    public void onNoDataFound() { // there's no data there, so we can add the user
                        FirestoreFacade.this.setHouse(house, listener);
                    }
                }
        );
    }

    private void setHouse(@NonNull House house, @NonNull BinaryResultListener listener){
        setHouseName(house.getName());
        this.db.collection(HOUSE_NAME)
                .document(HOUSE_NAME)
                .set(house)
                .addOnSuccessListener( avoid -> listener.onYesResult())
                .addOnFailureListener(e ->
                        Log.w("NextGenPos", "Error retrieving ledger from database",e));
    }

    @Override
    public void retrieveHouse(@NonNull String houseName, @NonNull DataListener<House> listener) {
        this.db.collection(houseName).document(houseName).get()
                .addOnSuccessListener(dsnap -> {
                    if (dsnap.exists()) { // got some data back
                        House house = dsnap.toObject(House.class);
                        assert (house != null);
                        listener.onDataReceived(house);
                    } else listener.onNoDataFound();  // no username match
                })
                .addOnFailureListener(e ->
                        Log.w("Housemates", "Error retrieving house from database",e));
    }

}

