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
        House house = new House("house");
        ShoppingList shoppingList = new ShoppingList();
        ArrayList<Housemate> hmList = new ArrayList<Housemate>();
        hmList.add( new Housemate("A", "2"));
        hmList.add( new Housemate("B", "3"));
        LineItem LI = new LineItem(2, "Apples", 5, hmList);
        house.addLineItemToShoppingList(2, "Apples", 5, hmList);
        assertNotEquals(house.getShoppingListLineItem(0), null);
        assertEquals(house.getShoppingListLineItem(0).toString(), "x2 Apples for $5.0");
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
    void addHousemate() {
        House house = new House("house");
        Housemate A = new Housemate("A", "2");
        assertEquals(house.addHousemate(A), "A successfully added to this house.");
        assertNotEquals(house.addHousemate(A), "");
    }

    @Test
    void removeHousemate() {
        House house = new House("house");
        Housemate B = new Housemate("B", "3");
        house.addHousemate(B);
        Housemate A = new Housemate("A", "2");
        assertEquals(house.removeHousemate(A), "A is not a member of this house.");
        assertEquals(house.removeHousemate(B), "B successfully removed from this house.");
    }

    // TODO: fix 
    @Test
    void createDebtForIHM() {
        House house = new House("house");
        ArrayList<Housemate> hmList = new ArrayList<>();
        Housemate A = new Housemate("A", "2");
        Housemate B = new Housemate("B", "3");
        hmList.add(A);
        house.addHousemate(A);
        house.addHousemate(B);
        LineItem LI = new LineItem(10, "Apple", 1.4f, hmList);
        house.addLineItemToShoppingList(10, "Apple", 1.4f, hmList);
        //A.getDebtlist().add(d1);
        house.createDebtForIHM(house.getShoppingListLineItem(0));
        assertEquals(A.getDebtlist(), "");

    }
    // TODO: fix
    @Test
    void createDebtForHH() {
        House house = new House("house");
        ArrayList<Housemate> hmList = new ArrayList<>();
        Housemate A = new Housemate("A", "2");
        Housemate B = new Housemate("B", "3");
        hmList.add(A);
        house.addHousemate(A);
        house.addHousemate(B);
        LineItem LI = new LineItem(10, "Apple", 1.4f, hmList);
        LineItem LI2 = new LineItem(10, "Grapes", 1.4f, hmList);
        Debt d1 = new Debt(A, B , 20, LI);
    }
    // TODO: fix
    @Test
    void createDebtForMe() {
        House house = new House("house");
        ArrayList<Housemate> hmList = new ArrayList<>();
        Housemate A = new Housemate("A", "2");
        Housemate B = new Housemate("B", "3");
        hmList.add(A);
        house.addHousemate(A);
        house.addHousemate(B);
        LineItem LI = new LineItem(10, "Apple", 1.4f, hmList);
        LineItem LI2 = new LineItem(10, "Grapes", 1.4f, hmList);
        Debt d1 = new Debt(A, B , 20, LI);
    }

    // TODO: fix
    @Test
    void checkout() {
    }

     // TODO: fix
    @Test
    void houseTransactions() {
        House house = new House("house");
        ArrayList<Housemate> hmList = new ArrayList<>();
        Housemate A = new Housemate("A", "2");
        Housemate B = new Housemate("B", "3");
        house.addHousemate(A);
        house.addHousemate(B);
        hmList.add(A);
        house.addLineItemToShoppingList(2, "Apples", 5, hmList);
        house.addLineItemToShoppingList(2, "Grapes", 5, hmList);
        house.checkout("Charge Me", A);
        //assertNotEquals(house.houseTransactions(),"");
    }

    @Test
    void houseBalance() {
        House house = new House("house");
        ArrayList<Housemate> hmList = new ArrayList<>();
        Housemate A = new Housemate("A", "2");
        Housemate B = new Housemate("B", "3");
        house.addHousemate(A);
        house.addHousemate(B);
        LineItem LI = new LineItem(10, "Apple", 1.4f, hmList);
        LineItem LI2 = new LineItem(10, "Grapes", 1.4f, hmList);
        Debt d1 = new Debt(A, B , 20, LI);
        Debt d2 = new Debt(B, A , 40, LI2);
        A.debtlist.add(d1);
        A.debtlist.add(d2);
        assertEquals(house.houseBalance(),"A has a balance of -20.0.\n" +
                "B has a balance of 0.0.\n");
        assertNotEquals(house.houseBalance(),"");
    }


}