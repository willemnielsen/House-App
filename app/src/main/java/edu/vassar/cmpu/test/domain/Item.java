package edu.vassar.cmpu.test.domain;

import java.io.Serializable;

public class Item implements Serializable {

    private String name;
    private float price;

    /**
     * Class constructor with just name
     */
    public Item(String name){
        this.name = name;
    }

    /**
     * Class constructor with name and price
     */
    public Item(String name, float price){
        this.name = name;
        this.price = price;
    }

    /**
     * Setter for the item price
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Retrieves the item price
     */
    public float getPrice() {
        return price;
    }

    /**
     * Retrieves the item name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the item name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * converts the item to a string to display
     */
    public String toString(){
        return name + " for $" + price;
    }

}
