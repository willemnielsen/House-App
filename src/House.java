import java.util.*;
public class House {
    private String name;
    int houseID = 0;
    ArrayList<Object> shoppingList;
    ArrayList<Housemate> housemates;
    public House(){
        Random random = new Random();
        for(int i=0; i<= 5; i++) {
            int rand = random.nextInt(10);
            houseID = houseID*10 + rand;
        }
        housemates = new ArrayList<Housemate>();
        shoppingList = new ArrayList<Object>();
        name = "";
    }
    public void addHousemate(Housemate housemate){

    }
    public void removeHousemate(Housemate housemate){

    }
    public void nameHouse(String name){

    }
}
