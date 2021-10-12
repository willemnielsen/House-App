package Domain;

public class Debt {
    boolean isPaid = false;
    Housemate debtor;
    Housemate creditor;
    String itemName;
    float owed;
    public Debt(Housemate creditor, Housemate debtor, float owed, String itemName){
        this.debtor = debtor;
        this.creditor = creditor;
        this.owed = owed;
        this.itemName = itemName;
    }
}
