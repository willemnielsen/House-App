package edu.vassar.cmpu.test.view.loginScreen;

import edu.vassar.cmpu.test.view.authScreen.IAuthView;

public interface ILoginScreenFragment {

    interface Listener{
        void onRegisterHouse(String houseName, String housePassword, ILoginScreenFragment loginScreenFragment);
        void onHouseSigninAttempt(String houseName, String housePassword, ILoginScreenFragment loginScreenFragment);
    }
    void onRegisterHouseSuccess();
    void onInvalidHouseCredentials();
    void onHouseAlreadyExists();

   // void updateDisplay();

}
