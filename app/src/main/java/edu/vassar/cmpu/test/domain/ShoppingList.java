package edu.vassar.cmpu.test.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShoppingList implements Serializable {

    private final List<LineItem> shoppingList;
    private static final String SALE_TOTAL = "saleTotal";

    public ShoppingList(){
      this.shoppingList = new ArrayList<LineItem>();
    }

    /** Need other methods
     *
     * @param quantity
     * @param name
     * @param price
     * @param interestedHouseMates
     * @return true if lineitem added to shopping list
     */
    public boolean addItem(int quantity, String name, float price, ArrayList<Housemate> interestedHouseMates){
       if(interestedHouseMates == null ){
           LineItem lineItem = new LineItem(quantity, name, new ArrayList<Housemate>());
           lineItem.setPrice(price);
           shoppingList.add(lineItem);
       } else {
           LineItem lineItem = new LineItem(quantity, name, interestedHouseMates);
           lineItem.setPrice(price);
           shoppingList.add(lineItem);
       }
        return true;
    }

    public void addLineItem(LineItem lineItem){
        this.shoppingList.add(lineItem);
    }


    /**
     * @param i, i is in range of indexes that shopping list has
     */
    public LineItem getShoppingListLineItem(int i) {
        return shoppingList.get(i);
    }

    public void remove(LineItem lineItem) {
        shoppingList.remove(lineItem);
    }

    public int size(){
        return shoppingList.size();
    }

    public void clear(){
        shoppingList.clear();
    }

    public String toString(){
        String list = "";
        for(LineItem lineItem : shoppingList) {
            list += lineItem.toString() + "\n";
        }
        return list;
    }


}
