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

    /**
     * Class constructor.
     */
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

    /**
     * Returns the debtor key
     */
    public AuthKey getDebtorAuthKey(){return debtorAuthKey;}

    public void setCreditorAuthKey(AuthKey creditorAuthKey) {
        this.creditorAuthKey = creditorAuthKey;
    }

    /**
     * Returns the creditor key
     */
    public AuthKey getCreditorAuthKey(){return creditorAuthKey;}

    /**
     * Returns the line item that was purchased
     */
    public LineItem getLineItem() {
        return lineItem;
    }

    /**
     * Setter for line item that is being added
     * @param lineItem              item
     */
    public void setLineItem(LineItem lineItem) {
        this.lineItem = lineItem;
    }

    /**
     * Setter for amount due
     * @param owed              float number that represents the amount due
     */
    public void setOwed(float owed) {
        this.owed = owed;
    }

    /**
     * Setter for paid boolean
     * @param paid              boolean value, true means an item was paid, false means it was not
     */
    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    /**
     * Returns the amount a housemate owes
     */
    public float getOwed(){return owed;}

    /**
     * Creates a debt object.
     */
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
