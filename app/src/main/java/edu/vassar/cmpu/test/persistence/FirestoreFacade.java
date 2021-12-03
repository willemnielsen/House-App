package edu.vassar.cmpu.test.persistence;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import edu.vassar.cmpu.test.domain.LineItem;
import edu.vassar.cmpu.test.domain.ShoppingList;
import edu.vassar.cmpu.test.domain.Housemate;
import edu.vassar.cmpu.test.domain.Calendar;
import edu.vassar.cmpu.test.domain.House;

public class FirestoreFacade implements IPersistenceFacade {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private static final String SHOPPING_LIST = "shopping list";


    @Override
    public void saveLineItem(LineItem lineItem) {
        db.collection(SHOPPING_LIST).add(lineItem);
    }

    @Override
    public void retrieveShoppingList(ShoppingListListener listener) {

        db.collection(SHOPPING_LIST).
                get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
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
