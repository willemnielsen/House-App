package UI;

import Domain.HouseController;
import Domain.Housemate;

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
                                houseController.addLineItemToShoppingList(quantity, name, price, null);
                                if(ask("add more items (yes) or done (done)").contains("done")){
                                    break;
                                }
                            } while (true);
                        } else if(shoppingListSubAction.contains("list")){
                            System.out.println(houseController.shoppingListToString());
                        } else if(shoppingListSubAction.contains("Done")) {
                            continue;
                        }
                    break;
                case "Purchase":
                    purchase();
                    break;
                case "Done":
                    return;
                    //checkout
                    //splitting options
                    //refining wishlist to perched items
            }



        }
    }

    public void purchase(){
        for(int i = 0; i < houseController.getHouse().getShoppingListSize(); i++){
            System.out.println(houseController.getShoppingListLineItem(i).toString());
            String respond = ask("Do you want to purchase this item. (yes/no)");
            if(respond.contains("yes")){
                houseController.addToPurchse(houseController.getShoppingListLineItem(i));
            }
        }
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
