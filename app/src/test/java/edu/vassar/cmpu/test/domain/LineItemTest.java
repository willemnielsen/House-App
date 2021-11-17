package edu.vassar.cmpu.test.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

class LineItemTest {

    ArrayList<Housemate> hmList = (ArrayList<Housemate>) Arrays.asList(new Housemate("A", "2"), new Housemate("B", "2"));

    @Test
    void getQuantity() {
        LineItem LI = new LineItem(10, "name", 1.4f, hmList);
        assertEquals(LI.getQuantity(), 10);
    }

    @Test
    void getPrice() {
        LineItem LI = new LineItem(10, "name", 1.4f, hmList);
        assertEquals(LI.getPrice(), 1.4f);
    }

    @Test
    void getName() {
        LineItem LI = new LineItem(10, "name", 1.4f, hmList);
        assertEquals(LI.getName(), "name");
    }

    @Test
    void testToString() {
        LineItem LI = new LineItem(10, "name", 1.4f, hmList);
        assertEquals(LI.toString(),  "x10 name for 1.4");
    }

}