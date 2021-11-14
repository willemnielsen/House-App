package edu.vassar.cmpu.test.view.loginScreen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.vassar.cmpu.test.databinding.FragmentLoginScreenBinding;

public class LoginScreenFragment extends Fragment implements ILoginScreenFragment {

    private Listener listener;
    private FragmentLoginScreenBinding binding;

    public LoginScreenFragment() {

    }

    public LoginScreenFragment(Listener listener) {
        this.listener = listener;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentLoginScreenBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.binding.joinHouseButton.setOnClickListener((View clickedView) -> {
            if (this.binding.houseName.getText().toString().isEmpty()) {
                this.binding.houseName.setError("Please Enter a valid House Name");
            }
            if (this.binding.newMembersName.getText().toString().isEmpty()) {
                this.binding.newMembersName.setError("Please Enter a Valid Name");
            }
            else{
                this.listener.onCreateHouse(this.binding.houseName.getText().toString(),
                        this.binding.newMembersName.getText().toString());
            }
        });
    }
}