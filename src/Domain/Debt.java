package Domain;

public class Debt {
    private boolean isPaid = false;
    private Housemate debtor;
    private Housemate creditor;
    private String itemName;
    private float owed;

    public Debt(Housemate creditor, Housemate debtor, float owed, String itemName){
        this.debtor = debtor;
        this.creditor = creditor;
        this.owed = owed;
        this.itemName = itemName;
    }

    public Housemate getDebtor(){return debtor;}
    public Housemate getCreditor(){return creditor;}
    public String getItemName(){return itemName;}
    public float getOwed(){return owed;}
}
