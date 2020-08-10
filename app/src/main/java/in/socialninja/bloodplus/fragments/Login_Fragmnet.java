package in.socialninja.bloodplus.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.Objects;

import cz.msebera.android.httpclient.Header;
import in.socialninja.bloodplus.MainActivity;
import in.socialninja.bloodplus.R;
import in.socialninja.bloodplus.httphandler.HttpClientActivity;
import in.socialninja.bloodplus.httphandler.InternetConnection;
import in.socialninja.bloodplus.httphandler.SHPref;

/**
 * Created by Unique on 06-03-2018.
 */

public class Login_Fragmnet extends Fragment {
    View view;
    TextView login_btn, sign_up;
    EditText useremail, userpass;
    String bloodgroup;
    ProgressDialog mProgress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_login, container, false);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Fade explode = new Fade();
            explode.setDuration(800);
            setEnterTransition(explode);
        }

        login_btn = (TextView) view.findViewById(R.id.login_btn);
        sign_up = (TextView) view.findViewById(R.id.signup_btn);
        useremail = (EditText) view.findViewById(R.id.login_email);
        userpass = (EditText) view.findViewById(R.id.login_pass);
        ImageView imageView = (ImageView) view.findViewById(R.id.heart);
        imageView.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.pulse));


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (useremail.getText().toString().isEmpty()) {
                    useremail.setError("Please provide user email address");
                }
                if (userpass.getText().toString().isEmpty()) {
                    userpass.setError("Please Enter valid password");
                } else {
                    String un = useremail.getText().toString();
                    String up = userpass.getText().toString();
//                    mProgress = new ProgressDialog(getContext());
//                    mProgress.setCancelable(false);
//                    mProgress.setTitle("Logging in");
//                    mProgress.setMessage("Loading Please wait...");
//                    mProgress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//                    mProgress.setMax(100);
//                    mProgress.setProgress(0);
//                    mProgress.show();
                    login_fun(un, up);
                }
            }
        });
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    Fade slide = new Fade();
                    slide.setDuration(800);
                    setExitTransition(slide);
                }
                RegistrationFragment frag = new RegistrationFragment();
                transact(frag);

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

    public void login_fun(String user, String pass) {
        RequestParams params = new RequestParams();
        params.put("bu_email", user);
        params.put("bu_password", pass);
        if (InternetConnection.checkConnection(getContext())) {
            HttpClientActivity.post("users/login/", params, new JsonHttpResponseHandler() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    try {
                        JSONObject jsonObj = response.getJSONObject("response");
                        String id = jsonObj.getString("bu_id");
                        String name = jsonObj.getString("bu_name");
                        String email = jsonObj.getString("bu_email");
                        String contact = jsonObj.getString("bu_contact");
                        String gender = jsonObj.getString("bu_gender");
                        String pasword = jsonObj.getString("bu_password");
                        String city = jsonObj.getString("bu_city");
                        String dob = jsonObj.getString("bu_dob");
                        //Toast.makeText(getContext(),response.toString(),Toast.LENGTH_LONG).show();

                        if (Objects.equals(jsonObj.getString("bu_b_group"), "A[plus]")) {
                            bloodgroup = "A+";
                        }
                        if (Objects.equals(jsonObj.getString("bu_b_group"), "B[plus]")) {
                            bloodgroup = "B+";
                        }
                        if (Objects.equals(jsonObj.getString("bu_b_group"), "AB[plus]")) {
                            bloodgroup = "AB+";
                        }
                        if (Objects.equals(jsonObj.getString("bu_b_group"), "O[plus]")) {
                            bloodgroup = "O+";
                        } else {
                            bloodgroup = jsonObj.getString("bu_b_group");
                        }

                        String lastdonate = jsonObj.getString("bu_d_date");
                        String mproblem = jsonObj.getString("bu_m_problem");
                        SHPref.setDefaults("id", id, getContext());
                        SHPref.setDefaults("name", name, getContext());
                        SHPref.setDefaults("email", email, getContext());
                        SHPref.setDefaults("contact", contact, getContext());
                        SHPref.setDefaults("gender", gender, getContext());
                        SHPref.setDefaults("password", pasword, getContext());
                        SHPref.setDefaults("dofb", dob, getContext());
                        SHPref.setDefaults("city", city, getContext());
                        SHPref.setDefaults("bloodgroup", bloodgroup, getContext());
                        SHPref.setDefaults("lastdonate", lastdonate, getContext());
                        SHPref.setDefaults("medical_problem", mproblem, getContext());
                        //Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    super.onFailure(statusCode, headers, responseString, throwable);
                    if (statusCode == 200) {
                        Toast.makeText(getContext(), "Database not found ", Toast.LENGTH_SHORT).show();
                    }
                    if (statusCode >= 400) {
                        Toast.makeText(getContext(), "Server not available " + statusCode, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(getContext(), "Please check your connection", Toast.LENGTH_SHORT).show();
        }
    }
}
