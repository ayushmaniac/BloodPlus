package in.socialninja.bloodplus.fragments;

import android.os.Bundle;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.Objects;

import cz.msebera.android.httpclient.Header;
import in.socialninja.bloodplus.R;
import in.socialninja.bloodplus.adapters.DateDialog;
import in.socialninja.bloodplus.httphandler.HttpClientActivity;
import in.socialninja.bloodplus.httphandler.InternetConnection;

/**
 * Created by Unique on 07-03-2018.
 */

public class RegistrationFragment2 extends Fragment {
    View view;
    TextView reg_btn_submit;
    EditText donate_date;
    TextView btn_a_plus, btn_b_plus, btn_o_plus, btn_ab_plus, btn_a_minus, btn_b_minus, btn_o_minus, btn_ab_minus;
    String blood_group;
    Spinner city_name;
    RadioGroup radio_btn;
    RadioButton med_radio;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_registration2, container, false);
        setAllowEnterTransitionOverlap(false);
        donate_date = (EditText) view.findViewById(R.id.txtDatepic);
        radio_btn = (RadioGroup) view.findViewById(R.id.medicationgroup);

        btn_a_plus = (TextView) view.findViewById(R.id.a_plus);
        btn_b_plus = (TextView) view.findViewById(R.id.b_plus);
        btn_ab_plus = (TextView) view.findViewById(R.id.ab_plus);
        btn_o_plus = (TextView) view.findViewById(R.id.o_plus);
        btn_a_minus = (TextView) view.findViewById(R.id.a_minus);
        btn_b_minus = (TextView) view.findViewById(R.id.b_minus);
        btn_ab_minus = (TextView) view.findViewById(R.id.ab_minus);
        btn_o_minus = (TextView) view.findViewById(R.id.o_minus);
        reg_btn_submit = (TextView) view.findViewById(R.id.reg_btn_submit);
        city_name = (Spinner) view.findViewById(R.id.city_spinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(Objects.requireNonNull(getContext()), R.array.drop_city, R.layout.spinner_item);
        city_name.setAdapter(adapter);
        Bundle args = this.getArguments();
        assert args != null;
        final String name = args.getString("name");
        final String mobile = args.getString("mobile");
        final String gender = args.getString("gender");
        final String dob = args.getString("dob");
        final String email = args.getString("email");
        final String pass = args.getString("password");
        final String date_of_donation = donate_date.getText().toString();
        final String city = city_name.getSelectedItem().toString();


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Explode slide = new Explode();
            slide.setDuration(800);
            setEnterTransition(slide);
        }
        donate_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dfrag = new DateDialog(view);
                assert getFragmentManager() != null;
                dfrag.show(getFragmentManager(), "DateFragment ");
            }
        });
        reg_btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (blood_group.equals("") || blood_group.isEmpty()) {
                    Toast.makeText(getContext(), "Select blood group first", Toast.LENGTH_LONG).show();
                } else {
                    int countId = radio_btn.getCheckedRadioButtonId();
                    med_radio = (RadioButton) view.findViewById(countId);
                    String health = med_radio.getText().toString();
                    registration_fun(name, email, mobile, gender, dob, pass, blood_group, date_of_donation, health, city);
                }
            }
        });
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
                blood_group = "A[plus]";
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
                blood_group = "B[plus]";
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
                blood_group = "AB[plus]";
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
                blood_group = "O[plus]";
            }
        });


        initComponent(view);
        return view;
    }

    private void initComponent(View view) {

    }

    public void registration_fun(String name, String email, String mobile, String gender, String dob, String pass, String bloodgroup, String lastDonateDate, String anyMedicinalProblem, String city) {
        RequestParams params = new RequestParams();
        params.put("bu_name", name);
        params.put("bu_contact", mobile);
        params.put("bu_email", email);
        params.put("bu_dob", dob);
        params.put("bu_gender", gender);
        params.put("bu_b_group", bloodgroup);
        params.put("bu_d_date", lastDonateDate);
        params.put("bu_m_problem", anyMedicinalProblem);
        params.put("bu_password", pass);
        params.put("bu_city", city);
        if (InternetConnection.checkConnection(Objects.requireNonNull(getContext()))) {
            HttpClientActivity.post("users/", params, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    try {
                        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(Objects.requireNonNull(getContext()));
                        final View mview = Objects.requireNonNull(getActivity()).getLayoutInflater().inflate(R.layout.layout_thankyou, null);

                        final TextView okbtn = (TextView) mview.findViewById(R.id.ok_btn);

                        alertBuilder.setView(mview);
                        final AlertDialog dialog = alertBuilder.create();
                        okbtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Login_Fragmnet lf = new Login_Fragmnet();
                                transact(lf);
                                dialog.dismiss();
                            }
                        });
                        dialog.show();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    super.onFailure(statusCode, headers, responseString, throwable);
                    if (statusCode <= 400) {
                        Toast.makeText(getContext(), "Bad Request or Server not available", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Something went wrong please check your connection", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(getContext(), "Please check your connection", Toast.LENGTH_SHORT).show();
        }
    }

    public void transact(Fragment fragment) {
        assert getFragmentManager() != null;
        FragmentTransaction transf = getFragmentManager().beginTransaction();
        transf.replace(R.id.root_frame, fragment);
        transf.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transf.addToBackStack(null);
        transf.commit();
    }
}