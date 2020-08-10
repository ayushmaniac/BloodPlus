package in.socialninja.bloodplus.fragments;

import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import in.socialninja.bloodplus.R;
import in.socialninja.bloodplus.adapters.DateDialog;

/**
 * Created by Unique on 06-03-2018.
 */

public class RegistrationFragment extends Fragment {
    View view;
    TextView reg_btn_next, signin_btn;
    EditText dob, name, email, mobile, pass, cpass;
    RadioButton gender;
    RadioGroup genderGroup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_registration, container, false);
        setAllowEnterTransitionOverlap(false);
        reg_btn_next = (TextView) view.findViewById(R.id.reg_btn_next);
        signin_btn = (TextView) view.findViewById(R.id.signin_btn);
        dob = (EditText) view.findViewById(R.id.datePicker);
        name = (EditText) view.findViewById(R.id.reg_name);
        email = (EditText) view.findViewById(R.id.reg_email);
        mobile = (EditText) view.findViewById(R.id.reg_mob);
        pass = (EditText) view.findViewById(R.id.reg_pass);
        cpass = (EditText) view.findViewById(R.id.reg_cpass);
        genderGroup = (RadioGroup) view.findViewById(R.id.sGroup);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Slide slide = new Slide();
            slide.setDuration(800);
            setExitTransition(slide);
        }
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dfrag = new DateDialog(view);
                dfrag.show(getActivity().getSupportFragmentManager(), "DateFragment ");
            }
        });
        reg_btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().isEmpty()) {
                    name.setError("Please Enter Name");
                }
                if (dob.getText().toString().isEmpty()) {
                    dob.setError("please select date of birth");
                }
                if (email.getText().toString().trim().isEmpty()) {
                    email.setError("Enter email its required");
                }
                if (mobile.getText().toString().length() <= 9 || mobile.getText().toString().isEmpty()) {
                    mobile.setError("please provide valid mobile number");
                }
                if (!pass.getText().toString().trim().equals(cpass.getText().toString().trim()) || pass.getText().toString().isEmpty()) {
                    cpass.setError("Password not matched");
                    pass.setError("Password not matched");
                }
                if (pass.getText().toString().length() < 8) {
                    pass.setError("Password must be 8 character");
                } else {
                    int selectedID = genderGroup.getCheckedRadioButtonId();
                    gender = (RadioButton) view.findViewById(selectedID);
                    RegistrationFragment2 frag = new RegistrationFragment2();
                    Bundle args = new Bundle();
                    args.putString("name", name.getText().toString());
                    args.putString("mobile", mobile.getText().toString());
                    args.putString("gender", gender.getText().toString());
                    args.putString("dob", dob.getText().toString());
                    args.putString("email", email.getText().toString().trim());
                    args.putString("password", cpass.getText().toString());
                    frag.setArguments(args);
                    transact(frag);
                }
            }
        });
        signin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    Fade slide = new Fade();
                    slide.setDuration(800);
                    setExitTransition(slide);
                }
                Login_Fragmnet frag = new Login_Fragmnet();
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
}
