package edu.vassar.cmpu.test.view.loginScreen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.vassar.cmpu.test.databinding.FragmentLoginScreenBinding;
import edu.vassar.cmpu.test.domain.HouseController;
import edu.vassar.cmpu.test.domain.ShoppingList;
import edu.vassar.cmpu.test.view.homeScreen.IShoppingListScreenView;

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
            this.listener.onCreateHouse(this.binding.houseName.getText().toString(),
                    this.binding.newMembersName.getText().toString());
        });
    }
}