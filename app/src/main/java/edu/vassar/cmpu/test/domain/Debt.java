package edu.vassar.cmpu.test.domain;

import com.google.rpc.context.AttributeContext;

import java.io.Serializable;

/**
 * Debt class stores information on the amount owed from one housemate to another for a particular
 * item that was bought.
 */
public class Debt implements Serializable {
    private boolean isPaid = false;
    private AuthKey debtorAuthKey;
    private AuthKey creditorAuthKey;
    private LineItem lineItem;
    private float owed;

    public Debt(){
        this.debtorAuthKey = null;
        this.creditorAuthKey = null;
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
        this.debtorAuthKey = debtor.getAuthKey();
        this.creditorAuthKey = creditor.getAuthKey();
        this.owed = owed;
        this.lineItem = lineItem;
    }


    public void setDebtorAuthKey(AuthKey debtorAuthKey) {
        this.debtorAuthKey = debtorAuthKey;
    }

    public AuthKey getDebtorAuthKey(){return debtorAuthKey;}

    public void setCreditorAuthKey(AuthKey creditorAuthKey) {
        this.creditorAuthKey = creditorAuthKey;
    }

    public AuthKey getCreditorAuthKey(){return creditorAuthKey;}

    public LineItem getLineItem() {
        return lineItem;
    }

    public void setLineItem(LineItem lineItem) {
        this.lineItem = lineItem;
    }

    public void setOwed(float owed) {
        this.owed = owed;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public float getOwed(){return owed;}

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Debt)){
            return false;
        }
        Debt debt = (Debt) o;
        return this.getDebtorAuthKey().equals(debt.debtorAuthKey)
                && this.getCreditorAuthKey().equals(debt.creditorAuthKey)
                && this.isPaid == debt.isPaid
                && this.owed == debt.owed
                && this.lineItem.equals(debt.getLineItem());
    }
}
