package in.socialninja.bloodplus.adapters;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

import in.socialninja.bloodplus.R;
import in.socialninja.bloodplus.modal.SearchContactModal;

/**
 * Created by Unique on 10-03-2018.
 */

public class SearchContactAdapter extends RecyclerView.Adapter<SearchContactAdapter.MyViewHolder> {


    public Context mContext;
    public SearchContactModal[] nav_3_modalClasses;
    String blood;
    private Activity activity;

    public SearchContactAdapter(Activity activity, Context mContext, SearchContactModal[] navmodalClasses) {
        this.activity = activity;
        this.mContext = mContext;
        this.nav_3_modalClasses = navmodalClasses;
    }

    @Override
    public SearchContactAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contactuser_list, parent, false);
        SearchContactAdapter.MyViewHolder vh = new SearchContactAdapter.MyViewHolder(itemView);
        return vh;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onBindViewHolder(SearchContactAdapter.MyViewHolder holder, int position) {

        holder.txtname.setText(nav_3_modalClasses[position].getName().trim());
        String city = "City : " + nav_3_modalClasses[position].getCity();
        if (Objects.equals(nav_3_modalClasses[position].getBloodgroup(), "A[plus]")) {
            blood = "A+";
        }
        if (Objects.equals(nav_3_modalClasses[position].getBloodgroup(), "B[plus]")) {
            blood = "B+";
        }
        if (Objects.equals(nav_3_modalClasses[position].getBloodgroup(), "AB[plus]")) {
            blood = "AB+";
        }
        if (Objects.equals(nav_3_modalClasses[position].getBloodgroup(), "O[plus]")) {
            blood = "O+";
        }
        if (Objects.equals(nav_3_modalClasses[position].getBloodgroup(), "B-")) {
            blood = "" + nav_3_modalClasses[position].getBloodgroup();
        }
        if (Objects.equals(nav_3_modalClasses[position].getBloodgroup(), "A-")) {
            blood = "" + nav_3_modalClasses[position].getBloodgroup();
        }
        if (Objects.equals(nav_3_modalClasses[position].getBloodgroup(), "AB-")) {
            blood = "" + nav_3_modalClasses[position].getBloodgroup();
        }
        if (Objects.equals(nav_3_modalClasses[position].getBloodgroup(), "O-")) {
            blood = "" + nav_3_modalClasses[position].getBloodgroup();
        }
        String email = "Gender : " + nav_3_modalClasses[position].getGender();
        holder.txtcity.setText(city);
        holder.bloodgroup.setText(blood);
        holder.emailadd.setText(email);
        if (Objects.equals(nav_3_modalClasses[position].getGender(), "Male")) {
            holder.img.setImageResource(R.drawable.male);
        }
        if (Objects.equals(nav_3_modalClasses[position].getGender(), "Female")) {
            holder.img.setImageResource(R.drawable.female);
        }
        //.setImageResource(nav_3_modalClasses[position].getImage());
        final String mobNum = nav_3_modalClasses[position].getContact();
        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                //callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_NO_USER_ACTION);
                callIntent.setData(Uri.parse("tel:" + mobNum));

                if (ActivityCompat.checkSelfPermission(mContext,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(mContext, "Can not Make call Application dont have calling permission please check your setting", Toast.LENGTH_LONG).show();
                } else {
                    mContext.startActivity(callIntent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return nav_3_modalClasses.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView txtname, txtcity, bloodgroup, emailadd, call;
        public ImageView img, map;

        public MyViewHolder(View itemView) {
            super(itemView);

            txtname = (TextView) itemView.findViewById(R.id.txtuser);
            txtcity = (TextView) itemView.findViewById(R.id.txtcity);
            bloodgroup = (TextView) itemView.findViewById(R.id.txtBloodgoroup);
            emailadd = (TextView) itemView.findViewById(R.id.txtGender);
            img = (ImageView) itemView.findViewById(R.id.img_hos);
            call = (TextView) itemView.findViewById(R.id.call_btn);
//            map = (ImageView)itemView.findViewById(R.id.map_btn);
//            call = (ImageView)itemView.findViewById(R.id.call_btn);

        }
    }


}
