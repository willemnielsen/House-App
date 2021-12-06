package edu.vassar.cmpu.test.domain;

import java.io.Serializable;

public class Item implements Serializable {

    private String name;
    private float price;


    public Item(String name){
        this.name = name;
    }

    public Item(String name, float price){
        this.name = name;
        this.price = price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String toString(){
        return name + " for $" + price;
    }

}
