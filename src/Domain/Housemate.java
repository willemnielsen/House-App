package Domain;
import java.util.*;
public class Housemate {


    String name;
    String housemateId;
    public ArrayList<Debt> debtlist;

    public Housemate(String name, String housemateId){
        this.name = name;
        this.housemateId = housemateId;
        ArrayList<Debt> debtlist = new ArrayList<Debt>();
    }

    public void myTransactions(){
        for (Debt debt: debtlist) {
            System.out.println("You owe " + debt.getCreditor() + " " + debt.getOwed() + " for " + debt.getItemName() + ".\n");
        }
    }

    public void myBalance(){
        float credit = 0;
        float owed = 0;
        for (Debt debt: debtlist) {
            if(debt.getCreditor().name.equals(this.name))
                credit += debt.getOwed();
            else owed += debt.getOwed();

    }
        float net = credit-owed;
        System.out.println("Your balance is " + net + ".");
    }

}
