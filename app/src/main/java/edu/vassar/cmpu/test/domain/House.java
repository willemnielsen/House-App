package edu.vassar.cmpu.test.domain;

import java.util.*;
public class House {

    private String houseName;
    int houseID = 0;
    private ShoppingList shoppingList;
    private List<Housemate> housemates;
    private List<LineItem> purchasedItems;
    private List<Debt> housedebt;
    private Calendar calendar;

    public House(String houseName) {
        Random random = new Random();
        for (int i = 0; i <= 5; i++) {
            int rand = random.nextInt(10);
            houseID = houseID * 10 + rand;
        }
        this.houseName = houseName;
        shoppingList = new ShoppingList();
        housemates = new ArrayList<Housemate>();
        purchasedItems = new ArrayList<LineItem>();
        housedebt = new ArrayList<Debt>();
        calendar = new Calendar();
    }

    public String getName(){
        return houseName;
    }

    public boolean addLineItemToShoppingList(int quantity, String name, float price,
                                             List<Housemate> interestedHouseMates){
        return shoppingList.addItem(quantity, name, price, interestedHouseMates);
    }



    public LineItem getShoppingListLineItem(int i){
        return shoppingList.getShoppingListLineItem(i);
    }

    public void loadShoppingList(ShoppingList sl){
        this.shoppingList = sl;
    }

    public void loadHousemates(List<Housemate> housemates){this.housemates = housemates;}

    public int getShoppingListSize(){
        return this.shoppingList.size();
    }

    public ShoppingList getShoppingList(){
        return shoppingList;
    }

    public List<LineItem> getPurchasedItems() {
        return purchasedItems;
    }

    private void purchaseItem(LineItem lineItem) {
        purchasedItems.add(lineItem);
    }

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

    public void createDebtForIHM(LineItem lineItem) {
        for (Housemate interHM : lineItem.getInterestedHouseMates()) {
                Debt newdebt = new Debt(lineItem.getPurchaser(), interHM,
                        (lineItem.getPrice()*lineItem.getQuantity())/lineItem.getInterestedHouseMates().size(),
                        lineItem);
                if(!interHM.getName().equals(lineItem.getPurchaser().getName())) {
                    this.housedebt.add(newdebt);
                    interHM.getDebtlist().add(newdebt);
                    lineItem.getPurchaser().getDebtlist().add(newdebt);
                }
                else{
                    this.housedebt.add(newdebt);
                    lineItem.getPurchaser().getDebtlist().add(newdebt);
                }
        }
    }

    public void createDebtForHH(LineItem lineItem) {
        for (Housemate currHM : housemates) {
            Debt newdebt = new Debt(lineItem.getPurchaser(), currHM,
                    (lineItem.getPrice()*lineItem.getQuantity())/housemates.size(), lineItem);
            if(!currHM.getName().equals(lineItem.getPurchaser().getName())) {
                this.housedebt.add(newdebt);
                currHM.getDebtlist().add(newdebt);
                lineItem.getPurchaser().getDebtlist().add(newdebt);
            }
            else{
                this.housedebt.add(newdebt);
                lineItem.getPurchaser().getDebtlist().add(newdebt);
            }
        }
    }

    public void createDebtForMe(LineItem lineItem) {
        Debt newdebt = new Debt(lineItem.getPurchaser(), lineItem.getPurchaser(),
                lineItem.getPrice()*lineItem.getQuantity(), lineItem);
        this.housedebt.add(newdebt);
        lineItem.getPurchaser().getDebtlist().add(newdebt);
        }

    public void checkout(String distribution, Housemate purchaser) {
        for (LineItem lineItem: purchasedItems) {
            lineItem.setPurchaser(purchaser);
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

    public String houseTransactions(){
        String transactionList = "";
        for (Debt debt: housedebt) {
            if(debt.getCreditor().getName().equals(debt.getDebtor().getName()))
                transactionList += debt.getDebtor().getName() + " paid " + debt.getOwed() + " for "
                        + debt.getItem().getQuantity() + " " + debt.getItem().getName() + "(s).\n";
            else
                transactionList += debt.getDebtor().getName() + " owes " +
                        debt.getCreditor().getName() + " " + debt.getOwed() + " for " +
                        debt.getItem().getQuantity() + " " + debt.getItem().getName() + "(s).\n";
        }
        return transactionList;
    }

    public String houseBalance(){
        String balanceTotal = "";
        for (Housemate hm: housemates) {
            float credit = 0;
            float owed = 0;
            for (Debt debt : hm.getDebtlist()) {
                if (debt.getCreditor().getName().equals(hm.getName()) &&
                        !debt.getCreditor().getName().equals(debt.getDebtor().getName()))
                    credit += debt.getOwed();
                if (debt.getDebtor().getName().equals(hm.getName()) &&
                        !debt.getCreditor().getName().equals(debt.getDebtor().getName()))
                    owed += debt.getOwed();
            }
            float net = credit - owed;
            balanceTotal += hm.getName() + " has a balance of " + net + ".\n";
        }
        return balanceTotal;
    }

    public List<Housemate> getHousemates(){
        return housemates;
    }
    public Calendar getCalendar(){
        return calendar;
    }

}

