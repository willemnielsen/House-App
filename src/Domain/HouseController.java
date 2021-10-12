package Domain;

public class HouseController {

    private House house;

    public HouseController(String houseName){
        house = new House(houseName);
    }

    public void addHousemate(Housemate housemate) {
        house.addHousemate(housemate);
    }

    public void removeHousemate(Housemate housemate){
        house.removeHousemate(housemate);
    }

    public House getHouse(){
        return house;
    }


}
