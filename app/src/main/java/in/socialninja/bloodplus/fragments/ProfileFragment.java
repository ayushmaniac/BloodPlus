package in.socialninja.bloodplus.fragments;

import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import in.socialninja.bloodplus.R;
import in.socialninja.bloodplus.httphandler.SHPref;

/**
 * Created by Unique on 12-03-2018.
 */

public class ProfileFragment extends Fragment {
    View mview;
    String bloodgroup;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mview = inflater.inflate(R.layout.profile_popup, container, false);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Explode exp = new Explode();
            setExitTransition(exp);
        }


        TextView name = (TextView) mview.findViewById(R.id.prof_name);
        TextView email = (TextView) mview.findViewById(R.id.prof_email);
        TextView blood = (TextView) mview.findViewById(R.id.prof_blood);
        TextView city = (TextView) mview.findViewById(R.id.prof_city);
        TextView dob = (TextView) mview.findViewById(R.id.prof_dob);
        TextView lastdonate = (TextView) mview.findViewById(R.id.prof_lastdonate);
        TextView medicalprob = (TextView) mview.findViewById(R.id.prof_mprob);
        TextView mob = (TextView) mview.findViewById(R.id.prof_mobnum);
        CircleImageView img = (CircleImageView) mview.findViewById(R.id.prof_img);
        TextView ok = (TextView) mview.findViewById(R.id.prof_okbtn);
        if (Objects.equals(SHPref.getDefaults("bloodgroup", getContext()), "A[plus]")) {
            bloodgroup = "A+";
        }
        if (Objects.equals(SHPref.getDefaults("bloodgroup", getContext()), "B[plus]")) {
            bloodgroup = "B+";
        }
        if (Objects.equals(SHPref.getDefaults("bloodgroup", getContext()), "O[plus]")) {
            bloodgroup = "O+";
        }
        if (Objects.equals(SHPref.getDefaults("bloodgroup", getContext()), "AB[plus]")) {
            bloodgroup = "AB+";
        }
        if (Objects.equals(SHPref.getDefaults("bloodgroup", getContext()), "A-")) {
            bloodgroup = "A-";
        }
        if (Objects.equals(SHPref.getDefaults("bloodgroup", getContext()), "B-")) {
            bloodgroup = "B-";
        }
        if (Objects.equals(SHPref.getDefaults("bloodgroup", getContext()), "AB-")) {
            bloodgroup = "AB-";
        }
        if (Objects.equals(SHPref.getDefaults("bloodgroup", getContext()), "O-")) {
            bloodgroup = "O-";
        }


        name.setText(SHPref.getDefaults("name", getContext()));
        email.setText(SHPref.getDefaults("email", getContext()));
        blood.setText(bloodgroup);
        city.setText(SHPref.getDefaults("city", getContext()));
        dob.setText(SHPref.getDefaults("dofb", getContext()));
        lastdonate.setText(SHPref.getDefaults("lastdonate", getContext()));


        mob.setText(SHPref.getDefaults("contact", getContext()));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Objects.equals(SHPref.getDefaults("gender", getContext()), "Male")) {
                img.setImageResource(R.drawable.male);
            }
            if (Objects.equals(SHPref.getDefaults("gender", getContext()), "Female")) {
                img.setImageResource(R.drawable.female);
            }
            if (Objects.equals(SHPref.getDefaults("medical_problem", getContext()), "0")) {
                medicalprob.setText("No");
            }
            if (Objects.equals(SHPref.getDefaults("medical_problem", getContext()), "1")) {
                medicalprob.setText("Yes");
            }
        } else {
            medicalprob.setText(SHPref.getDefaults("medical_problem", getContext()));
            img.setImageResource(R.drawable.friend);
        }
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FrontFragment fm = new FrontFragment();
                transact(fm);
            }
        });

        return mview;
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
