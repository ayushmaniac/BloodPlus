package in.socialninja.bloodplus.fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import in.socialninja.bloodplus.R;

/**
 * Created by Unique on 15-03-2018.
 */

public class AboutUs extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

    }

    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
