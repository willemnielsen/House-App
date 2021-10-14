package Domain;

import Domain.ShoppingList;

import java.util.*;
public class House {

    private String houseName;
    int houseID = 0;
    private ShoppingList shoppingList;
    private ArrayList<Housemate> housemates;
    private ArrayList<LineItem> purchasedItems;
    private ArrayList<Debt> housedebt;

    public House(String houseName) {
        Random random = new Random();
        for (int i = 0; i <= 5; i++) {
            int rand = random.nextInt(10);
            houseID = houseID * 10 + rand;
        }
        housemates = new ArrayList<Housemate>();
        shoppingList = new ShoppingList();
        housedebt = new ArrayList<Debt>();
        purchasedItems = new ArrayList<LineItem>();
        this.houseName = houseName;
    }

    public String addHousemate(Housemate housemate) {
        String output;
        if (housemates.contains(housemate)) {
            output = housemate.name + " is already a member of this house.";
        } else {
            housemates.add(housemate);
            output = housemate.name + " successfully added to this house.";
        }
        return output;
    }

    public String removeHousemate(Housemate housemate) {
        String output;
        if (housemates.contains(housemate)) {
            housemates.remove(housemate);
            output = housemate.name + " successfully removed from this house.";
        } else {
            output = housemate.name + " is not a member of this house.";
        }
        return output;
    }

    public String nameHouse(String name) {
        houseName = name;
        return "House name changed to '" + houseName + "'.";
    }

    private void purchaseItem(LineItem lineItem) {
        purchasedItems.add(lineItem);
    }


    public void createDebtForIHM(LineItem lineItem) {
        for (Housemate interHM : lineItem.getInterestedHouseMates()) {
                Debt newdebt = new Debt(lineItem.purchaser, interHM, (lineItem.getPrice()*lineItem.getQuantity())/lineItem.getInterestedHouseMates().size(), lineItem.getName());
                if(!interHM.name.equals(lineItem.purchaser.name)) {
                    this.housedebt.add(newdebt);
                    interHM.debtlist.add(newdebt);
                    lineItem.purchaser.debtlist.add(newdebt);
                }
                else{
                    this.housedebt.add(newdebt);
                    lineItem.purchaser.debtlist.add(newdebt);
                }
        }
    }

    public void createDebtForHH(LineItem lineItem) {
        for (Housemate currHM : housemates) {
            Debt newdebt = new Debt(lineItem.purchaser, currHM, (lineItem.getPrice()*lineItem.getQuantity())/housemates.size(), lineItem.getName());
            if(!currHM.name.equals(lineItem.purchaser.name)) {
                this.housedebt.add(newdebt);
                currHM.debtlist.add(newdebt);
                lineItem.purchaser.debtlist.add(newdebt);
            }
            else{
                this.housedebt.add(newdebt);
                lineItem.purchaser.debtlist.add(newdebt);
            }
        }
    }

    public void createDebtForMe(LineItem lineItem) {
        Debt newdebt = new Debt(lineItem.purchaser, lineItem.purchaser, lineItem.getPrice()*lineItem.getQuantity(), lineItem.getName());
        this.housedebt.add(newdebt);
        lineItem.purchaser.debtlist.add(newdebt);
        }

    public void checkout(String distribution, Housemate purchaser) {
        for (LineItem lineItem: purchasedItems) {
            lineItem.purchaser = purchaser;
            if(distribution.equals("Charge Based on Interested Housemates"))
                createDebtForIHM(lineItem);
            else if(distribution.equals("Charge Household"))
                createDebtForHH(lineItem);
            else if(distribution.equals("Charge Me"))
                createDebtForMe(lineItem);
        }
        for (LineItem lineItem: purchasedItems) {
            purchasedItems.remove(lineItem);
        }
    }

    public String houseTransactions(){
        String transactionList = "";
        for (Debt debt: housedebt) {
            if(debt.getCreditor().name.equals(debt.getDebtor().name))
                transactionList += debt.getDebtor().name + " paid " + debt.getOwed() + " for " + debt.getItemName() + ".\n";
            else
                transactionList += debt.getDebtor().name + " owes " + debt.getCreditor().name + " " + debt.getOwed() + " for " + debt.getItemName() + ".\n";
        }
        return transactionList;
    }

    public boolean addLineItemToShoppingList(int quantity, String name, float price, ArrayList<Housemate> interestedHouseMates){
        return shoppingList.addItem(quantity, name, price, interestedHouseMates);
    }

    public LineItem getShoppingListLineItem(int i){
        return shoppingList.getShoppingListLineItem(i);
    }

    public int getShoppingListSize(){
        return this.shoppingList.size();
    }

    public ShoppingList getShoppingList(){
        return shoppingList;
    }

    public ArrayList<LineItem> getPurchasedItems() {
        return purchasedItems;
    }

    public String toString(){
        String list = "Purchased Items {\n";
        for(LineItem lineItem : purchasedItems) {
            list += "\t" + lineItem.toString() + "\n";
        }
        return list + "}\n";
    }
}

