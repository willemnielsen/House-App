package Domain;
import java.lang.reflect.Array;
import java.util.*;
public class Housemate {


    private String name;
    private String housemateId;
    private ArrayList<Debt> debtlist;

    public Housemate(String name, String housemateId){
        this.name = name;
        this.housemateId = housemateId;
        ArrayList<Debt> debtlist = new ArrayList<Debt>();
    }

    public String myTransactions(){
        String transactionList = "";
        for (Debt debt: debtlist) {
            if(debt.getCreditor().name.equals(debt.getDebtor().name))
            transactionList += "You paid " + debt.getOwed() + " for " + debt.getItem().getName() + ".\n";
            else transactionList += "You paid " + debt.getCreditor().name + " " + debt.getOwed() + " for " + debt.getItem().getName() + ".\n";
        }
        return transactionList;
    }

    public String myBalance(){
        float credit = 0;
        float owed = 0;
        String balanceTotal = "";
        for (Debt debt: debtlist) {
            if(debt.getCreditor().name.equals(this.name) && !debt.getCreditor().name.equals(debt.getDebtor().name))
                credit += debt.getOwed();
            if (debt.getDebtor().name.equals(this.name) && !debt.getCreditor().name.equals(debt.getDebtor().name))
                owed += debt.getOwed();
    }
        float net = credit-owed;
        balanceTotal += "Your balance is " + net + ".";
        return balanceTotal;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Debt> getDebtlist(){return debtlist;}

    public String toString(){
        return "{" + name + "}";
    }
}
