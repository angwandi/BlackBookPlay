package e.demad.mydialog;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    int mHour, mMinute, mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button alertbtn = findViewById(R.id.alertbtn);
        alertbtn.setOnClickListener(this);
        Button progressbtn = findViewById(R.id.progressbtn);
        progressbtn.setOnClickListener(this);
        Button custombtn = findViewById(R.id.custombtn);
        custombtn.setOnClickListener(this);
        Button datepicker = findViewById(R.id.datePickerbtn);
        datepicker.setOnClickListener(this);
        Button timepicker = findViewById(R.id.timePickerbtn);
        timepicker.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == findViewById(R.id.alertbtn)) {
            //get the builder to create the AlertDialog
            AlertDialog.Builder demoBuilder = new AlertDialog.Builder(this);
            //Set the properties of the AlertDialog
            demoBuilder.setMessage("Do you want to exit the application?");
            demoBuilder.setCancelable(false);
            demoBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    MainActivity.this.finish();
                }
            });
            demoBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            //create the alert dialog
            AlertDialog alert = demoBuilder.create();
            //show the AlertDialog
            alert.show();
        }
        //Progress Dialog
        if (view == findViewById(R.id.progressbtn)) {
            final ProgressDialog progressDialog;
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.THEME_TRADITIONAL);
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.incrementProgressBy(50);
            //noinspection deprecation
            progressDialog.setButton("Stop progress", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    progressDialog.dismiss();
                }
            });
            progressDialog.show();
        }
        //custom dialog
        if (view == findViewById(R.id.custombtn)) {
            Dialog dialog = new Dialog(MainActivity.this);
            dialog.setContentView(R.layout.custom_dialog);
            dialog.setTitle("Custom Dialog");
            TextView text = dialog.findViewById(R.id.text);
            text.setText("Here is your custom dialog");
            ImageView image = dialog.findViewById(R.id.image);
            image.setImageResource(R.drawable.ic_assignment_returned_black_24dp);
            dialog.show();
        }
        //date picker dialog
        if (view == findViewById(R.id.datePickerbtn)) {
            final TextView mDateDisplay = findViewById(R.id.dateDisplay);
            final DatePickerDialog datePickerDialog;
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog.OnDateSetListener mDateListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    mDateDisplay.setText(new StringBuilder().append(mMonth + 1).append("-").append(mDay).append("-").append(mYear).append(" "));
                }
            };
            datePickerDialog = new DatePickerDialog(MainActivity.this, mDateListener, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        //time picker dialog
        if (view == findViewById(R.id.timePickerbtn)) {
            final TextView mTimeDisplay = findViewById(R.id.timeDisplay);
            final TimePickerDialog timePickerDialog;
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);
            TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                    mHour = hourOfDay;
                    mMinute = minute;
                    mTimeDisplay.setText(new StringBuffer().append(mHour).append(":").append(mMinute));
                }
            };
            timePickerDialog = new TimePickerDialog(MainActivity.this, onTimeSetListener, mHour, mMinute, false);
            timePickerDialog.show();

        }
    }
}
