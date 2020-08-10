package in.socialninja.bloodplus.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import in.socialninja.bloodplus.R;
import in.socialninja.bloodplus.modal.NavModalClass;

/**
 * Created by Unique on 06-03-2018.
 */

public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.MyViewHolder> {


    public Context mContext;
    public Activity activity;
    public NavModalClass[] nav_3_modalClasses;
    String url;

    public NavigationAdapter(Activity activity, Context mContext, NavModalClass[] navmodalClasses) {
        this.activity = activity;
        this.mContext = mContext;
        this.nav_3_modalClasses = navmodalClasses;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_nav, parent, false);
        MyViewHolder vh = new MyViewHolder(itemView);
        return vh;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.txtname.setText(nav_3_modalClasses[position].getName());
        holder.txtcity.setText(nav_3_modalClasses[position].getCity());
        holder.txthosType.setText(nav_3_modalClasses[position].getImage());
        //holder.txtcount.setText(nav_3_modalClasses[position].getCount());
        //Bitmap img1 = getImageBitmap();
        final String mobNum = nav_3_modalClasses[position].getContact();
        url = nav_3_modalClasses[position].getMapaddress();
        if (!url.startsWith("http://") && !url.startsWith("https://"))
            url = "http://" + url;

        Glide.with(mContext).load(R.drawable.hospital_list)
                .thumbnail(0.5f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.img);
        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallingAdapter c = new CallingAdapter();
                c.makeCall(activity, mContext, mobNum);
            }
        });
        holder.map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                mContext.startActivity(browserIntent);
            }
        });
        //.setImageResource(nav_3_modalClasses[position].getImage());
    }

    @Override
    public int getItemCount() {
        return nav_3_modalClasses.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView txtname, txtcity, txthosType;
        public ImageView img, map, call;

        public MyViewHolder(View itemView) {
            super(itemView);

            txtname = (TextView) itemView.findViewById(R.id.txthospital);
            txtcity = (TextView) itemView.findViewById(R.id.txtcity);
            txthosType = (TextView) itemView.findViewById(R.id.txthospitaltype);
            img = (ImageView) itemView.findViewById(R.id.img_hos);
            map = (ImageView) itemView.findViewById(R.id.map_btn);
            call = (ImageView) itemView.findViewById(R.id.call_btn);

        }
    }


}
