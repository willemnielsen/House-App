package Domain;

public class Housemate {

    String name;
    String housemateId;
    Arraylist<Debt> debtlist;

    public Housemate(String name, String housemateId){
        this.name = name;
        this.housemateId = housemateId;
        debt = new Arraylist<Debt>;
    }

    public void myTransactions(){
        for (Debt debt: debtlist
             ) {
            System.out.println("You owe " + debt.creditor + " " + debt.owed + " for " + debt.name + ".\n");
        }
    }

    public void myBalance(){
        private float credit = 0;
        private float owed = 0;
        for (Debt debt: debtlist)
             ) {
    if(debt.creditor.name.equal(this.name))
            credit += debt.owed;
        else owed += debt.owed;

    }
        System.out.println("Your balance is " + credit-debt + ".");
    }
}
