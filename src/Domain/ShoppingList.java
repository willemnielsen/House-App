package Domain;

import Domain.LineItem;

import java.util.ArrayList;

public class ShoppingList {

    private ArrayList<Item> shoppingList = new ArrayList<Item>();

    public ShoppingList(){

    }

    /** Need other methods
     *
     * @param quantity
     * @param name
     * @param price
     * @param interestedHouseMates
     * @return true if lineitem added to shopping list
     */
    public boolean addItem(int quantity, String name,float price, ArrayList<Housemate> interestedHouseMates){
        LineItem lineItem = new LineItem(quantity, name, interestedHouseMates);
        lineItem.setPrice(price);
        shoppingList.add(lineItem);
        return true;
    }





}
