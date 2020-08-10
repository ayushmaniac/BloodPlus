package in.socialninja.bloodplus.fragments;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import in.socialninja.bloodplus.R;
import in.socialninja.bloodplus.adapters.NavigationAdapter;
import in.socialninja.bloodplus.httphandler.HttpClientActivity;
import in.socialninja.bloodplus.httphandler.InternetConnection;
import in.socialninja.bloodplus.modal.NavModalClass;

/**
 * Created by Unique on 09-03-2018.
 */

public class NearestHospital extends Fragment {
    View view;
    RecyclerView list;
    NavigationAdapter navigationadapter;
    NavModalClass nav_3_modalClasses[];
    ProgressDialog mProgress;

    List<NavModalClass> list_nav = new ArrayList<>();

    public static Bitmap getBitmapFromURL(String imgUrl) {
        try {
            URL url = new URL(imgUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_nearesthospital, container, false);
        list = (RecyclerView) view.findViewById(R.id.nearest_recycle);

        getHospital();
        //=

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

    public void getHospital() {
        RequestParams params = new RequestParams();
        if (InternetConnection.checkConnection(getContext())) {
            HttpClientActivity.get("/hospital", params, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    try {
                        JSONArray contacts = response.getJSONArray("response");
                        for (int i = 0; i < contacts.length(); i++) {
                            JSONObject c = contacts.getJSONObject(i);

                            String h_id = c.getString("h_id");
                            String h_name = c.getString("h_name");
                            String h_city = c.getString("h_city");
                            String h_image = c.getString("h_image");
                            String h_gmap = c.getString("h_gmap");
                            String h_contact = c.getString("h_contact");

                            NavModalClass nav = new NavModalClass(h_image, h_name, h_city, h_gmap, h_id, h_contact);
                            list_nav.add(nav);
                        }
                        nav_3_modalClasses = list_nav.toArray(new NavModalClass[list_nav.size()]);
                        navigationadapter = new NavigationAdapter(getActivity(), getContext(), nav_3_modalClasses);
                        list.setLayoutManager(new LinearLayoutManager(getActivity()));
                        list.setAdapter(navigationadapter);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    super.onFailure(statusCode, headers, responseString, throwable);
                    if (statusCode <= 400) {
                        Toast.makeText(getContext(), "Bad Request or Server not available", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Something went wrong please check your connection", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(getContext(), "Please check your connection", Toast.LENGTH_SHORT).show();
        }
    }
}
