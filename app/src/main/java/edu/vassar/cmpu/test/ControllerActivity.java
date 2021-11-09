package edu.vassar.cmpu.test;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import edu.vassar.cmpu.test.view.addItemView.AddItemFragment;
import edu.vassar.cmpu.test.view.homeScreen.MainScreenFragment;

public class ControllerActivity extends AppCompatActivity {
    //extends makes this class an activity

    private IMainView mainView;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        mainView = new MainView(this);
        setContentView(mainView.getRootView());

        this.mainView.displayFragment(new MainScreenFragment());//displays the add Item Fragment
    }



}
