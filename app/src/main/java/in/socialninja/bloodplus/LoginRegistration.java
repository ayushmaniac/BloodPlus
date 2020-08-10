package in.socialninja.bloodplus;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import in.socialninja.bloodplus.fragments.SignInUp_root;
import in.socialninja.bloodplus.httphandler.SHPref;

/**
 * Created by Unique on 06-03-2018.
 */

public class LoginRegistration extends AppCompatActivity {
    Thread splashThread;

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_registration);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Explode explode = new Explode();
            getWindow().setExitTransition(explode);
        }
        String uname = SHPref.getDefaults("email", getApplicationContext());
        String upass = SHPref.getDefaults("password", getApplicationContext());
        if (uname != null && upass != null) {
            ImageView iv = (ImageView) findViewById(R.id.logo);
            iv.setVisibility(View.VISIBLE);
            StartAnimation();
        } else {
            FrameLayout fr = (FrameLayout) findViewById(R.id.lr_container);
            fr.setVisibility(View.VISIBLE);
            SignInUp_root rootFrame = new SignInUp_root();
            getSupportFragmentManager().beginTransaction().add(R.id.lr_container, rootFrame).commit();

        }
    }

    private void StartAnimation() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        LinearLayout l = (LinearLayout) findViewById(R.id.activity_splash_screen);
        l.clearAnimation();
        l.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.logo);
        iv.clearAnimation();
        iv.startAnimation(anim);


        splashThread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;

                    while (waited < 3500) {
                        sleep(100);
                        waited += 100;
                    }
                    Intent intent = new Intent(LoginRegistration.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    LoginRegistration.this.finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        splashThread.start();
    }

}
