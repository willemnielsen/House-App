import java.util.ArrayList;

public class LineItem extends Item{


    private int quantity;
    private ArrayList<Housemate> interestedHouseMates;


    public LineItem(int quantity, String name, ArrayList<Housemate> interestedHouseMates){
        super(name);
        this.quantity = quantity;
        this.interestedHouseMates = interestedHouseMates;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quanity) {
        this.quantity = quanity;
    }

    public ArrayList<Housemate> getInterestedHouseMates() {
        return interestedHouseMates;
    }
}
