package edu.vassar.cmpu.test.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LineItem implements Serializable {


    private int quantity;
    private List<Housemate> interestedHouseMates;
    private Housemate purchaser;
    private Item item;

    public LineItem(){
        item = new Item("NA", 0.0f);
        this.quantity = 0;
        this.interestedHouseMates = new ArrayList<>();
    }

    public LineItem(int quantity, String name, List<Housemate> interestedHouseMates){
        item = new Item(name);
        this.quantity = quantity;
        this.interestedHouseMates = interestedHouseMates;
    }

    public LineItem(int quantity, String name, float price,
                    List<Housemate> interestedHouseMates){
        item = new Item(name, price);
        this.quantity = quantity;
        this.interestedHouseMates = interestedHouseMates;
    }

    public int getQuantity() {
        return quantity;
    }
    public void addPurchaser(Housemate purchaser){
        this.purchaser = purchaser;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<Housemate> getInterestedHouseMates() {
        return interestedHouseMates;
    }

    public Housemate getPurchaser() {
        return purchaser;
    }

    public void setPurchaser(Housemate purchaser) {
        this.purchaser = purchaser;
    }

    public float getPrice(){
        return item.getPrice();
    }

    public void setPrice(float price){
        item.setPrice(price);
    }

    public String getName(){
        return item.getName();
    }

    public void setName(String name){
        item.setName(name);
    }


    public String toString(){
        return "x" + this.quantity + " " + item.toString();
    }

}
