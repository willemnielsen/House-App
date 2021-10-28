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


    public void checkout(String distribution, Housemate purchaser){
        house.checkout(distribution, purchaser);
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


    /**
     *
     * @return object with corresponding name else null
     */
    public Housemate getHousemate(String name){
        for(Housemate housemate : house.getHousemates()){
            if(housemate.getName().equals(name)){
                return housemate;
            }
        }
        return null;
    }

    public String houseTransactions(){
        return house.houseTransactions();
    }

    public String houseBalance(){
        return house.houseBalance();
    }


    public static String convertPurchaseToString(ArrayList<LineItem> lIL){
        String list = "Items {\n";
        for(LineItem lineItem : lIL) {
            list += "\t" + lineItem.toString() + "\n";
        }
        return list + "}\n";
    }

    public static String convertHouseMatesToString(ArrayList<Housemate> h){
        String list = "HouseMates {\n";
        for(Housemate housemate : h) {
            list += "\t" + housemate.toString() + "\n";
        }
        return list + "}\n";
    }


    public ArrayList<Housemate> getHousemates() {
        return house.getHousemates();
    }
}
