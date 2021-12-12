package edu.vassar.cmpu.test.domain;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Class for housemate. Contains name, id, and list of debt for the housemate
 */
public class Housemate implements Serializable {


    private String name;
    private long housemateId;
    public List<Debt> debtlist;
    private String username;
    private AuthKey authKey;

    /**
     * Creates a housemate object. By default name is "NA", id is 0, and debt list is an empty
     * arraylist
     */
    public Housemate(){
        this.name = "NA";
        this.housemateId = 0;
        debtlist = new ArrayList<Debt>();
        this.username="NA";
        this.authKey= new AuthKey();
    }

    /**
     * Creates a housemate object with given name. By default, id is a random long and debt list
     * is an empty arraylist
     * @param username      name of housemate to be created
     */
    public Housemate(String username, String password){
        this.name = username;
        this.housemateId = new Random().nextLong();
        debtlist = new ArrayList<Debt>();
        this.username = username;
        this.authKey = new AuthKey(password);
    }

    public String getUsername(){ return this.username; }

    public void setUsername(String username) {
        this.username = username;
    }

    public AuthKey getAuthKey(){ return this.authKey; }

    public void setAuthKey(AuthKey authKey) {
        this.authKey = authKey;
    }

    public boolean validatePassword(String password){
        return this.authKey.validatePassword(password);
    }


    /**
     * myTransactions creates a string that lists what you paid for and what you owe for.
     * @return          transactionList, a string that lists the housemate's transactions
     */
    /*
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

     */

    /**
     * myBalance takes the total credit - total debt of the housemate and returns it in string form
     * @return      balanceTotal, a string that states your total credit - total balance
     */

    public String myBalance(){
        float credit = 0;
        float owed = 0;
        String balanceTotal = "";
        for (Debt debt: debtlist) {
            //if(debt.getCreditor().name.equals(this.name) &&
            //        !debt.getCreditor().name.equals(debt.getDebtor().name))
            if(debt.getCreditorAuthKey().getKey().equals(this.getAuthKey().getKey())
                && !debt.getCreditorAuthKey().getKey().equals(debt.getDebtorAuthKey().getKey()))
                credit += debt.getOwed();
            //if (debt.getDebtor().name.equals(this.name) &&
            //        !debt.getCreditor().name.equals(debt.getDebtor().name))
            if(debt.getDebtorAuthKey().getKey().equals(this.getAuthKey().getKey())
                && !debt.getCreditorAuthKey().getKey().equals(debt.getDebtorAuthKey().getKey()))
                owed += debt.getOwed();
    }
        float net = credit-owed;
        balanceTotal += "Your balance is " + net + ".";
        return balanceTotal;
    }

    /**
     * Retrieves name of housemate
     * @return name of housemate
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of housemate
     * @param name         name of housemate to be set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves list of debt for this housemate
     * @return      the debtlist attribute for this housemate
     */
    public List<Debt> getDebtlist(){return debtlist;}

    public void setDebtlist(List<Debt> debtlist) {
        this.debtlist = debtlist;
    }

    /**
     * Retrieves housemate id
     * @return      the housemateId attribute for this housemate
     */
    public long getHousemateId() {
        return housemateId;
    }

    /**
     * Sets housemate id
     * @param housemateId       the housemate id that will be set
     */
    public void setHousemateId(long housemateId) {
        this.housemateId = housemateId;
    }


    /**
     * Gives name of housemate in string form
     * @return      name of housemate in string form
     */
    public String toString(){
        return "" + username + "";
    }

    /**
     * Checks whether this housemate is the same as the given object
     * @param o         the object that is tested
     * @return          True if the object is a housemate and has the same housemate id. Otherwise,
     * returns false.
     */
    @Override
    public boolean equals(Object o){
        if(!(o instanceof Housemate)){
            return false;
        }
        Housemate hm = (Housemate) o;
        return this.authKey.getKey() == hm.authKey.getKey();
    }
}
