package UI;

import Domain.HouseController;
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
        houseController = new HouseController(ask("Enter name of house"));
        while(true){
            //ask high level questions first
            String action = ask("\nActions\n\"Members\"\n\"ShoppingLists\"\n");

            switch (action) {
                case "Members"://Member?
                    String memberubAction = ask("\"add new Member\"\n\"remove Member\"\n\"list of Members\"\n");
                    //add/remove member
                    //member list
                    break;
                case "ShoppingList"://ShoppingList
                    String shoppingListSubAction = ask("\"add item\"\n\"remove item\"\n\"list of items\"\n");

                        //add item
                        //remove item
                        //show wish list
                    break;
                //checkout
                    //splitting options
                    //refining wishlist to perched items
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

    public static void main(String[] args){
        TerminalController houseMatesTerminalApp = new TerminalController();
        houseMatesTerminalApp.start();
    }


}
