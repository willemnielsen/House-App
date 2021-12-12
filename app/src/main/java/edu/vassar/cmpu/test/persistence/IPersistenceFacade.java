package edu.vassar.cmpu.test.persistence;

import androidx.annotation.NonNull;

import edu.vassar.cmpu.test.domain.Debt;
import edu.vassar.cmpu.test.domain.Event;
import java.util.List;

import edu.vassar.cmpu.test.domain.LineItem;
import edu.vassar.cmpu.test.domain.ShoppingList;
import edu.vassar.cmpu.test.domain.Housemate;
import edu.vassar.cmpu.test.domain.Calendar;
import edu.vassar.cmpu.test.domain.House;

public interface IPersistenceFacade {

    interface DataListener<T>{
        void onDataReceived(@NonNull T data);
        void onNoDataFound();
    }

    interface BinaryResultListener {
        void onYesResult();
        void onNoResult();
    }

    interface ShoppingListListener{
        void onShoppingListReceived(ShoppingList shoppingList);
    }

    interface CalendarListener{
        void onCalendarReceived(Calendar calendar);
    }
    interface PurchaseListListener{
        void onPurchaseListReceived(List<LineItem> purchaseList);
    }
    interface HousematesListListener{
        void onHousemateListReceived(List<Housemate> housemateList);
    }
    interface DebtListListener{
        void onDebtListReceived(List<Debt> debtList);
    }


    void setHouseName(String houseName);


    void saveLineItem(LineItem lineItem);
    void retrieveShoppingList(ShoppingListListener listener);
    void saveEvent(Event event);
    void retrieveCalendar(CalendarListener listener);
    void retrievePurchaseList(PurchaseListListener listener);
    void saveHousemate(Housemate housemate);
    void retrieveHousemateList(HousematesListListener listener);
    void saveLineItemPL(LineItem lineItem);
    void onCheckOut();
    void updateHousemateDebt(List<Housemate> housemates);
    void retrieveDebtList(DebtListListener listener);
    void createUserIfNotExists(@NonNull Housemate user, @NonNull BinaryResultListener listener);
    void retrieveUser(@NonNull String username, @NonNull DataListener<Housemate> listener);

    void createHouseIfNotExists(@NonNull House house, @NonNull BinaryResultListener listener);
    void retrieveHouse(@NonNull String houseName, @NonNull DataListener<House> listener);


}
