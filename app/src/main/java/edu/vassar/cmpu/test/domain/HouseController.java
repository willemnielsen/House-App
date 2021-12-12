package edu.vassar.cmpu.test.domain;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HouseController {

    private House house;
    private Housemate loggedInUser;

    public HouseController(){
        house = new House();
    }

    public HouseController(String houseName, String housePassword){
        house = new House(houseName, housePassword);
    }

    public void addHousemate(Housemate housemate) {
        house.addHousemate(housemate);
    }
    
    public void setUser(Housemate user) {
        loggedInUser = user;
    }

    public void setHouse(House house) {
        this.house = house;
    }
    
    public Housemate getLoggedInUser(){
        return loggedInUser;
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

    public boolean addLineItemToShoppingList(int quantity, String name, float price,
                                             List<Housemate> interestedHouseMates){
        return house.addLineItemToShoppingList(quantity, name, price, interestedHouseMates);
    }

    public int getShoppingListSize(){
        return house.getShoppingListSize();
    }

    public String shoppingListToString(){
        return house.getShoppingList().toString();
    }

    public void loadShoppList(ShoppingList sl){
        this.house.loadShoppingList(sl);
    }

    public void loadDebtList(List<Debt> dl){
        this.house.loadDebtList(dl);
    }


    public void addToPurchase(LineItem lineitem){
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
    public List<Housemate> getHousemates() {
        return house.getHousemates();
    }

    public Event getThisEvent(Event event){
        return house.getCalendar().getThisEvent(event);}

    public boolean addEventToCalendar(String name, Date date, Time startTime, Time endTime,
                                      List<Housemate> housemates, Recurrence recurrence){
        return house.getCalendar().addEvent(name, date, startTime, endTime, housemates, recurrence);
    }

    public void loadCalendar(Calendar calendar){
        this.house.loadCalendar(calendar);
    }

}
