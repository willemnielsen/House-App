package edu.vassar.cmpu.test.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class LineItemTest {

    @Test
    void getQuantity() {
        ArrayList<Housemate> hmList = new ArrayList<Housemate>();
        hmList.add( new Housemate("A", "2"));
        hmList.add( new Housemate("B", "3"));
        LineItem LI = new LineItem(10, "name", 1.4f, hmList);
        assertEquals(LI.getQuantity(), 10);
    }

    @Test
    void getPrice() {
        ArrayList<Housemate> hmList = new ArrayList<Housemate>();
        hmList.add( new Housemate("A", "2"));
        hmList.add( new Housemate("B", "3"));
        LineItem LI = new LineItem(10, "name", 1.4f, hmList);
        assertEquals(LI.getPrice(), 1.4f);
    }

    @Test
    void getName() {
        ArrayList<Housemate> hmList = new ArrayList<Housemate>();
        hmList.add( new Housemate("A", "2"));
        hmList.add( new Housemate("B", "3"));
        LineItem LI = new LineItem(10, "name", 1.4f, hmList);
        assertEquals(LI.getName(), "name");
    }

    @Test
    void testToString() {
        ArrayList<Housemate> hmList = new ArrayList<Housemate>();
        hmList.add( new Housemate("A", "2"));
        hmList.add( new Housemate("B", "3"));
        LineItem LI = new LineItem(10, "name", 1.4f, hmList);
        assertEquals(LI.toString(),  "x10 name for 1.4");
    }

}