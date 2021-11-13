package edu.vassar.cmpu.test.view.purchasedListScreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import edu.vassar.cmpu.test.databinding.FragmentLoginScreenBinding;
import edu.vassar.cmpu.test.databinding.FragmentPurchasedBinding;
import edu.vassar.cmpu.test.view.loginScreen.ILoginScreenFragment;

public class PurchasedListScreenFragment extends Fragment implements IPurchasedListScreenFragment {
    private FragmentPurchasedBinding binding;
    private ILoginScreenFragment.Listener listener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentPurchasedBinding.inflate(inflater);
        return this.binding.getRoot();
    }

   // @Override
   // public void onViewCreated(View view, Bundle savedInstanceState) {
    //    this.binding..setOnClickListener((View clickedView) -> {
    //        this.listener.onCreateHouse(this.binding.houseName.getText().toString(),
    //                this.binding.newMembersName.getText().toString());
   //     });
  //  }
}
