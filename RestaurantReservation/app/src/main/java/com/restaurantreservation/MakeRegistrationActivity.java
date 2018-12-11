package com.restaurantreservation;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MakeRegistrationActivity extends AppCompatActivity {

    DatabaseManager dbManager;
    EditText[] registrationForm;
    private SharedPreferences userInfoPref;
    String phoneNumber;

    Calendar calendar = Calendar.getInstance();
    Button selectDateBtn;
    Button timePickerEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makeregistration);

        dbManager = LoginActivity.dbManager;
        userInfoPref = getSharedPreferences("userInfo", MODE_PRIVATE);

        phoneNumber = userInfoPref.getString("phoneNumber", "");
        registrationForm = new EditText[]{
                findViewById(R.id.txtPeople),

                findViewById(R.id.txtSpecialNote)
        };
        selectDateBtn = (Button) findViewById(R.id.book_selectDateBtn);
        timePickerEditText = (Button) findViewById(R.id.book_timePickerBtn);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(new Date());
        selectDateBtn.setText(currentDate);
        timePickerEditText.setText(Calendar.HOUR_OF_DAY + ":" + Calendar.MINUTE);
    }

    public void onClickMakeRegistration(View view) {
        // Loop through each required field, request focus if no filled
        for (int i = 0; i < registrationForm.length; i++) {
            if (registrationForm[i].getText().length() == 0) {
                Toast.makeText(this, registrationForm[i].getHint() + "is required.",
                        Toast.LENGTH_SHORT).show();
                registrationForm[i].requestFocus();
                return;
            }
        }

        // Check if Reservation already Exists
        String date = selectDateBtn.getText().toString();
        String time = timePickerEditText.getText().toString();

//        if(dbManager.ReservationExists("tbl_reservation", date, time)){
//            Toast.makeText(this,"Table is already reserved for this time", Toast.LENGTH_SHORT).show();
//        }
        if(false){
            Toast.makeText(this,"Table is already reserved for this time", Toast.LENGTH_SHORT).show();
        }
        else {

            String noOfPeople = registrationForm[0].getText().toString();
            String registrationDate = date;
            String arrivalTime = time;
            String specialNote = registrationForm[1].getText().toString();

            String[] personalInfoFields = {"phoneNumber", "numberOfGuest", "reservationDate", "arrivalTime", "notes"};
            String[] personalInfoRecords = {phoneNumber, noOfPeople, registrationDate, arrivalTime, specialNote};

            long id = dbManager.addRecord(new ContentValues(), "tbl_reservation", personalInfoFields, personalInfoRecords);

            if (id > -1) {
                Toast.makeText(this, phoneNumber + "reserved a table", Toast.LENGTH_LONG).show();

                // Reset all the values to null
//                registrationForm[0].setText("");
//                registrationForm[1].setText("");
//                registrationForm[2].setText("");
//                registrationForm[3].setText("");

                //startActivity(new Intent(this,ViewRegistration.class));

            } else {
                Toast.makeText(this, "Unable to register.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void CancelButton(View view){
        startActivity(new Intent(this,ReservationActivity.class));
    }

    public void book_openDatePicker(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(MakeRegistrationActivity.this, onSelectDateListener,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(Calendar.DAY_OF_MONTH);
        datePickerDialog.show();
    }

    public void book_openTimePicker(View view) {
        new TimePickerDialog(MakeRegistrationActivity.this, onTimeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
    }

    DatePickerDialog.OnDateSetListener onSelectDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            selectDateBtn.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
        }
    };
    TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            timePickerEditText.setText(hourOfDay + ":" + minute);
        }
    };
}
