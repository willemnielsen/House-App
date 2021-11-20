package edu.vassar.cmpu.test.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class DebtTest {

    @Test
    void getDebtor() {
        ArrayList<Housemate> hmList = new ArrayList<Housemate>();
        Housemate A = new Housemate("A", "2");
        Housemate B = new Housemate("B", "3");
        hmList.add(A);
        LineItem LI = new LineItem(10, "Apple", 1.4f, hmList);
        Debt debt = new Debt(A, B , 20, LI);
        assertEquals(debt.getDebtor(), B);
        assertNotEquals(debt.getDebtor(), A);
    }

    @Test
    void getCreditor() {
        ArrayList<Housemate> hmList = new ArrayList<Housemate>();
        Housemate A = new Housemate("A", "2");
        Housemate B = new Housemate("B", "3");
        hmList.add(A);
        LineItem LI = new LineItem(10, "Apple", 1.4f, hmList);
        Debt debt = new Debt(A, B , 20, LI);
        assertEquals(debt.getCreditor(), A);
        assertNotEquals(debt.getCreditor(), B);
    }

    @Test
    void getItem() {
        ArrayList<Housemate> hmList = new ArrayList<Housemate>();
        Housemate A = new Housemate("A", "2");
        Housemate B = new Housemate("B", "3");
        hmList.add(A);
        LineItem LI = new LineItem(10, "Apple", 1.4f, hmList);
        LineItem LI2 = new LineItem(10, "Grapes", 1.4f, hmList);
        Debt debt = new Debt(A, B , 20, LI);
        assertEquals(debt.getItem(), LI);
        assertNotEquals(debt.getItem(), LI2);
    }

    @Test
    void getOwed() {
        ArrayList<Housemate> hmList = new ArrayList<Housemate>();
        Housemate A = new Housemate("A", "2");
        Housemate B = new Housemate("B", "3");
        hmList.add(A);
        LineItem LI = new LineItem(10, "Apple", 1.4f, hmList);

        Debt debt = new Debt(A, B , 20, LI);
        assertEquals(debt.getOwed(), 20);
    }
}