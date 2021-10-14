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

    public String myTransactions(){
        String transactionList = "";
        for (Debt debt: debtlist) {
            if(debt.getCreditor().name.equals(debt.getDebtor().name))
            transactionList += "You paid " + debt.getOwed() + " for " + debt.getItemName() + ".\n";
            else transactionList += "You paid " + debt.getCreditor().name + " " + debt.getOwed() + " for " + debt.getItemName() + ".\n";
        }
        return transactionList;
    }

    public String myBalance(){
        float credit = 0;
        float owed = 0;
        String balanceTotal = "";
        for (Debt debt: debtlist) {
            if(debt.getCreditor().name.equals(this.name))
                credit += debt.getOwed();
            else owed += debt.getOwed();

    }
        float net = credit-owed;
        balanceTotal += "Your balance is " + net + ".";
        return balanceTotal;
    }

}
