package Domain;

import Domain.ShoppingList;

import java.util.*;
public class House {

    private String houseName;
    int houseID = 0;
    private ShoppingList shoppingList;
    private ArrayList<Housemate> housemates;
    private ArrayList<LineItem> puchasedItems;
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
        this.houseName = houseName;
    }

    public boolean addHousemate(Housemate housemate) {
        if (housemates.contains(housemate)) {
            System.out.println(housemate.name + " is already a member of this house.");
            return false;
        } else {
            housemates.add(housemate);
            System.out.println(housemate.name + " successfully added to this house.");
            return true;
        }
    }

    public boolean removeHousemate(Housemate housemate) {
        if (housemates.contains(housemate)) {
            housemates.remove(housemate);
            System.out.println(housemate.name + " successfully removed from this house.");
            return true;
        } else {
            System.out.println(housemate.name + " is not a member of this house.");
            return false;
        }
    }

    public void nameHouse(String name) {
        houseName = name;
        System.out.println("House name changed to '" + houseName + "'.");
    }

    private void purchaseItem(LineItem lineItem) {
        puchasedItems.add(lineItem);
    }


    public void createDebt(LineItem lineItem) {
        for (Housemate interHM : lineItem.getInterestedHouseMates()) {
                Debt newdebt = new Debt(lineItem.purchaser, interHM, lineItem.getPrice()/lineItem.getInterestedHouseMates().size(), lineItem.getName());
                if(!interHM.name.equals(lineItem.purchaser.name)){
                    this.housedebt.add(newdebt);
                    interHM.debtlist.add(newdebt);
                    lineItem.purchaser.debtlist.add(newdebt);
            }
        }
    }
    public void checkout() {
        for (LineItem lineItem: puchasedItems) {
            createDebt(lineItem);
        }
        for (LineItem lineItem: puchasedItems) {
            purchasedItem.remove(lineItem);
        }
    }
}

