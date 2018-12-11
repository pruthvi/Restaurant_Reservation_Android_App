package com.restaurantreservation;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MakeRegistrationActivity extends AppCompatActivity {

    DatabaseManager dbManager;
    EditText[] registrationForm;
    private SharedPreferences userInfoPref;
    String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makeregistration);

        dbManager = LoginActivity.dbManager;
        userInfoPref = getSharedPreferences("userInfo", MODE_PRIVATE);

        phoneNumber = userInfoPref.getString("phoneNumber", "");
        registrationForm = new EditText[]{
                findViewById(R.id.txtPeople),
                findViewById(R.id.txtDate),
                findViewById(R.id.txtTime),
                findViewById(R.id.txtSpecialNote)
        };
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
        String date = registrationForm[1].getText().toString();
        String time = registrationForm[2].getText().toString();

        if(dbManager.ReservationExists("tbl_reservation", date, time)){
            Toast.makeText(this,"Table is already reserved for this time", Toast.LENGTH_SHORT).show();
        }
        else {

            String noOfPeople = registrationForm[0].getText().toString();
            String registrationDate = registrationForm[1].getText().toString();
            String arrivalTime = registrationForm[2].getText().toString();
            String specialNote = registrationForm[3].getText().toString();

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
}
