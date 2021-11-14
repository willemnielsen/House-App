package edu.vassar.cmpu.test.view.housemateListScreen;

import java.util.ArrayList;

import edu.vassar.cmpu.test.domain.Housemate;

public interface IHousemateListScreenFragment {
    interface Listener{
        void onPreviousOnHousemateListScreen();
    }

    void updateDisplay(ArrayList<Housemate> housemates);
}
