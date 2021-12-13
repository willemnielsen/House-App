package edu.vassar.cmpu.test.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class HouseTest {

    /**
     * Tests addLineItemToShoppingList() by adding one LineItem.
     */

    @Test
    void addLineItemToShoppingList() {
        House house = new House("house", "password");
        ShoppingList shoppingList = new ShoppingList();
        ArrayList<Housemate> hmList = new ArrayList<Housemate>();
        hmList.add( new Housemate("A", "2"));
        hmList.add( new Housemate("B", "3"));
        house.addLineItemToShoppingList(2, "Apples", 5, hmList);
        assertEquals(house.addLineItemToShoppingList(2, "Apples", 5, hmList), true);
    }

    /**
     * Tests getShoppingListLineItem() by adding one LineItem and getting the item and its string
     * form.
     */
    @Test
    void getShoppingListLineItem() {
        House house = new House("house", "password");
        ShoppingList shoppingList = new ShoppingList();
        ArrayList<Housemate> hmList = new ArrayList<Housemate>();
        hmList.add( new Housemate("A", "2"));
        hmList.add( new Housemate("B", "3"));
        house.addLineItemToShoppingList(2, "Apples", 5, hmList);
        assertNotEquals(house.getShoppingListLineItem(0), null);
        assertEquals(house.getShoppingListLineItem(0).toString(), "x2 Apples for $5.0");
    }
    /**
     * Tests getShoppingListSize() by adding two LineItem and calling the function.
     */
    @Test
    void getShoppingListSize() {
        House house = new House("house", "password");
        ShoppingList shoppingList = new ShoppingList();
        ArrayList<Housemate> hmList = new ArrayList<Housemate>();
        hmList.add( new Housemate("A", "2"));
        hmList.add( new Housemate("B", "3"));
        house.getShoppingList().addItem(2, "Apples", 5, hmList);
        house.getShoppingList().addItem(1,"Carrots", 2,hmList);
        assertEquals(house.getShoppingListSize(), 2);
        assertNotEquals(house.getShoppingListSize(), 3);
    }
    /**
     * Tests addHousemate() by adding a Housemate and calling the function.
     */
    @Test
    void addHousemate() {
        House house = new House("house", "password");
        Housemate A = new Housemate("A", "2");
        assertEquals(house.addHousemate(A), "A successfully added to this house.");
        assertNotEquals(house.addHousemate(A), "");
    }
    /**
     * Tests removeHousemate() by adding a Housemate and removing it with the function.
     * Tried with a housemate not in the house too.
     */
    @Test
    void removeHousemate() {
        House house = new House("house", "password");
        Housemate B = new Housemate("B", "3");
        house.addHousemate(B);
        Housemate A = new Housemate("A", "2");
        assertEquals(house.removeHousemate(A), "A is not a member of this house.");
        assertEquals(house.removeHousemate(B), "B successfully removed from this house.");
    }

    /**
     * Tests createDebtForIHM() by creating the debt for one item
     * since doesn't return anything / void, we'll look at the houseTransactions
     */
    @Test
    void createDebtForIHM() {
        House house = new House("house", "password");
        ArrayList<Housemate> hmList = new ArrayList<>();
        Housemate A = new Housemate("A", "2");
        Housemate B = new Housemate("B", "3");
        house.addHousemate(A);
        house.addHousemate(B);
        hmList.add(A);
        hmList.add(B);
        house.getShoppingList().addItem(2, "Apples", 5, hmList);
        house.getShoppingList().addItem(2, "Grapes", 5, hmList);
        house.getShoppingListLineItem(0).setPurchaserAuthKey(A.getAuthKey());
        house.createDebtForIHM(house.getShoppingListLineItem(0));
        //since doesn't return anything / void, we'll look at the houseTransactions
        assertEquals(house.houseTransactions(), "A paid 5.0 for 2 Apples(s).\n" +
                "B owes A 5.0 for 2 Apples(s).\n");
    }

    /**
     * Tests createDebtForHH() by creating the debt for one item
     * since doesn't return anything / void, we'll look at the houseTransactions
     */
    @Test
    void createDebtForHH() {
        House house = new House("house", "password");
        ArrayList<Housemate> hmList = new ArrayList<>();
        Housemate A = new Housemate("A", "2");
        Housemate B = new Housemate("B", "3");
        house.addHousemate(A);
        house.addHousemate(B);
        hmList.add(A);
        hmList.add(B);
        house.getShoppingList().addItem(2, "Apples", 5, hmList);
        house.getShoppingList().addItem(2, "Grapes", 5, hmList);
        house.getShoppingListLineItem(0).setPurchaserAuthKey(A.getAuthKey());
        house.createDebtForHH(house.getShoppingListLineItem(0));
        //since doesn't return anything / void, we'll look at the houseTransactions
        assertEquals(house.houseTransactions(), "A paid 5.0 for 2 Apples(s).\n" +
                "B owes A 5.0 for 2 Apples(s).\n");
    }

    /**
     * Tests createDebtForMe() by creating the debt for one item
     * since doesn't return anything / void, we'll look at the houseTransactions
     */
    @Test
    void createDebtForMe() {
        House house = new House("house", "password");
        ArrayList<Housemate> hmList = new ArrayList<>();
        Housemate A = new Housemate("A", "2");
        Housemate B = new Housemate("B", "3");
        house.addHousemate(A);
        house.addHousemate(B);
        hmList.add(A);
        hmList.add(B);
        house.getShoppingList().addItem(2, "Apples", 5, hmList);
        house.getShoppingList().addItem(2, "Grapes", 5, hmList);
        house.getShoppingListLineItem(0).setPurchaserAuthKey(A.getAuthKey());
        house.createDebtForMe(house.getShoppingListLineItem(0));
        //since doesn't return anything / void, we'll look at the houseTransactions
        assertEquals(house.houseTransactions(), "A paid 10.0 for 2 Apples(s).\n");
    }

    /**
     * Tests createDebtForIHM() by creating the debt for two items
     * since doesn't return anything / void, we'll make sure the purchase list is empty a
     * after calling the function since its supposed to do that
     */
    @Test
    void checkout() {
        House house = new House("house", "password");
        ArrayList<Housemate> hmList = new ArrayList<>();
        Housemate A = new Housemate("A", "2");
        Housemate B = new Housemate("B", "3");
        house.addHousemate(A);
        house.addHousemate(B);
        hmList.add(A);
        LineItem LI = new LineItem(2, "Apples", 5, hmList);
        LineItem LI2 = new LineItem(5, "Grapes", 4, hmList);
        house.getPurchasedItems().add(LI2);
        house.getPurchasedItems().add(LI);
        house.checkout("Charge Me", A);
        // since doesn't return anything / void, we'll make sure the purchase list is empty
        assertTrue(house.getPurchasedItems().isEmpty());
    }

    /**
     * Tests houseTransactions() by creating the debt for two items and making sure the
     * transaction is correct
     */

    @Test
    void houseTransactions() {
        House house = new House("house", "password");
        ArrayList<Housemate> hmList = new ArrayList<>();
        Housemate A = new Housemate("A", "2");
        Housemate B = new Housemate("B", "3");
        house.addHousemate(A);
        house.addHousemate(B);
        hmList.add(A);
        LineItem LI = new LineItem(2, "Apples", 5, hmList);
        LineItem LI2 = new LineItem(5, "Grapes", 4, hmList);
        house.getPurchasedItems().add(LI2);
        house.getPurchasedItems().add(LI);
        house.checkout("Charge Me", A);
        assertNotEquals(house.houseTransactions(),"");
        assertEquals(house.houseTransactions(),"A paid 20.0 for 5 Grapes(s).\n" +
                "A paid 10.0 for 2 Apples(s).\n");
    }

    /**
     * Tests houseBalance() by creating the debt for two items and making sure the
     * balance is correct
     */
    @Test
    void houseBalance() {
        House house = new House("house", "password");
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