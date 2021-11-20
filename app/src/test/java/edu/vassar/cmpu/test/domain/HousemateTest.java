package edu.vassar.cmpu.test.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class HousemateTest {

    @Test
    void myTransactions() {
        ArrayList<Housemate> hmList = new ArrayList<>();
        Housemate A = new Housemate("A", "2");
        Housemate B = new Housemate("B", "3");
        hmList.add(A);
        LineItem LI = new LineItem(10, "Apple", 1.4f, hmList);
        Debt d1 = new Debt(A, B , 20, LI);
        Debt d2 = new Debt(B, A , 40, LI);
        A.debtlist.add(d1);
        A.debtlist.add(d2);
        assertEquals(A.myTransactions(), "You owe A 20.0 for 10 Apple(s).\nYou owe B 40.0 for 10 Apple(s).\n" );
        assertNotEquals(A.myTransactions(), "");
    }

    @Test
    void myBalance() {
        ArrayList<Housemate> hmList = new ArrayList<>();
        Housemate A = new Housemate("A", "2");
        Housemate B = new Housemate("B", "3");
        hmList.add(A);
        LineItem LI = new LineItem(10, "Apple", 1.4f, hmList);
        LineItem LI2 = new LineItem(10, "Grapes", 1.4f, hmList);
        Debt d1 = new Debt(A, B , 20, LI);
        Debt d2 = new Debt(B, A , 40, LI2);
        A.debtlist.add(d1);
        A.debtlist.add(d2);
        assertEquals(B.myBalance(), "Your balance is 0.0.");
        assertEquals(A.myBalance(), "Your balance is -20.0.");
        assertNotEquals(B.myBalance(), A.myBalance());
    }

    @Test
    void getName() {
        Housemate A = new Housemate("A", "2");
        assertEquals(A.getName(), "A");
    }

    @Test
    void getDebtlist() {
        ArrayList<Housemate> hmList = new ArrayList<>();
        Housemate A = new Housemate("A", "2");
        Housemate B = new Housemate("B", "3");
        hmList.add(A);
        LineItem LI = new LineItem(10, "Apple", 1.4f, hmList);
        Debt d1 = new Debt(A, B , 20, LI);
        A.debtlist.add(d1);
        B.debtlist.add(d1);
        assertEquals(A.getDebtlist(), B.getDebtlist());
    }

    @Test
    void testToString() {
        Housemate A = new Housemate("A", "2");
        assertEquals(A.toString(), "A");
        assertNotEquals(A.toString(), "B");
    }
}