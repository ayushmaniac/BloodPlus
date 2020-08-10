package in.socialninja.bloodplus.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import in.socialninja.bloodplus.R;

/**
 * Created by Unique on 06-03-2018.
 */

public class SignInUp_root extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /* Inflate the layout for this fragment */
        View view = inflater.inflate(R.layout.root_frame, container, false);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        /*
         * When this container fragment is created, we fill it with our first
         * "real" fragment
         */
        transaction.replace(R.id.root_frame, new Login_Fragmnet());

        transaction.commit();

        return view;
    }
}