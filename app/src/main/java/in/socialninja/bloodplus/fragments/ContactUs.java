package in.socialninja.bloodplus.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import in.socialninja.bloodplus.R;
import in.socialninja.bloodplus.httphandler.HttpClientActivity;
import in.socialninja.bloodplus.httphandler.InternetConnection;
import in.socialninja.bloodplus.httphandler.SHPref;

/**
 * Created by Unique on 12-03-2018.
 */

public class ContactUs extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);


        TextView btn_submit = (TextView) findViewById(R.id.contactus_submit);
        final EditText query = (EditText) findViewById(R.id.contactus_query);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.drop_request, R.layout.spinner_item);
        final Spinner topic = (Spinner) findViewById(R.id.contactus_topic);
        topic.setAdapter(adapter);
        //CircleImageView img = (CircleImageView) findViewById(R.id.prof_img);


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value1 = query.getText().toString();
                String value2 = topic.getSelectedItem().toString();
                String value0 = SHPref.getDefaults("id", getApplicationContext());
                contactus_query(value0, value1, value2);

            }
        });

    }

    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    public void contactus_query(String u_id, String user, String pass) {
        RequestParams params = new RequestParams();
        params.put("u_id", u_id);
        params.put("e_type", user);
        params.put("e_message", pass);
        if (InternetConnection.checkConnection(getApplicationContext())) {
            HttpClientActivity.post("enquiry/", params, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    try {

                        Toast.makeText(getApplicationContext(), "Your Query is submitted", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    super.onFailure(statusCode, headers, responseString, throwable);
                    if (statusCode <= 400) {
                        Toast.makeText(getApplicationContext(), "Bad Request or Server not available", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Something went wrong please check your connection", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "Please check your connection", Toast.LENGTH_SHORT).show();
        }
    }
}

