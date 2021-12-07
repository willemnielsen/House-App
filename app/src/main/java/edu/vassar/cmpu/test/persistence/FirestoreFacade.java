package edu.vassar.cmpu.test.persistence;

import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

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
    private static final String SHOPPING_LIST = "shopping list";
    private static final String HOUSEMATE_LIST = "housemate list";

    @Override
    public void setHouseName(String houseName){
        HOUSE_NAME = houseName;
    }

    @Override
    public void saveHousemate(Housemate housemate) {
        db.collection(HOUSEMATE_LIST).add(housemate);
    }

    @Override
    public void retrieveHousemateList(HousematesListListener listener) {
        db.collection(HOUSEMATE_LIST).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
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
        db.collection(SHOPPING_LIST).add(lineItem);
    }

    @Override
    public void retrieveShoppingList(ShoppingListListener listener) {

        db.collection(SHOPPING_LIST).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
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
}

