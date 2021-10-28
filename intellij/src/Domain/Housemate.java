package Domain;
import java.lang.reflect.Array;
import java.util.*;
public class Housemate {


    private String name;
    private String housemateId;
    public ArrayList<Debt> debtlist;

    public Housemate(String name, String housemateId){
        this.name = name;
        this.housemateId = housemateId;
        debtlist = new ArrayList<Debt>();
    }

    public String myTransactions(){
        String transactionList = "";
        for (Debt debt: debtlist) {
            if(debt.getCreditor().name.equals(debt.getDebtor().name))
            transactionList += "You paid " + debt.getOwed() + " for " + debt.getItem().getQuantity() + " " + debt.getItem().getName() + "(s).\n";
            else transactionList += "You owe " + debt.getCreditor().name + " " + debt.getOwed() + " for " + debt.getItem().getQuantity() + " " + debt.getItem().getName() + "(s).\n";
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
