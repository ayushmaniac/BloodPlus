package in.socialninja.bloodplus.adapters;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.Objects;

/**
 * Created by Unique on 06-03-2018.
 */

public class DateDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    EditText txtDate;

    public DateDialog(View view) {
        txtDate = (EditText) view;
    }

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(Objects.requireNonNull(getActivity()), this, year, month, day);
        dialog.getDatePicker().setMaxDate(c.getTimeInMillis());
        return dialog;

    }

    public void onDateSet(DatePicker view, int yy, int mm, int dd) {
        populateSetDate(yy, mm + 1, dd);
    }

    @SuppressLint("SetTextI18n")
    public void populateSetDate(int year, int month, int day) {
        txtDate.setText(year + "-" + month + "-" + day);
    }
}
