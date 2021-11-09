package edu.vassar.cmpu.test.domain;

import java.util.ArrayList;

public class LineItem {


    private int quantity;
    private ArrayList<Housemate> interestedHouseMates;
    private Housemate purchaser;
    private Item item;

    public LineItem(int quantity, String name, ArrayList<Housemate> interestedHouseMates){
        item = new Item(name);
        this.quantity = quantity;
        this.interestedHouseMates = interestedHouseMates;
    }

    public LineItem(int quantity, String name, float price, ArrayList<Housemate> interestedHouseMates){
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

    public ArrayList<Housemate> getInterestedHouseMates() {
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

    public String toString(){
        return "x" + this.quantity + " " + item.toString();
    }

}
