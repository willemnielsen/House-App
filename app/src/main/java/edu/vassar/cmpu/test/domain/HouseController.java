package edu.vassar.cmpu.test.domain;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HouseController {

    private House house;
    private Housemate loggedInUser;

    /**
     * Class constructor.
     */
    public HouseController(){
        house = new House();
    }

    /**
     * Class constructor with houseName and housePassword
     */
    public HouseController(String houseName, String housePassword){
        house = new House(houseName, housePassword);
    }

    /**
     * adds housemate to the house
     * @param housemate     person being added to the house
     */
    public void addHousemate(Housemate housemate) {
        house.addHousemate(housemate);
    }

    /**
     * Sets the housemate that is log in to the app from the device
     * @param user     person using the app
     */

    public void setUser(Housemate user) {
        loggedInUser = user;
    }

    /**
     * Sets the house
     * @param house     the house we're currently in
     */
    public void setHouse(House house) {
        this.house = house;
    }

    /**
     * Returns the housemate that is log in to the app from the device
     */
    public Housemate getLoggedInUser(){
        return loggedInUser;
    }
    
    public void removeHousemate(Housemate housemate){
        house.removeHousemate(housemate);
    }

    /**
     * This method is how the items were purchases and who made the purchases, the person who is logged in
     * @param distribution     how the debt is going to be created
     * @param purchaser        the person buying the line item, the person logged in
     */
    public void checkout(String distribution, Housemate purchaser){
        house.checkout(distribution, purchaser);
    }

    /**
     * This method gets a line item from the house shopping list
     * @param i             index of the line item we're searching for
     */
    public LineItem getShoppingListLineItem(int i){
        return house.getShoppingListLineItem(i);
    }

    /**
     * adds line item to the house shopping list
     *
     *   @param quantity
     *   @param name
     *   @param price
     *   @param interestedHouseMates
     *   @return true if lineitem added to shopping list
     */
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

    /**
     *
     * @return the transactions that have taken place with the housemates
     */
    public String houseTransactions(){
        return house.houseTransactions();
    }

    /**
     *
     * @return the balances
     */
    public String houseBalance(){
        return house.houseBalance();
    }

    /**
     *
     * @return the housemates related to house
     */
    public List<Housemate> getHousemates() {
        return house.getHousemates();
    }

    /**
     *
     * @return the given event
     */
    public Event getThisEvent(Event event){
        return house.getCalendar().getThisEvent(event);}

    /**
     * This method adds an event to the house calendar. If the recurrence frequency is daily or weekly it
     * adds multiple different instances of events to the calendar on the recurring dates.
     * @param name              name of event
     * @param date              first date that event occurs
     * @param startTime         start time of event
     * @param endTime           end time of event
     * @param housemates        list of housemates participating in event
     * @param recurrence        type of recurrence associated with this event
     * @return <code>true</code>
     */
    public boolean addEventToCalendar(String name, Date date, Time startTime, Time endTime,
                                      List<Housemate> housemates, Recurrence recurrence){
        return house.getCalendar().addEvent(name, date, startTime, endTime, housemates, recurrence);
    }

    public void loadCalendar(Calendar calendar){
        this.house.loadCalendar(calendar);
    }

}
