package com.restaurantreservation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    // Shared Preferences
    private SharedPreferences userInfoPref;

    private static final String tables[] = { "tbl_account", "tbl_reservation", "tbl_authentication"};
    static DatabaseManager dbManager;
    // Table Creator String
    private static final String tableCreatorString[] =
            { "CREATE TABLE tbl_account (phoneNumber INTEGER PRIMARY KEY, password TEXT NOT NULL, firstName TEXT NOT NULL, lastName TEXT NOT NULL);",
                    "CREATE TABLE tbl_reservation (phoneNumber INTEGER, tableId INTEGER PRIMARY KEY, numberOfGuest INTEGER, reservationDate INTEGER,arrivalTime TEXT, notes TEXT, FOREIGN KEY(phoneNumber) REFERENCES tbl_account(phoneNumber));",
                    "CREATE TABLE tbl_authentication (phoneNumber INTEGER, code TEXT, expiryTime TEXT, FOREIGN KEY(phoneNumber) REFERENCES tbl_account(phoneNumber));"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userInfoPref = getSharedPreferences("userInfo", MODE_PRIVATE);

        dbManager = new DatabaseManager(this);

        //dbManager.deleteDatabase(this);

        dbManager.CreateDatabase(getApplicationContext());
        dbManager.InitDatabase(tables, tableCreatorString);
    }

    public void onClickLogin(View view) {
        String phoneNumber = ((EditText)findViewById(R.id.textLoginPhoneNumber)).getText().toString();
        String password = ((EditText)findViewById(R.id.textLoginPassword)).getText().toString();

        Intent intent = null;

        // Check Login status
        if(dbManager.getLoginStatus(this, "tbl_account", phoneNumber, password)){
            // Check if authentication is needed
            if(dbManager.requireAuthentication(phoneNumber)){
                sendCode(phoneNumber);
                intent = new Intent(this, AuthenticationActivity.class);
                // Store phone number in Shared Preference
                SharedPreferences.Editor editor = userInfoPref.edit();
                editor.putString("phoneNumber", phoneNumber);
                editor.apply();
            }
            else {
                intent = new Intent(this, ReservationActivity.class);
            }
            if(intent != null){
                startActivity(intent);
            }
        }
    }

    private void sendCode(String phoneNumber){
        MainActivity.smsMessageSender.SendMessage(phoneNumber,
                "Here is your code for Restaurant Reservation App: " + dbManager.getAuthenticationCode(phoneNumber) + ". This code will expiry in 5 minutes.");
    }

    public void onClickRegister(View view) {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }
}
