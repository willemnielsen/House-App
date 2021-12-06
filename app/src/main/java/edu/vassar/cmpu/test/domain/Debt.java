package edu.vassar.cmpu.test.domain;

import java.io.Serializable;

public class Debt implements Serializable {
    private boolean isPaid = false;
    private Housemate debtor;
    private Housemate creditor;
    private LineItem lineItem;
    private float owed;

    public Debt(){
        this.debtor = null;
        this.creditor = null;
        this.owed = 0;
        this.lineItem = null;
    }

    public Debt(Housemate creditor, Housemate debtor, float owed, LineItem lineItem){
        this.debtor = debtor;
        this.creditor = creditor;
        this.owed = owed;
        this.lineItem = lineItem;
    }

    public Housemate getDebtor(){return debtor;}
    public Housemate getCreditor(){return creditor;}
    public LineItem getItem(){return lineItem;}
    public float getOwed(){return owed;}
}
