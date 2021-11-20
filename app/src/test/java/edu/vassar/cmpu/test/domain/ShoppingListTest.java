package edu.vassar.cmpu.test.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class ShoppingListTest {

    ArrayList<Housemate> hmList;
    ShoppingList sl;
    LineItem apple;
    LineItem pie;

    @Test
    void addItem() {
        hmList = new ArrayList<Housemate>();
        hmList.add( new Housemate("A", "2"));
        hmList.add( new Housemate("B", "3"));
        sl = new ShoppingList();

        ArrayList<Housemate> hm1List = new ArrayList<Housemate>();
        hm1List.add(hmList.get(0));
        apple = new LineItem(10, "Apple", 20.0f, hm1List);
        pie = new LineItem(5, "Pie", 40.0f, hmList);
        sl.addItem(apple.getQuantity(), apple.getName(), apple.getPrice(), apple.getInterestedHouseMates());
        sl.addItem(pie.getQuantity(), pie.getName(), pie.getPrice(), pie.getInterestedHouseMates());

        assertEquals(sl.getShoppingListLineItem(0).toString(), "x10 Apple for $20.0");

        assertEquals(sl.getShoppingListLineItem(1).toString(), "x5 Pie for $40.0");

    }

    @Test
    void remove() {
        this.addItem();
        sl.remove(sl.getShoppingListLineItem(0));
        assertEquals(sl.size(), 1);
        assertEquals(sl.getShoppingListLineItem(0).getName(), "Pie");
    }

    @Test
    void testToString() {
        this.addItem();
        assertEquals(sl.toString(), "x10 Apple for $20.0" + "\n" + "x5 Pie for $40.0" + "\n");
    }
}