package edu.vassar.cmpu.test.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class HouseTest {

    @Test
    void addLineItemToShoppingList() {
        House house = new House("house");
        ShoppingList shoppingList = new ShoppingList();
        ArrayList<Housemate> hmList = new ArrayList<Housemate>();
        hmList.add( new Housemate("A", "2"));
        hmList.add( new Housemate("B", "3"));
        house.addLineItemToShoppingList(2, "Apples", 5, hmList);
        assertEquals(house.addLineItemToShoppingList(2, "Apples", 5, hmList), true);
    }

    @Test
    void getShoppingListLineItem() {
    }

    @Test
    void getShoppingListSize() {
        House house = new House("house");
        ShoppingList shoppingList = new ShoppingList();
        ArrayList<Housemate> hmList = new ArrayList<Housemate>();
        hmList.add( new Housemate("A", "2"));
        hmList.add( new Housemate("B", "3"));
        house.getShoppingList().addItem(2, "Apples", 5, hmList);
        house.getShoppingList().addItem(1,"Carrots", 2,hmList);
        assertEquals(house.getShoppingListSize(), 2);
        assertNotEquals(house.getShoppingListSize(), 3);
    }

    @Test
    void getShoppingList() {
    }

    @Test
    void getPurchasedItems() {
    }

    @Test
    void addHousemate() {
    }

    @Test
    void removeHousemate() {
    }

    @Test
    void createDebtForIHM() {
    }

    @Test
    void createDebtForHH() {
    }

    @Test
    void createDebtForMe() {
    }

    @Test
    void checkout() {
    }

    @Test
    void houseTransactions() {
    }

    @Test
    void houseBalance() {
    }

    @Test
    void getHousemates() {
    }
}