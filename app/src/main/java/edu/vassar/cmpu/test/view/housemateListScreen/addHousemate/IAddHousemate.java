package edu.vassar.cmpu.test.view.housemateListScreen.addHousemate;

import edu.vassar.cmpu.test.domain.Housemate;

public interface IAddHousemate {

    public interface Listener {
        void onAddHousemate(String name);
        void onPreviousOnAddHousemateScreen();
    }



}
