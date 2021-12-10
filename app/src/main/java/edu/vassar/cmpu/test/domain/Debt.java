package edu.vassar.cmpu.test.domain;

import java.io.Serializable;

/**
 * Debt class stores information on the amount owed from one housemate to another for a particular
 * item that was bought.
 */
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

    /**
     * Creates a debt object.
     * @param creditor      housemate who is owed
     * @param debtor        housemate who is in debt
     * @param owed          amount owed in dollars
     * @param lineItem      the line item that caused this debt
     */
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
