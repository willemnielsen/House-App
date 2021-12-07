package edu.vassar.cmpu.test.persistence;

import edu.vassar.cmpu.test.domain.Event;
import java.util.List;

import edu.vassar.cmpu.test.domain.LineItem;
import edu.vassar.cmpu.test.domain.ShoppingList;
import edu.vassar.cmpu.test.domain.Housemate;
import edu.vassar.cmpu.test.domain.Calendar;
import edu.vassar.cmpu.test.domain.House;

public interface IPersistenceFacade {

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


    void setHouseName(String houseName);


    void saveLineItem(LineItem lineItem);
    void retrieveShoppingList(ShoppingListListener listener);
    void saveEvent(Event event);
    void retrieveCalendar(CalendarListener listener);
    void retrievePurchaseList(PurchaseListListener listener);
    void saveHousemate(Housemate housemate);
    void retrieveHousemateList(HousematesListListener listener);
    void saveLineItemPL(LineItem lineItem);

}
