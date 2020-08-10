package in.socialninja.bloodplus.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import in.socialninja.bloodplus.R;
import in.socialninja.bloodplus.adapters.SearchContactAdapter;
import in.socialninja.bloodplus.httphandler.HttpClientActivity;
import in.socialninja.bloodplus.httphandler.InternetConnection;
import in.socialninja.bloodplus.modal.SearchContactModal;

/**
 * Created by Unique on 10-03-2018.
 */

public class SearchContactResult extends Fragment {
    View view;
    RecyclerView contactlist;
    SearchContactModal scmodal[];
    List<SearchContactModal> list_contact = new ArrayList<>();
    String bloodgr;

    //CardView blood_compatibility,nearest_hospital;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_contactsearchresult, container, false);
        Bundle args = getArguments();
        contactlist = view.findViewById(R.id.contactsarchresult);
        String city = args.getString("cityname");
        String blood = args.getString("bloodgroup");
        if (blood == "O-") {
            getSearchContact(city, "O-");
        }
        if (blood == "A-") {
            getSearchContact(city, "A-");
            getSearchContact(city, "O-");
        }
        if (blood == "B-") {
            getSearchContact(city, "B-");
            getSearchContact(city, "O-");
        }
        if (blood == "AB-") {
            getSearchContact(city, "A-");
            getSearchContact(city, "B-");
            getSearchContact(city, "AB-");
            getSearchContact(city, "O-");
        }
        if (blood == "O+") {
            getSearchContact(city, "O[plus]");
            getSearchContact(city, "O-");
        }
        if (blood == "A+") {
            getSearchContact(city, "A[plus]");
            getSearchContact(city, "A-");
            getSearchContact(city, "O[plus]");
            getSearchContact(city, "O-");
        }
        if (blood == "B+") {
            getSearchContact(city, "B[plus]");
            getSearchContact(city, "B-");
            getSearchContact(city, "O[plus]");
            getSearchContact(city, "O-");
        }
        if (blood == "AB+") {
            getSearchContact(city, "A[plus]");
            getSearchContact(city, "A-");
            getSearchContact(city, "B[plus]");
            getSearchContact(city, "B-");
            getSearchContact(city, "AB[plus]");
            getSearchContact(city, "AB-");
            getSearchContact(city, "O[plus]");
            getSearchContact(city, "O-");
        }
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

    public void getSearchContact(String city, String blood) {
        //Toast.makeText(getContext(), city+"   "+blood, Toast.LENGTH_SHORT).show();
        RequestParams params = new RequestParams();
        params.put("bu_city", city);
        params.put("bu_b_group", blood);
        if (InternetConnection.checkConnection(getContext())) {
            HttpClientActivity.post("/users/search/", params, new JsonHttpResponseHandler() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    try {
                        JSONArray contacts = response.getJSONArray("response");
                        for (int i = 0; i < contacts.length(); i++) {
                            JSONObject c = contacts.getJSONObject(i);

                            String bu_id = c.getString("bu_id");
                            String bu_name = c.getString("bu_name");
                            String bu_email = c.getString("bu_email");
                            String bu_gender = c.getString("bu_gender");
                            String bu_city = c.getString("bu_city");
                            bloodgr = c.getString("bu_b_group");
                            String bu_dob = c.getString("bu_dob");
                            String bu_contact = c.getString("bu_contact");
                            String bu_d_date = c.getString("bu_d_date");
                            String bu_m_problem = c.getString("bu_m_problem");
//id, name,city,dob,gender,contact,emailaddress,problem,bloodgroup,lastdonatedate
                            //Toast.makeText(getContext(),bloodgr,Toast.LENGTH_LONG).show();
                            SearchContactModal nav = new SearchContactModal(bu_id, bu_name, bu_city, bu_dob, bu_gender, bu_contact, bu_email, bu_m_problem, bloodgr, bu_d_date);
                            list_contact.add(nav);
                        }
                        scmodal = list_contact.toArray(new SearchContactModal[list_contact.size()]);
                        SearchContactAdapter scm = new SearchContactAdapter(getActivity(), getContext(), scmodal);
                        contactlist.setLayoutManager(new LinearLayoutManager(getActivity()));
                        contactlist.setAdapter(scm);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    super.onFailure(statusCode, headers, responseString, throwable);
                    if (statusCode <= 400) {
                        Toast.makeText(getContext(), "Bad Request or Server not available", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Something went wrong please check your connection " + statusCode, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(getContext(), "Please check your connection", Toast.LENGTH_SHORT).show();
        }
    }
}
