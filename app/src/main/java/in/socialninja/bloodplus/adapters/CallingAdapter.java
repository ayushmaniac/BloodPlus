package in.socialninja.bloodplus.adapters;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

public class CallingAdapter {
    public void makeCall(Activity activity, Context context, String mobileNum) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        //callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_NO_USER_ACTION);
        callIntent.setData(Uri.parse("tel:" + mobileNum));

        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(context, "Can not Make call Application dont have calling permission please check your setting", Toast.LENGTH_LONG).show();
        } else {
            context.startActivity(callIntent);
        }


    }

}
