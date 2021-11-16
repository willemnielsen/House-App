package edu.vassar.cmpu.test.view.housemateListScreen.addHousemate;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.vassar.cmpu.test.R;
import edu.vassar.cmpu.test.databinding.FragmentAddHousemateBinding;

public class AddHousemateFragment extends Fragment implements IAddHousemate {


    FragmentAddHousemateBinding binding;
    Listener listener;

    public AddHousemateFragment(Listener listener) {
        this.listener = listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAddHousemateBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.binding.addButton.setOnClickListener((View clickedView) -> {
            Editable name = this.binding.typeHousemateName.getEditableText();
            String nameStr = name.toString();
            if(nameStr.length() != 0){
                this.listener.onAddHousemate(nameStr);
            }
            this.binding.typeHousemateName.setText("");
        });

        this.binding.previous.setOnClickListener((View clickedView) -> {
            this.listener.onPreviousOnAddHousemateScreen();
        });
    }

}