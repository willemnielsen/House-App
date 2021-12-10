package edu.vassar.cmpu.test.view.loginScreen;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import edu.vassar.cmpu.test.R;
import edu.vassar.cmpu.test.databinding.FragmentAuthBinding;
import edu.vassar.cmpu.test.databinding.FragmentLoginScreenBinding;
import edu.vassar.cmpu.test.view.authScreen.AuthFragment;
import edu.vassar.cmpu.test.view.authScreen.IAuthView;

public class LoginScreenFragment extends Fragment implements ILoginScreenFragment {

    private static final String IS_REGISTERED = "isRegistered";

    private final ILoginScreenFragment.Listener listener;
    private FragmentLoginScreenBinding binding;
    private boolean isRegistered = false;


    public LoginScreenFragment(Listener listener) {
        this.listener = listener;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentLoginScreenBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
/*        this.binding.joinHouseButton.setOnClickListener((View clickedView) -> {
            if (this.binding.houseName.getText().toString().isEmpty()) {
                this.binding.houseName.setError("Please Enter a valid House Name");
            }
            if (this.binding.housePassword.getText().toString().isEmpty()) {
                this.binding.housePassword.setError("Please Enter a Valid Password");
            }
            else{
                this.listener.onCreateHouse(this.binding.houseName.getText().toString(),
                        this.binding.housePassword.getText().toString());
            }
        });*/
        if (savedInstanceState != null && savedInstanceState.getBoolean(IS_REGISTERED))
            activateRegisteredConfig();

        this.binding.joinHouseButton.setOnClickListener( (clickedView) ->{
            String username = this.binding.houseName.getText().toString();
            String password = this.binding.housePassword.getText().toString();
            LoginScreenFragment.this.listener.onRegisterHouse(
                    username, password,LoginScreenFragment.this);
        });

        this.binding.joinHouseButton2.setOnClickListener( (clickedView) ->{
            String username = this.binding.houseName.getText().toString();
            String password = this.binding.housePassword.getText().toString();
            LoginScreenFragment.this.listener.onHouseSigninAttempt(
                    username, password, LoginScreenFragment.this);
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(IS_REGISTERED, this.isRegistered);
    }

    @Override
    public void onRegisterHouseSuccess() {
        activateRegisteredConfig();
        this.displayMessage(R.string.registration_success_msg);
    }

    // prevent multiple registration attempts
    private void activateRegisteredConfig(){
        this.isRegistered = true;
        this.binding.houseName.setEnabled(false);
    }

    @Override
    public void onInvalidHouseCredentials() {
        displayMessage(R.string.invalid_credentials_msg);
    }

    @Override
    public void onHouseAlreadyExists() {
        displayMessage(R.string.house_already_exists_msg);
    }

    private void displayMessage(int msgRid){
        Snackbar.make(this.binding.getRoot(),
                msgRid,
                Snackbar.LENGTH_LONG)
                .show();
    }
}