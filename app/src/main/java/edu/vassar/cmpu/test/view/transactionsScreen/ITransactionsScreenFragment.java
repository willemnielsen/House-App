package edu.vassar.cmpu.test.view.transactionsScreen;

public interface ITransactionsScreenFragment {

    public interface Listener {
        void onPreviousOnTransactionsScreen();
    }

    void updateDisplay(String transactions);

}
