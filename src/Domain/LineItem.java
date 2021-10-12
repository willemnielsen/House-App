package Domain;

import java.util.ArrayList;

public class LineItem extends Item {


    private int quantity;
    private ArrayList<Housemate> interestedHouseMates;
    public Housemate purchaser;

    public LineItem(int quantity, String name, ArrayList<Housemate> interestedHouseMates){
        super(name);
        this.quantity = quantity;
        this.interestedHouseMates = interestedHouseMates;
    }

    public int getQuantity() {
        return quantity;
    }
    public void addPurchaser(Housemate purchaser){
        this.purchaser = purchaser;
    }
    public void setQuantity(int quanity) {
        this.quantity = quanity;
    }

    public ArrayList<Housemate> getInterestedHouseMates() {
        return interestedHouseMates;
    }

    public String toString(){
        return this.getName();
    }

}
