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

    /**
     * Tests testAddItem() by creating adding 2 items asserting the toString of item is correct
     */
    @Test
    void testAddItem() {
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

    /**
     * Tests testRemove() by creating adding 2 items and then removing one
     */
    @Test
    void testRemove() {
        this.testAddItem();
        sl.remove(sl.getShoppingListLineItem(0));
        assertEquals(sl.size(), 1);
        assertEquals(sl.getShoppingListLineItem(0).getName(), "Pie");
    }

    /**
     * Tests testToString() by creating adding 2 items asserting the toString of ShoppingList is correct
     */
    @Test
    void testToString() {
        this.testAddItem();
        assertEquals(sl.toString(), "x10 Apple for $20.0" + "\n" + "x5 Pie for $40.0" + "\n");
    }
}