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

    public boolean addHousemate(Housemate housemate) {
        if (housemates.contains(housemate)) {
            System.out.println(housemate.getName() + " is already a member of this house.");
            return false;
        } else {
            housemates.add(housemate);
            System.out.println(housemate.getName() + " successfully added to this house.");
            return true;
        }
    }

    public boolean removeHousemate(Housemate housemate) {
        if (housemates.contains(housemate)) {
            housemates.remove(housemate);
            System.out.println(housemate.getName() + " successfully removed from this house.");
            return true;
        } else {
            System.out.println(housemate.getName() + " is not a member of this house.");
            return false;
        }
    }

    public void nameHouse(String name) {
        houseName = name;
        System.out.println("House name changed to '" + houseName + "'.");
    }

    private void purchaseItem(LineItem lineItem) {
        purchasedItems.add(lineItem);
    }


    public void createDebtForIHM(LineItem lineItem) {
        for (Housemate interHM : lineItem.getInterestedHouseMates()) {
                Debt newdebt = new Debt(lineItem.getPurchaser(), interHM, (lineItem.getPrice()*lineItem.getQuantity())/lineItem.getInterestedHouseMates().size(), lineItem.getName());
                if(!interHM.getName().equals(lineItem.getPurchaser().getName())) {
                    this.housedebt.add(newdebt);
                    interHM.debtlist.add(newdebt);
                    lineItem.getPurchaser().debtlist.add(newdebt);
                }
                else{
                    this.housedebt.add(newdebt);
                    lineItem.getPurchaser().debtlist.add(newdebt);
                }
        }
    }

    public void createDebtForHH(LineItem lineItem) {
        for (Housemate currHM : housemates) {
            Debt newdebt = new Debt(lineItem.getPurchaser(), currHM, (lineItem.getPrice()*lineItem.getQuantity())/housemates.size(), lineItem.getName());
            if(!currHM.getName().equals(lineItem.getPurchaser().getName())) {
                this.housedebt.add(newdebt);
                currHM.debtlist.add(newdebt);
                lineItem.getPurchaser().debtlist.add(newdebt);
            }
            else{
                this.housedebt.add(newdebt);
                lineItem.getPurchaser().debtlist.add(newdebt);
            }
        }
    }

    public void createDebtForMe(LineItem lineItem) {
        Debt newdebt = new Debt(lineItem.getPurchaser(), lineItem.getPurchaser(), lineItem.getPrice()*lineItem.getQuantity(), lineItem.getName());
        this.housedebt.add(newdebt);
        lineItem.getPurchaser().debtlist.add(newdebt);
        }

    public void checkout(String distribution, Housemate purchaser) {
        for (LineItem lineItem: purchasedItems) {
            lineItem.setPurchaser(purchaser);
            if(distribution.equals("Charge Based on Interested Housemates"))
                createDebtForIHM(lineItem);
            else if(distribution.equals("Charge Household") || distribution.equals("B"))
                createDebtForHH(lineItem);
            else if(distribution.equals("Charge Me") || distribution.equals("C"))
                createDebtForMe(lineItem);
        }
        purchasedItems.clear();
        shoppingList.clear();
    }

    public String houseTransactions(){
        String transactionList = "";
        for (Debt debt: housedebt) {
            if(debt.getCreditor().getName().equals(debt.getDebtor().getName()))
                transactionList += debt.getDebtor().getName() + " paid " + debt.getOwed() + " for " + debt.getItemName() + ".\n";
            else
                transactionList += debt.getDebtor().getName() + " owes " + debt.getCreditor().getName() + " " + debt.getOwed() + " for " + debt.getItemName() + ".\n";
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

    public ArrayList<Housemate> getHousemates(){
        return housemates;
    }
}

