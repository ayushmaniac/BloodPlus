package in.socialninja.bloodplus.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import in.socialninja.bloodplus.R;
import in.socialninja.bloodplus.httphandler.SHPref;

/**
 * Created by Unique on 06-03-2018.
 */

public class FrontFragment extends Fragment {
    View view;
    CardView blood_compatibility, nearest_hospital, request_blood, donate_blood;
    ProgressDialog mProgress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);
        blood_compatibility = (CardView) view.findViewById(R.id.btn_bloodcompat);
        nearest_hospital = (CardView) view.findViewById(R.id.btn_hospital);
        request_blood = (CardView) view.findViewById(R.id.btn_requestblood);
        donate_blood = (CardView) view.findViewById(R.id.btn_donate);
        ImageView imageView = (ImageView) view.findViewById(R.id.heart);
        imageView.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.pulse));
        donate_blood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // profile_alert(getContext(),getActivity());
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    Explode explode = new Explode();
                    setExitTransition(explode);
                }
                ProfileFragment intent = new ProfileFragment();
                //intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                transact(intent);
            }
        });
        blood_compatibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    Explode explode = new Explode();
                    setExitTransition(explode);
                }
                BloodCompatibility compatibility = new BloodCompatibility();
                transact(compatibility);
            }
        });
        nearest_hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    Explode explode = new Explode();
                    setExitTransition(explode);
                }
                NearestHospital nearestHospital = new NearestHospital();
                transact(nearestHospital);
            }
        });
        request_blood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    Explode explode = new Explode();
                    setExitTransition(explode);
                }
                SearchContact searchContact = new SearchContact();
                transact(searchContact);
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

    public void profile_alert(Context c, Activity a) {

        @SuppressLint("RestrictedApi")
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(new ContextThemeWrapper(c, R.style.AlertDialogCustom));
        final View mview = a.getLayoutInflater().inflate(R.layout.profile_popup, null);

        final TextView okbtn = (TextView) mview.findViewById(R.id.prof_okbtn);
        TextView name = (TextView) mview.findViewById(R.id.prof_name);
        TextView email = (TextView) mview.findViewById(R.id.prof_email);
        TextView blood = (TextView) mview.findViewById(R.id.prof_blood);
        TextView city = (TextView) mview.findViewById(R.id.prof_city);
        TextView dob = (TextView) mview.findViewById(R.id.prof_dob);
        TextView lastdonate = (TextView) mview.findViewById(R.id.prof_lastdonate);
        TextView medicalprob = (TextView) mview.findViewById(R.id.prof_mprob);
        TextView mob = (TextView) mview.findViewById(R.id.prof_mobnum);
        CircleImageView img = (CircleImageView) mview.findViewById(R.id.prof_img);

        name.setText(SHPref.getDefaults("name", c));
        email.setText(SHPref.getDefaults("email", c));
        blood.setText(SHPref.getDefaults("bloodgroup", c));
        city.setText(SHPref.getDefaults("city", c));
        dob.setText(SHPref.getDefaults("dofb", c));
        lastdonate.setText(SHPref.getDefaults("lastdonate", c));
        medicalprob.setText(SHPref.getDefaults("medical_problem", c));
        mob.setText(SHPref.getDefaults("contact", c));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Objects.equals(SHPref.getDefaults("gender", c), "Male")) {
                img.setImageResource(R.drawable.male);
            }

            if (Objects.equals(SHPref.getDefaults("gender", c), "Female")) {
                img.setImageResource(R.drawable.female);
            }
        } else {
            img.setImageResource(R.drawable.friend);
        }

        alertBuilder.setView(mview);
        final AlertDialog dialog = alertBuilder.create();
        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}

