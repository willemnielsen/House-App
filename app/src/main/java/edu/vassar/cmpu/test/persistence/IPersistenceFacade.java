package edu.vassar.cmpu.test.persistence;

import edu.vassar.cmpu.test.domain.Event;
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

    void saveLineItem(LineItem lineItem);
    void retrieveShoppingList(ShoppingListListener listener);
    void saveEvent(Event event);
    void retrieveCalendar(CalendarListener listener);

}
