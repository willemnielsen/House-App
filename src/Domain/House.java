package Domain;

import Domain.ShoppingList;

import java.util.*;
public class House {

    private String houseName;
    int houseID = 0;
    private ShoppingList shoppingList;
    ArrayList<Housemate> housemates;

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

    public void addHousemate(Housemate housemate) {

    }

    public void removeHousemate(Housemate housemate){

    }

    public void nameHouse(String name){

    }
}
