package edu.vassar.cmpu.test.view.housemateListScreen.debtScreen;

public interface IDebtScreenFragment {

    public interface Listener {
        void onPreviousOnDebtScreen();
    }

    public void updateDisplay(String debt);
}
