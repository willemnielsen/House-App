package edu.vassar.cmpu.test.domain;

import android.util.Log;

import java.io.Serializable;
import java.util.*;

/**
 * Class for the house. Contains the shopping list, calendar, housemates, and record of debt.
 */
public class House implements Serializable {

    private String houseName;
    private AuthKey authKey;
    int houseID = 0;
    private ShoppingList shoppingList;
    private List<Housemate> housemates;
    private List<LineItem> purchasedItems;
    private List<Debt> housedebt;
    private Calendar calendar;

    /**
     * Class constructor.
     */
    public House(){
        this.houseName = "Test";
        this.authKey = new AuthKey("password");
        int houseID = 0;
        this.shoppingList = new ShoppingList();
        this.housemates = new ArrayList<Housemate>();
        this.purchasedItems = new ArrayList<LineItem>();
        this.housedebt = new ArrayList<Debt>();
        this.calendar= new Calendar();
    }

    /**
     * Creates a house object. Uses input name has house name, sets random 6 digit house ID.
     * @param houseName         name for the house
     */
    public House(String houseName, String housePassword) {
        Random random = new Random();
        for (int i = 0; i <= 5; i++) {
            int rand = random.nextInt(10);
            houseID = houseID * 10 + rand;
        }
        this.houseName = houseName;
        this.authKey= new AuthKey(housePassword);
        shoppingList = new ShoppingList();
        housemates = new ArrayList<Housemate>();
        purchasedItems = new ArrayList<LineItem>();
        housedebt = new ArrayList<Debt>();
        calendar = new Calendar();
    }

    /**
     * Returns the authKey for the house
     */
    public AuthKey getAuthKey(){ return this.authKey; }

    /**
     *  Setter for the authKey for the house
     */
    public void setAuthKey(AuthKey authKey) {
        this.authKey = authKey;
    }

    /**
     *  Insures that the password entered is correct
     */
    public boolean validatePassword(String password){
        return this.authKey.validatePassword(password);
    }

    /**
     * Returns the name of the current house
     */
    public String getName(){
        return houseName;
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
        return shoppingList.addItem(quantity, name, price, interestedHouseMates);
    }

    /**
     * Returns the item list from the house shopping list at the index i
     */
    public LineItem getShoppingListLineItem(int i){
        return shoppingList.getShoppingListLineItem(i);
    }

    /**
     * logs when the shopping list loads to the shopping list
     */
    public void loadShoppingList(ShoppingList sl){
        this.shoppingList = sl;
        Log.e("TEST", "SL AT A ON LOAD METHOD"  + this.shoppingList.toString());
    }

    public void loadHousemates(List<Housemate> housemates){this.housemates = housemates;}

    public void loadPurchasedList(List<LineItem> purchasedItems){this.purchasedItems = purchasedItems;}

    /**
     * Returns the size of the house shopping list
     */
    public int getShoppingListSize(){
        return this.shoppingList.size();
    }

    /**
     * Returns the shoppingList object associated to the house
     */
    public ShoppingList getShoppingList(){
        return shoppingList;
    }

    /**
     * Returns the purchasedItems list associated to the house
     */
    public List<LineItem> getPurchasedItems() {
        return purchasedItems;
    }

    /**
     * adds line item to the purchasedItems list
     */
    private void purchaseItem(LineItem lineItem) {
        purchasedItems.add(lineItem);
    }

    /**
     * Adds housemate to house.
     * @param housemate     Housemate to be added
     * @return If housemate is already in the house, returns a string that says so.
     * Else, returns a string that confirms the addition of the housemate.
     */
    public String addHousemate(Housemate housemate) {
        String output;
        if (housemates.contains(housemate)) {
            output = housemate.getName() + " is already a member of this house.";
        } else {
            housemates.add(housemate);
            output = housemate.getName() + " successfully added to this house.";
        }
        return output;
    }

    /**
     * Adds housemate to house.
     * @param housemate     Housemate to be added
     * @return If housemate is successfully removes, returns a string that says so.
     * Else, returns a string that says that the housemate does not exist.
     */
    public String removeHousemate(Housemate housemate) {
        String output;
        if (housemates.contains(housemate)) {
            housemates.remove(housemate);
            output = housemate.getName() + " successfully removed from this house.";
        } else {
            output = housemate.getName() + " is not a member of this house.";
        }
        return output;
    }

    /**
     * Creates one or more debt object(s) for the specified line item and adds this/these object(s)
     * to the house debt. If multiple people are interested in the line item, then one debt object
     * is created for each interested housemate, and the cost is divided evenly amongst them. For
     * each debt object, the purchaser of the line item is the creditor and the interested housemate
     * is the debtor. The amount owed is the price of the line item divided by the number of
     * interested housemates. The debt object is also added to each respective housemate's debt list
     * @param lineItem      the line item that the debt is created for
     */
    public void createDebtForIHM(LineItem lineItem) {
        for (Housemate interHM : getHousemateList(lineItem.getInterestedHouseMatesAuthKet())) {
            Debt newdebt = new Debt(getHousemate(lineItem.getPurchaserAuthKey()), interHM,
                    (lineItem.getPrice()*lineItem.getQuantity())/lineItem.getInterestedHouseMatesAuthKet().size(),
                    lineItem);
            //if(!interHM.getName().equals(lineItem.getPurchaser().getName())) {
            if( !interHM.getAuthKey().equals(lineItem.getPurchaserAuthKey())){
                this.housedebt.add(newdebt);
                interHM.getDebtlist().add(newdebt);
                getHousemate(lineItem.getPurchaserAuthKey()).getDebtlist().add(newdebt);
            }
            else{
                this.housedebt.add(newdebt);
                getHousemate(lineItem.getPurchaserAuthKey()).getDebtlist().add(newdebt);
            }
        }
    }


    /**
     * Does the same thing as createDebtForIHM but instead of dividing the cost evenly amongst
     * the interested housemates, it divides it amongst every housemate in the house.
     * @param lineItem          the line item that the debt is created for
     */

    public void createDebtForHH(LineItem lineItem) {
        for (Housemate currHM : housemates) {
            Debt newdebt = new Debt(getHousemate(lineItem.getPurchaserAuthKey()), currHM,
                    (lineItem.getPrice()*lineItem.getQuantity())/housemates.size(), lineItem);
            //if(!currHM.getName().equals(lineItem.getPurchaser().getName())) {
            if(!currHM.getAuthKey().equals(lineItem.getPurchaserAuthKey())){
                this.housedebt.add(newdebt);
                currHM.getDebtlist().add(newdebt);
                getHousemate(lineItem.getPurchaserAuthKey()).getDebtlist().add(newdebt);
            }
            else{
                this.housedebt.add(newdebt);
                getHousemate(lineItem.getPurchaserAuthKey()).getDebtlist().add(newdebt);
            }
        }
    }

    /**
     * For the case where the user selects charge me, this method creates a debt object where the
     * creditor and debtor is the purchaser and the amount owed is the cost of the line item
     * @param lineItem          line item that the debt is for
     */

    public void createDebtForMe(LineItem lineItem) {
        Debt newdebt = new Debt(getHousemate(lineItem.getPurchaserAuthKey()), getHousemate(lineItem.getPurchaserAuthKey()),
                lineItem.getPrice()*lineItem.getQuantity(), lineItem);
        this.housedebt.add(newdebt);
        getHousemate(lineItem.getPurchaserAuthKey()).getDebtlist().add(newdebt);
        }

    /**
     * This method iterates through the purchased items list and does the following:
     * If the distribution is "Charge Based on Interested Housemates" then it calls the
     * createDebtForIHM method
     * If the distribution is "Charge Household" then it calls the createDebtForHH method
     * If the distribution is "Charge Me" then it calls the createDebtForMe method
     * @param distribution          how to distribute the cost of each line item
     * @param purchaser             the housemate that is purchased the items
     */
    public void checkout(String distribution, Housemate purchaser) {
        for (LineItem lineItem: purchasedItems) {
            lineItem.setPurchaserAuthKey(purchaser.getAuthKey());
            if(distribution.equals("Charge Based on Interested Housemates"))
                createDebtForIHM(lineItem);
            else if(distribution.equals("Charge Household"))
                createDebtForHH(lineItem);
            else if(distribution.equals("Charge Me"))
                createDebtForMe(lineItem);
            shoppingList.remove(lineItem);
        }
        purchasedItems.clear();
    }

    /**
     *
     * @return String that lists all the debt in the history of the house. If the debtor and the
     * creditor are the same person then the string says, "[Housemate_Name] paid x for [item name]."
     */
    public String houseTransactions(){
        String transactionList = "";
        for (Debt debt: housedebt) {
            //if(debt.getCreditor().getName().equals(debt.getDebtor().getName()))
            if(debt.getCreditorAuthKey().equals(debt.getDebtorAuthKey()))
                transactionList += getHousemate(debt.getDebtorAuthKey()).getName() + " paid " + debt.getOwed() + " for "
                        + debt.getLineItem().getQuantity() + " " + debt.getLineItem().getName() + "(s).\n";
            else
                transactionList += getHousemate(debt.getDebtorAuthKey()).getName() + " owes " +
                        getHousemate(debt.getCreditorAuthKey()).getName() + " " + debt.getOwed() + " for " +
                        debt.getLineItem().getQuantity() + " " + debt.getLineItem().getName() + "(s).\n";
        }
        return transactionList;
    }

    /**
     * This method takes each housemate's total credit - total owed and adds it to a string
     * balanceTotal.
     * @return balanceTotal a string that lists all the housemates net credit/debt
     */
    public String houseBalance(){
        String balanceTotal = "";
        for (Housemate hm: housemates) {
            float credit = 0;
            float owed = 0;
            for (Debt debt : hm.getDebtlist()) {
                Housemate debtor = getHousemate(debt.getDebtorAuthKey());
                Housemate creditor = getHousemate(debt.getCreditorAuthKey());
                //if (creditor.getName().equals(hm.getName()) && !creditor.getName().equals(debtor.getName()))
                if(creditor.getAuthKey().equals(hm.getAuthKey()) &&
                        !creditor.getAuthKey().equals(debtor.getAuthKey()))
                    credit += debt.getOwed();
                //if (debt.getDebtor().getName().equals(hm.getName()) &&
                //!creditor.getName().equals(debtor.getName()))
                if(debt.getDebtorAuthKey().equals(hm.getAuthKey())
                        && !creditor.getAuthKey().equals(debtor.getAuthKey()))
                    owed += debt.getOwed();
            }
            float net = credit - owed;
            balanceTotal += hm.getName() + " has a balance of " + net + ".\n";
        }
        return balanceTotal;
    }

    /**
     * Returns the housemate list associated to the house
     */
    public List<Housemate> getHousemates(){
        return housemates;
    }

    /**
     * Returns the calendar associated to the house
     */
    public Calendar getCalendar(){
        return calendar;
    }

    public void loadCalendar(Calendar calendar){
        this.calendar = calendar;
    }

    /**
     * This method takes a key and returns the housemate that the key is associated to.
     * @param key          address of housemate
     * @return the housemate with the given key
     */
    // key -> address of housemate
    public Housemate getHousemate(AuthKey key){
        for(int i = 0; i < housemates.size(); i++){
            if(housemates.get(i).getAuthKey().equals(key)){
                return housemates.get(i);
            }
        }
        return null;
    }

    /**
     * This method takes in a list of keys and returns a list of housemates with that key.
     * @param keys          address of housemates
     * @return the housemate with the given key
     */
    // keys -> address of housemates
    public List<Housemate> getHousemateList(List<AuthKey> keys){
        List<Housemate> tempList = new ArrayList<>();
        for(int i = 0; i < keys.size(); i++){
            tempList.add(getHousemate(keys.get(i)));
        }
        return tempList;
    }

    public void loadDebtList(List<Debt> dl) {
        this.housedebt = dl;
    }

    /**
     * Returns the list of house debts
     * @return the a list of debts
     */
    public List<Debt> getHousedebt(){
        return this.housedebt;
    }


    /**
     * Setter for the list of house debts
     */
    public void setHousedebt(List<Debt> housedebt) {
        this.housedebt = housedebt;
    }
}

