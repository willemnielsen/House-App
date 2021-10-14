package Domain;

import javax.sound.sampled.Line;
import java.util.ArrayList;

public class HouseController {

    private House house;

    public HouseController(String houseName){
        house = new House(houseName);
    }

    public void addHousemate(Housemate housemate) {
        house.addHousemate(housemate);
    }

    public void removeHousemate(Housemate housemate){
        house.removeHousemate(housemate);
    }


    public void checkout(String distribution){
        house.checkout(distribution);
    }

    public LineItem getShoppingListLineItem(int i){
        return house.getShoppingListLineItem(i);
    }

    public boolean addLineItemToShoppingList(int quantity, String name, float price, ArrayList<Housemate> interestedHouseMates){
        return house.addLineItemToShoppingList(quantity, name, price, interestedHouseMates);
    }

    public int getShoppingListSize(){
        return house.getShoppingListSize();
    }

    public String shoppingListToString(){
        return house.getShoppingList().toString();
    }

    public void addToPurchse(LineItem lineitem){
        house.getPurchasedItems().add(lineitem);
    }

    public House getHouse(){
        return house;
    }
}
