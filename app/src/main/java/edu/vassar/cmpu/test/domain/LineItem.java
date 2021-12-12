package edu.vassar.cmpu.test.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LineItem implements Serializable {


    private int quantity;

    private List<AuthKey> interestedHouseMatesAuthKet;
    private AuthKey purchaserAuthKey;
    private Item item;

    public LineItem(){
        item = new Item("NA", 0.0f);
        this.quantity = 0;
        this.interestedHouseMatesAuthKet = new ArrayList<>();
    }

    public LineItem(int quantity, String name, List<Housemate> interestedHouseMates){
        item = new Item(name);
        this.quantity = quantity;
        this.interestedHouseMatesAuthKet = new ArrayList<>();
        for(Housemate hm : interestedHouseMates){
            this.interestedHouseMatesAuthKet.add(hm.getAuthKey());
        }
    }

    public LineItem(int quantity, String name, float price,
                    List<Housemate> interestedHouseMates){
        item = new Item(name, price);
        this.quantity = quantity;
        this.interestedHouseMatesAuthKet = new ArrayList<>();
        for(Housemate hm : interestedHouseMates){
            this.interestedHouseMatesAuthKet.add(hm.getAuthKey());
        }
    }

    public int getQuantity() {
        return quantity;
    }
    public void addPurchaser(Housemate purchaser){
        this.purchaserAuthKey = purchaser.getAuthKey();
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<AuthKey> getInterestedHouseMatesAuthKet() {
        return interestedHouseMatesAuthKet;
    }

    public void setInterestedHouseMatesAuthKet(List<AuthKey> interestedHouseMatesAuthKet) {
        this.interestedHouseMatesAuthKet = interestedHouseMatesAuthKet;
    }

    public AuthKey getPurchaserAuthKey() {
        return purchaserAuthKey;
    }

    public void setPurchaserAuthKey(AuthKey purchaserAuthKey) {
        this.purchaserAuthKey = purchaserAuthKey;
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

    /*
    public boolean equal0s(Object o){
        if(!(o instanceof LineItem)){
            return false;
        }
        LineItem li = (LineItem) o;
        if(this.interestedHouseMatesAuthKet.size() != li.interestedHouseMatesAuthKet.size()){
            return false;
        }
        for(int i = 0; i < this.interestedHouseMatesAuthKet.size(); i++){
            if(!this.interestedHouseMatesAuthKet.get(i).equals(li.interestedHouseMatesAuthKet.get(i))){
                return false;
            }
        }
        return this.getPurchaserAuthKey().equals(li.getPurchaserAuthKey())
                && this.toString().equals(li.toString());

    }

     */
}
