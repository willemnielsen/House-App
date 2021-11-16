package edu.vassar.cmpu.test.domain;

import java.util.ArrayList;

public class ShoppingList {

    private ArrayList<LineItem> shoppingList = new ArrayList<LineItem>();


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
            list += lineItem.toString() + " #of HM " + lineItem.getInterestedHouseMates().size() + "\n";
        }
        return list;
    }


}
