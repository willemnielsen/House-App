package edu.vassar.cmpu.test.domain;

public class Debt {
    private boolean isPaid = false;
    private Housemate debtor;
    private Housemate creditor;
    private LineItem lineItem;
    private float owed;

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
