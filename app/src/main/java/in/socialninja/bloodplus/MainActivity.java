package in.socialninja.bloodplus;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Slide;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;

import java.net.InetAddress;

import in.socialninja.bloodplus.adapters.FrontFragmentAdapter;
import in.socialninja.bloodplus.adapters.NavigationAdapter;
import in.socialninja.bloodplus.fragments.AboutUs;
import in.socialninja.bloodplus.fragments.ContactUs;
import in.socialninja.bloodplus.fragments.ProfileActivity;
import in.socialninja.bloodplus.fragments.Termsofuse;
import in.socialninja.bloodplus.httphandler.SHPref;
import nl.psdcompany.duonavigationdrawer.views.DuoDrawerLayout;
import nl.psdcompany.duonavigationdrawer.widgets.DuoDrawerToggle;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // private ActionBarDrawerToggle actionBarDrawerToggle;
    // private DrawerLayout drawer;
    DuoDrawerLayout drawerLayout;
    NavigationView navigationView;
    NavigationAdapter navigationadapter;
    TextView username, usermail;
    TextView okbtn;
    int CALL_PERMISSION_CODE = 1;
    LinearLayout s_profile, s_doante, s_rate, s_about, s_contact, s_tos;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupWindowAnimations();
        //drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        // navigationView = (NavigationView) findViewById(R.id.nav_view1);

        drawerLayout = (DuoDrawerLayout) findViewById(R.id.drawer_layout);
        DuoDrawerToggle drawerToggle = new DuoDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
        s_about = (LinearLayout) findViewById(R.id.side_about);
        s_profile = (LinearLayout) findViewById(R.id.side_profile);
        s_doante = (LinearLayout) findViewById(R.id.side_donate);
        s_rate = (LinearLayout) findViewById(R.id.side_rateus);
        s_contact = (LinearLayout) findViewById(R.id.side_contact);
        s_tos = (LinearLayout) findViewById(R.id.side_toc);
        username = (TextView) findViewById(R.id.username);
        usermail = (TextView) findViewById(R.id.email);
        drawerLayout.closeDrawer();
        setToolbar();
        if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
        } else {
            requetstCallPermission();
        }
        s_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    Explode exp = new Explode();
                    getWindow().setExitTransition(exp);
                }
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        s_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AboutUs.class);
                startActivity(i);
            }
        });
        s_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getApplicationContext();
                Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                // To count with Play market backstack, After pressing back button,
                // to taken back to our application, we need to add following flags to intent.
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));

                }
            }
        });
        s_doante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        s_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ContactUs.class);
                startActivity(i);
            }
        });
        s_tos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Termsofuse.class);
                startActivity(i);
            }
        });


        invalidateOptionsMenu();

        username.setText(SHPref.getDefaults("name", getApplicationContext()));
        usermail.setText(SHPref.getDefaults("email", getApplicationContext()));

        ViewPager pager = (ViewPager) findViewById(R.id.container);
        FrontFragmentAdapter ffa = new FrontFragmentAdapter(getSupportFragmentManager(), 1);
        pager.setAdapter(ffa);
    }

   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    /*@Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }*/
    @Override
    public void onBackPressed() {
        FragmentManager fm = getFragmentManager();
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {

            drawerLayout.closeDrawer(Gravity.LEFT); //OPEN Nav Drawer!
        }

        if (fm.getBackStackEntryCount() > 0) {
            //Log.i("MainActivity", "popping backstack");
            fm.popBackStack();
        } else {
            super.onBackPressed();
        }

    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setTitle("");
        toolbar.findViewById(R.id.logout_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bu_id = SHPref.getDefaults("bu_id", getApplicationContext());
                logout_fun();

            }
        });

        toolbar.findViewById(R.id.navigation_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer();
            }
        });


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void logout_fun() {
        Toast.makeText(getApplicationContext(), "Thank you! now You are logged out", Toast.LENGTH_SHORT).show();
        SHPref.clearPref(getApplicationContext());
        Intent i = new Intent(MainActivity.this, LoginRegistration.class);
        startActivity(i);
        this.finish();
    }

    public void transact(Fragment fragment) {
        FragmentTransaction transf = getFragmentManager().beginTransaction();
        transf.replace(R.id.root_frame, fragment);
        transf.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transf.addToBackStack(null);
        transf.commit();
    }

    private void setupWindowAnimations() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide slide = new Slide();
            slide.setDuration(1000);
            getWindow().setExitTransition(slide);
        }
    }

    private void requetstCallPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, android.Manifest.permission.CALL_PHONE)) {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Permission Needed")
                    .setMessage("To make a call permission is requered")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.CALL_PHONE}, CALL_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.CALL_PHONE}, CALL_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permission, int[] grantResult) {
        if (requestCode == CALL_PERMISSION_CODE) {
            if (grantResult.length >= 0 && grantResult[0] == PackageManager.PERMISSION_GRANTED) {
                //Toast.makeText(,"dnjgkhgkj",Toast.LENGTH_LONG).show();
            } else {

            }
        }
    }

    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            //You can replace it with your name
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }
    }
}
