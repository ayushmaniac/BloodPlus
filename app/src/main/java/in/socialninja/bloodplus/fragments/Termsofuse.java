package in.socialninja.bloodplus.fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import in.socialninja.bloodplus.R;

/**
 * Created by Unique on 14-03-2018.
 */

public class Termsofuse extends AppCompatActivity {
    //View mview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terms_and_use);

    }

    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
