package edu.vassar.cmpu.test.domain;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;
public class Housemate implements Serializable {


    private String name;
    private long housemateId;
    public List<Debt> debtlist;

    public Housemate(){
        this.name = "NA";
        this.housemateId = 0;
        debtlist = new ArrayList<Debt>();
    }

    public Housemate(String name){
        this.name = name;
        this.housemateId = new Random().nextLong();
        debtlist = new ArrayList<Debt>();
    }

    public String myTransactions(){
        String transactionList = "";
        for (Debt debt: debtlist) {
            if(debt.getCreditor().name.equals(debt.getDebtor().name))
            transactionList += "You paid " + debt.getOwed() + " for " + debt.getItem().getQuantity()
                    + " " + debt.getItem().getName() + "(s).\n";
            else transactionList += "You owe " + debt.getCreditor().name + " " + debt.getOwed()
                    + " for " + debt.getItem().getQuantity() + " " + debt.getItem().getName()
                    + "(s).\n";
        }
        return transactionList;
    }

    public String myBalance(){
        float credit = 0;
        float owed = 0;
        String balanceTotal = "";
        for (Debt debt: debtlist) {
            if(debt.getCreditor().name.equals(this.name) &&
                    !debt.getCreditor().name.equals(debt.getDebtor().name))
                credit += debt.getOwed();
            if (debt.getDebtor().name.equals(this.name) &&
                    !debt.getCreditor().name.equals(debt.getDebtor().name))
                owed += debt.getOwed();
    }
        float net = credit-owed;
        balanceTotal += "Your balance is " + net + ".";
        return balanceTotal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Debt> getDebtlist(){return debtlist;}

    public void setDebtlist(List<Debt> debtlist) {
        this.debtlist = debtlist;
    }

    public long getHousemateId() {
        return housemateId;
    }

    public void setHousemateId(long housemateId) {
        this.housemateId = housemateId;
    }

    public String toString(){
        return "" + name + "";
    }
}
