public class Debt {
    boolean isPaid = false;
    Housemate debtor;
    Housemate creditor;
    float debt;
    public Debt(Housemate debtor, Housemate creditor, float debt){
        this.debtor = debtor;
        this.creditor = creditor;
        this.debt = debt;
    }
}
