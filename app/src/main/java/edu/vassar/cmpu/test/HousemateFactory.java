package edu.vassar.cmpu.test;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;

import edu.vassar.cmpu.test.domain.Housemate;
import edu.vassar.cmpu.test.domain.ShoppingList;
import edu.vassar.cmpu.test.view.addItemView.AddItemFragment;
import edu.vassar.cmpu.test.view.homeScreen.HomeScreenFragment;
import edu.vassar.cmpu.test.view.housemateListScreen.HousemateListScreenFragment;
import edu.vassar.cmpu.test.view.housemateListScreen.addHousemate.AddHousemateFragment;
import edu.vassar.cmpu.test.view.housemateListScreen.debtScreen.DebtScreenFragment;
import edu.vassar.cmpu.test.view.loginScreen.LoginScreenFragment;
import edu.vassar.cmpu.test.view.purchasedListScreen.PurchasedListScreenFragment;
import edu.vassar.cmpu.test.view.shoppingListScreen.ShoppingListScreenFragment;
import edu.vassar.cmpu.test.view.transactionsScreen.TransactionsScreenFragment;

public class HousemateFactory extends FragmentFactory {

    private ControllerActivity controller;

    HousemateFactory(ControllerActivity controller){
        this.controller = controller;
    }

    @NonNull
    @Override
    public Fragment instantiate(@NonNull ClassLoader classLoader, @NonNull String className) {

        Class<? extends Fragment> fragmentClass = loadFragmentClass(classLoader, className);

        Fragment fragment;
        if (fragmentClass == LoginScreenFragment.class)
            fragment = new LoginScreenFragment(controller);
        else if (fragmentClass == HomeScreenFragment.class)
            fragment = new HomeScreenFragment(controller);
        else if (fragmentClass == HousemateListScreenFragment.class)
            fragment = new HousemateListScreenFragment(controller);
        else if (fragmentClass == AddHousemateFragment.class)
            fragment = new AddHousemateFragment(controller);
        else if (fragmentClass == DebtScreenFragment.class)
            fragment = new DebtScreenFragment(controller);
        else if (fragmentClass == ShoppingListScreenFragment.class)
            fragment = new ShoppingListScreenFragment(controller);
        else if (fragmentClass == AddItemFragment.class)
            fragment = new AddItemFragment(controller);
        else if (fragmentClass == PurchasedListScreenFragment.class)
            fragment = new PurchasedListScreenFragment(controller);
        else if (fragmentClass == TransactionsScreenFragment.class)
            fragment = new TransactionsScreenFragment(controller);
        else fragment = super.instantiate(classLoader, className);

        return fragment;
    }




}
