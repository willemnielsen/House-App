package edu.vassar.cmpu.test.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class ItemTest {
    /**
     * Tests getPrice() by creating a item and asserting the price is correct
     */
    @Test
    void getPrice() {
        Item e = new Item("name", 23.43f);
        assertEquals(e.getPrice(), 23.43f);

        Item e2 = new Item("name");
        assertEquals(e2.getPrice(), 0.0f);
    }
    /**
     * Tests getName() by creating a item and asserting the name is correct
     */
    @Test
    void getName() {
        Item e = new Item("name", 23.43f);
        assertEquals(e.getName(), "name");
    }

    /**
     * Tests testToString() by creating a item and asserting the string is correct
     */
    @Test
    void testToString() {
        Item e = new Item("name", 23.43f);
        assertEquals(e.toString(), "name for $23.43");
    }
}