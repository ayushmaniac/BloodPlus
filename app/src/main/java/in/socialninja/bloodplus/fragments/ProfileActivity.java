package in.socialninja.bloodplus.fragments;

import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import in.socialninja.bloodplus.R;
import in.socialninja.bloodplus.httphandler.SHPref;

/**
 * Created by Unique on 12-03-2018.
 */

public class ProfileActivity extends AppCompatActivity {
    //View mview;
    String bloodgroup;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_popup);
        setupWindowAnimations();
        TextView name = (TextView) findViewById(R.id.prof_name);
        TextView email = (TextView) findViewById(R.id.prof_email);
        TextView blood = (TextView) findViewById(R.id.prof_blood);
        TextView city = (TextView) findViewById(R.id.prof_city);
        TextView dob = (TextView) findViewById(R.id.prof_dob);
        TextView lastdonate = (TextView) findViewById(R.id.prof_lastdonate);
        TextView medicalprob = (TextView) findViewById(R.id.prof_mprob);
        TextView mob = (TextView) findViewById(R.id.prof_mobnum);
        CircleImageView img = (CircleImageView) findViewById(R.id.prof_img);
        TextView ok = (TextView) findViewById(R.id.prof_okbtn);


        if (Objects.equals(SHPref.getDefaults("bloodgroup", getApplicationContext()), "A[plus]")) {
            bloodgroup = "A+";
        }
        if (Objects.equals(SHPref.getDefaults("bloodgroup", getApplicationContext()), "B[plus]")) {
            bloodgroup = "B+";
        }
        if (Objects.equals(SHPref.getDefaults("bloodgroup", getApplicationContext()), "O[plus]")) {
            bloodgroup = "O+";
        }
        if (Objects.equals(SHPref.getDefaults("bloodgroup", getApplicationContext()), "AB[plus]")) {
            bloodgroup = "AB+";
        }
        if (Objects.equals(SHPref.getDefaults("bloodgroup", getApplicationContext()), "A-")) {
            bloodgroup = "A-";
        }
        if (Objects.equals(SHPref.getDefaults("bloodgroup", getApplicationContext()), "B-")) {
            bloodgroup = "B-";
        }
        if (Objects.equals(SHPref.getDefaults("bloodgroup", getApplicationContext()), "AB-")) {
            bloodgroup = "AB-";
        }
        if (Objects.equals(SHPref.getDefaults("bloodgroup", getApplicationContext()), "O-")) {
            bloodgroup = "O-";
        }

        name.setText(SHPref.getDefaults("name", getApplicationContext()));
        email.setText(SHPref.getDefaults("email", getApplicationContext()));
        blood.setText(bloodgroup);
        city.setText(SHPref.getDefaults("city", getApplicationContext()));
        dob.setText(SHPref.getDefaults("dofb", getApplicationContext()));
        lastdonate.setText(SHPref.getDefaults("lastdonate", getApplicationContext()));


        mob.setText(SHPref.getDefaults("contact", getApplicationContext()));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Objects.equals(SHPref.getDefaults("gender", getApplicationContext()), "Male")) {
                img.setImageResource(R.drawable.male);
            }
            if (Objects.equals(SHPref.getDefaults("gender", getApplicationContext()), "Female")) {
                img.setImageResource(R.drawable.female);
            }
            if (Objects.equals(SHPref.getDefaults("medical_problem", getApplicationContext()), "0")) {
                medicalprob.setText("No");
            }
            if (Objects.equals(SHPref.getDefaults("medical_problem", getApplicationContext()), "1")) {
                medicalprob.setText("Yes");
            }
        } else {
            medicalprob.setText(SHPref.getDefaults("medical_problem", getApplicationContext()));
            img.setImageResource(R.drawable.friend);
        }
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                ProfileActivity.this.finish();
            }
        });


    }

    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    private void setupWindowAnimations() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Fade fade = new Fade();
            fade.setDuration(1000);
            getWindow().setEnterTransition(fade);
        }
    }
}
