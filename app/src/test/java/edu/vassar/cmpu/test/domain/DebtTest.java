package edu.vassar.cmpu.test.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class DebtTest {
    /**
     * Tests getDebtor() by creating a debt
     */
    @Test
    void getDebtor() {
        ArrayList<Housemate> hmList = new ArrayList<Housemate>();
        Housemate A = new Housemate("A", "2");
        Housemate B = new Housemate("B", "3");
        hmList.add(A);
        LineItem LI = new LineItem(10, "Apple", 1.4f, hmList);
        Debt debt = new Debt(A, B , 20, LI);
        assertEquals(debt.getDebtorAuthKey(), B.getAuthKey());
        assertNotEquals(debt.getDebtorAuthKey(), A.getAuthKey());
    }

    /**
     * Tests getCreditor() by creating a debt
     */
    @Test
    void getCreditor() {
        ArrayList<Housemate> hmList = new ArrayList<Housemate>();
        Housemate A = new Housemate("A", "2");
        Housemate B = new Housemate("B", "3");
        hmList.add(A);
        LineItem LI = new LineItem(10, "Apple", 1.4f, hmList);
        Debt debt = new Debt(A, B , 20, LI);
        assertEquals(debt.getCreditorAuthKey(), A.getAuthKey());
        assertNotEquals(debt.getCreditorAuthKey(), B.getAuthKey());
    }

    /**
     * Tests getItem() by creating a debt
     */
    @Test
    void getItem() {
        ArrayList<Housemate> hmList = new ArrayList<Housemate>();
        Housemate A = new Housemate("A", "2");
        Housemate B = new Housemate("B", "3");
        hmList.add(A);
        LineItem LI = new LineItem(10, "Apple", 1.4f, hmList);
        LineItem LI2 = new LineItem(10, "Grapes", 1.4f, hmList);
        Debt debt = new Debt(A, B , 20, LI);
        assertEquals(debt.getLineItem(), LI);
        assertNotEquals(debt.getLineItem(), LI2);
    }

    /**
     * Tests getOwed() by creating a debt
     */
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