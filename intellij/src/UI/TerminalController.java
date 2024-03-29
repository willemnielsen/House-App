package UI;

import Domain.House;
import Domain.HouseController;
import Domain.Housemate;
import Domain.LineItem;

import java.util.ArrayList;
import java.util.Scanner;

public class TerminalController implements Runnable{

    public static Scanner scanner = new Scanner(System.in);
    public boolean running = false;

    public HouseController houseController;//house that will be controlled

    /**
     * start run method by enabling program loop
     */
    public void start(){
        running = true;
        run();
    }


    /**
     * app run loop handles single house at time
     */
    public void run(){
        houseController = new HouseController("TH80");
        houseController.addHousemate(new Housemate(ask("Enter your name"), "120332412"));
        houseController.addHousemate(new Housemate("Tom", "12033546457"));
        houseController.addHousemate(new Housemate("Jerry", "12033457457"));


        while(true){
            //ask high level questions first
            String action = ask("\nActions\n\"ShoppingList\"\n\"Purchase\"\n\"Done\" to exit\n");

            switch (action) {
                case "ShoppingList"://ShoppingList
                    String shoppingListSubAction = ask("\"add item\"\n\"list\"\n\"Done\" to return\n");
                        if(shoppingListSubAction.contains("add item")){
                            do {
                                String name = ask("Enter Item name");
                                float price = (float) Double.parseDouble(ask("Enter price", "double"));
                                int quantity = Integer.parseInt(ask("Enter quantity", "int"));
                                ArrayList<Housemate> ihm = getInteresetedHousemates(houseController);
                                System.out.println(HouseController.convertHouseMatesToString(ihm));
                                houseController.addLineItemToShoppingList(quantity, name, price, ihm);
                                if(ask("add more items (yes) or done (done)").toLowerCase().contains("done")){
                                    break;
                                }
                            } while (true);
                        } else if(shoppingListSubAction.contains("list")){
                            System.out.println(houseController.shoppingListToString());
                        } else if(shoppingListSubAction.toLowerCase().contains("Done")) {
                            continue;
                        }
                    break;
                case "Purchase":
                    purchase();
                    //print purchased list (assume no mistake)
                    System.out.println(houseController.convertPurchaseToString(houseController.getHouse().getPurchasedItems()));
                    //ask payment methods 3 of them
                    String paymentType = ask("Select a payment option:\nA \"Charge Based on Interested Housemates\"\nB \"Charge Household\"\nC \"Charge Me\"");
                        //call the method to split bill
                        //clear purchase and shopping list;
                    //whose paying
                    System.out.println("Select a purchaser");
                    houseController.checkout(paymentType, askForHouseMate(houseController));
                    System.out.println(houseController.houseTransactions());
                    System.out.println(houseController.houseBalance());


                    break;
                case "Done":
                    //status at the end
                    System.out.println("Closing App");
                    System.out.println(HouseController.convertHouseMatesToString(houseController.getHousemates()));
                    System.out.println(houseController.houseTransactions());
                    System.out.println(houseController.houseBalance());
                    return;
            }
        }
    }

    public void purchase(){
        for(int i = 0; i < houseController.getHouse().getShoppingListSize(); i++){
            System.out.println(houseController.getShoppingListLineItem(i).toString());
            String respond = ask("Did you purchase this item. (yes/no)");
            if(respond.contains("yes")){
                houseController.addToPurchse(houseController.getShoppingListLineItem(i));
            }
        }
    }


    public ArrayList<Housemate> getInteresetedHousemates(HouseController houseController){
        ArrayList<Housemate> interestedHousemates = new ArrayList<Housemate>();
        System.out.println(HouseController.convertHouseMatesToString(houseController.getHousemates()));
        String input = "";
        Housemate x = null;
        do {
            input = ask("Enter interested Housemates one at a time; 'Done' for when done");
            x = houseController.getHousemate(input);
            if(x != null && !interestedHousemates.contains(x)){
                interestedHousemates.add(x);
                x = null;
            }
        } while (!input.equals("Done"));


        return interestedHousemates;
    }

    public Housemate askForHouseMate(HouseController housecontroller){
        System.out.println(HouseController.convertHouseMatesToString(housecontroller.getHousemates()));//all names
        Housemate x = null;
        do {
            x = housecontroller.getHousemate(ask("Enter a Housemate"));
        } while (x == null);
        return x;
    }


    /**
     *
     * @param question
     * @return respond to question
     */
    public static String ask(String question){
        System.out.println(question);
        return scanner.nextLine();
    }

    public static String ask(String question, String type){
        String input = "";
        while(true) {
            try {
                if (type.equals("String")) {
                    break;
                } else if (type.contains("int")) {
                    int x = Integer.parseInt(input);
                    break;
                } else if (type.contains("double") || type.contains("float")) {
                    double x = Double.parseDouble(input);
                    break;
                }
            } catch (Exception e) {
                System.out.println(question);
                input = scanner.nextLine();
            }
        }
        return input;
    }

    public static void main(String[] args){
        TerminalController houseMatesTerminalApp = new TerminalController();
        houseMatesTerminalApp.start();
    }


}
