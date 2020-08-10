package in.socialninja.bloodplus.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import in.socialninja.bloodplus.R;
import in.socialninja.bloodplus.modal.SearchContactModal;

/**
 * Created by Unique on 10-03-2018.
 */

public class SearchContact extends Fragment {
    View view;
    TextView btn_a_plus, btn_b_plus, btn_o_plus, btn_ab_plus, btn_a_minus, btn_b_minus, btn_o_minus, btn_ab_minus, btn_search;
    String blood_group;
    Spinner city_name;
    RecyclerView contactlist;
    SearchContactModal scmodal[];
    List<SearchContactModal> list_contact = new ArrayList<>();

    //CardView blood_compatibility,nearest_hospital;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_contactsearch, container, false);
        city_name = (Spinner) view.findViewById(R.id.city_spinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getContext(), R.array.drop_city, R.layout.spinner_item);
        btn_a_plus = (TextView) view.findViewById(R.id.a_plus);
        btn_b_plus = (TextView) view.findViewById(R.id.b_plus);
        btn_ab_plus = (TextView) view.findViewById(R.id.ab_plus);
        btn_o_plus = (TextView) view.findViewById(R.id.o_plus);
        btn_a_minus = (TextView) view.findViewById(R.id.a_minus);
        btn_b_minus = (TextView) view.findViewById(R.id.b_minus);
        btn_ab_minus = (TextView) view.findViewById(R.id.ab_minus);
        btn_o_minus = (TextView) view.findViewById(R.id.o_minus);
        btn_search = (TextView) view.findViewById(R.id.btn_search);
        city_name.setAdapter(adapter);
        btn_a_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_a_plus.setBackgroundResource(R.drawable.roundshapered);
                btn_b_minus.setBackgroundResource(R.drawable.blood_group_btn);
                btn_ab_minus.setBackgroundResource(R.drawable.blood_group_btn);
                btn_o_minus.setBackgroundResource(R.drawable.blood_group_btn);
                btn_b_plus.setBackgroundResource(R.drawable.blood_group_btn);
                btn_ab_plus.setBackgroundResource(R.drawable.blood_group_btn);
                btn_o_plus.setBackgroundResource(R.drawable.blood_group_btn);
                btn_a_minus.setBackgroundResource(R.drawable.blood_group_btn);
                blood_group = "A+";
            }
        });
        btn_a_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_a_plus.setBackgroundResource(R.drawable.blood_group_btn);
                btn_b_minus.setBackgroundResource(R.drawable.blood_group_btn);
                btn_ab_minus.setBackgroundResource(R.drawable.blood_group_btn);
                btn_o_minus.setBackgroundResource(R.drawable.blood_group_btn);
                btn_b_plus.setBackgroundResource(R.drawable.blood_group_btn);
                btn_ab_plus.setBackgroundResource(R.drawable.blood_group_btn);
                btn_o_plus.setBackgroundResource(R.drawable.blood_group_btn);
                btn_a_minus.setBackgroundResource(R.drawable.roundshapered);
                blood_group = "A-";
            }
        });
        btn_b_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_a_plus.setBackgroundResource(R.drawable.blood_group_btn);
                btn_b_minus.setBackgroundResource(R.drawable.blood_group_btn);
                btn_ab_minus.setBackgroundResource(R.drawable.blood_group_btn);
                btn_o_minus.setBackgroundResource(R.drawable.blood_group_btn);
                btn_b_plus.setBackgroundResource(R.drawable.roundshapered);
                btn_ab_plus.setBackgroundResource(R.drawable.blood_group_btn);
                btn_o_plus.setBackgroundResource(R.drawable.blood_group_btn);
                btn_a_minus.setBackgroundResource(R.drawable.blood_group_btn);
                blood_group = "B+";
            }
        });
        btn_b_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_a_plus.setBackgroundResource(R.drawable.blood_group_btn);
                btn_b_minus.setBackgroundResource(R.drawable.roundshapered);
                btn_ab_minus.setBackgroundResource(R.drawable.blood_group_btn);
                btn_o_minus.setBackgroundResource(R.drawable.blood_group_btn);
                btn_b_plus.setBackgroundResource(R.drawable.blood_group_btn);
                btn_ab_plus.setBackgroundResource(R.drawable.blood_group_btn);
                btn_o_plus.setBackgroundResource(R.drawable.blood_group_btn);
                btn_a_minus.setBackgroundResource(R.drawable.blood_group_btn);
                blood_group = "B-";
            }
        });
        btn_ab_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_a_plus.setBackgroundResource(R.drawable.blood_group_btn);
                btn_b_minus.setBackgroundResource(R.drawable.blood_group_btn);
                btn_ab_minus.setBackgroundResource(R.drawable.blood_group_btn);
                btn_o_minus.setBackgroundResource(R.drawable.blood_group_btn);
                btn_b_plus.setBackgroundResource(R.drawable.blood_group_btn);
                btn_ab_plus.setBackgroundResource(R.drawable.roundshapered);
                btn_o_plus.setBackgroundResource(R.drawable.blood_group_btn);
                btn_a_minus.setBackgroundResource(R.drawable.blood_group_btn);
                blood_group = "AB+";
            }
        });
        btn_ab_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_a_plus.setBackgroundResource(R.drawable.blood_group_btn);
                btn_b_minus.setBackgroundResource(R.drawable.blood_group_btn);
                btn_ab_minus.setBackgroundResource(R.drawable.roundshapered);
                btn_o_minus.setBackgroundResource(R.drawable.blood_group_btn);
                btn_b_plus.setBackgroundResource(R.drawable.blood_group_btn);
                btn_ab_plus.setBackgroundResource(R.drawable.blood_group_btn);
                btn_o_plus.setBackgroundResource(R.drawable.blood_group_btn);
                btn_a_minus.setBackgroundResource(R.drawable.blood_group_btn);
                blood_group = "AB-";
            }
        });
        btn_o_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_a_plus.setBackgroundResource(R.drawable.blood_group_btn);
                btn_b_minus.setBackgroundResource(R.drawable.blood_group_btn);
                btn_ab_minus.setBackgroundResource(R.drawable.blood_group_btn);
                btn_o_minus.setBackgroundResource(R.drawable.roundshapered);
                btn_b_plus.setBackgroundResource(R.drawable.blood_group_btn);
                btn_ab_plus.setBackgroundResource(R.drawable.blood_group_btn);
                btn_o_plus.setBackgroundResource(R.drawable.blood_group_btn);
                btn_a_minus.setBackgroundResource(R.drawable.blood_group_btn);
                blood_group = "O-";
            }
        });
        btn_o_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_a_plus.setBackgroundResource(R.drawable.blood_group_btn);
                btn_b_minus.setBackgroundResource(R.drawable.blood_group_btn);
                btn_ab_minus.setBackgroundResource(R.drawable.blood_group_btn);
                btn_o_minus.setBackgroundResource(R.drawable.blood_group_btn);
                btn_b_plus.setBackgroundResource(R.drawable.blood_group_btn);
                btn_ab_plus.setBackgroundResource(R.drawable.blood_group_btn);
                btn_o_plus.setBackgroundResource(R.drawable.roundshapered);
                btn_a_minus.setBackgroundResource(R.drawable.blood_group_btn);
                blood_group = "O+";
            }
        });
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city = city_name.getSelectedItem().toString();
                Bundle args = new Bundle();
                args.putString("bloodgroup", blood_group);
                args.putString("cityname", city);
                SearchContactResult scr = new SearchContactResult();
                scr.setArguments(args);
                transact(scr);

            }
        });
        initComponent(view);
        return view;
    }

    private void initComponent(View view) {

    }

    public void transact(Fragment fragment) {
        FragmentTransaction transf = getFragmentManager().beginTransaction();
        transf.replace(R.id.root_frame, fragment);
        transf.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transf.addToBackStack(null);
        transf.commit();
    }
}