package Domain;

import Domain.ShoppingList;

import java.util.*;
public class House {

    private String houseName;
    int houseID = 0;
    private ShoppingList shoppingList;
    private ArrayList<Housemate> housemates;
    private ArrayList<lineItem> puchasedItems;

    public House(String houseName){
        Random random = new Random();
        for(int i=0; i<= 5; i++) {
            int rand = random.nextInt(10);
            houseID = houseID*10 + rand;
        }
        housemates = new ArrayList<Housemate>();
        shoppingList = new ShoppingList();
        this.houseName = houseName;
    }
    public boolean addHousemate(Housemate housemate){
        if (housemates.contains(housemate)){
            System.out.println(housemate.name + " is already a member of this house.")
            return false;
        }
        else{
            housemates.add(housemate);
            System.out.println(housemate.name + " successfully added to this house.");
            return true;
        }
    }

    public void removeHousemate(Housemate housemate){
        if (housemates.contains(housemate)){
            housemates.remove(housemate);
            System.out.println(housemate.name + " sucessfully removed from this house.");
            return true;
        }
        else{
            System.out.println(housemate.name + " is not a member of this house.");
            return false;
        }
    }

    public void nameHouse(String name){
        houseName = name;
        System.out.println("House name changed to '" + houseName + "'.");
    }
    private purchaseItem(LineItem lineItem){
        puchasedItems.add(lineItem)
    }
}
