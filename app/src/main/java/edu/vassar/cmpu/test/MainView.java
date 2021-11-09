package edu.vassar.cmpu.test;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import edu.vassar.cmpu.test.databinding.MainBinding;

public class MainView implements IMainView {

    FragmentActivity activity;
    MainBinding binding;

    public MainView(FragmentActivity activity){
        this.activity = activity;
        binding = MainBinding.inflate(activity.getLayoutInflater());
    }

    @Override
    public View getRootView() {
        return binding.getRoot();
    }

    @Override
    public void displayFragment(Fragment fragment) {
        FragmentManager fragmentManager= this.activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(this.binding.fragmentContainerView.getId(), fragment);
        fragmentTransaction.commitNow();
    }
}
