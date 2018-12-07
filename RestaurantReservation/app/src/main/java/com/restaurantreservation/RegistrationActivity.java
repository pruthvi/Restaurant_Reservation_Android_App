package com.restaurantreservation;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    DatabaseManager dbManager;

    private EditText[] requiredFields;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        dbManager = LoginActivity.dbManager;

        requiredFields = new EditText[]{
                findViewById(R.id.textPhoneNumber),
                findViewById(R.id.textPassword),
                findViewById(R.id.textFirstName),
                findViewById(R.id.textLastName) };
    }

    public void onClickRegisterAccount(View view) {
        // Loop through each required field, request focus if no filled
        for (int i = 0; i < requiredFields.length; i++) {
            if(requiredFields[i].getText().length() == 0){
                Toast.makeText(this, requiredFields[i].getHint() +" is required.", Toast.LENGTH_SHORT).show();
                requiredFields[i].requestFocus();
                return;
            }
        }

        // Check if user exist
        String phoneNumber = requiredFields[0].getText().toString();

        if(dbManager.userExists("tbl_account", phoneNumber)){
            Toast.makeText(this, "You already registered.", Toast.LENGTH_SHORT).show();
        }
        else {
            String password = requiredFields[1].getText().toString();
            String firstName = requiredFields[2].getText().toString();
            String lastName = requiredFields[3].getText().toString();

            String[] personalInfoFields = { "phoneNumber", "password", "firstName", "lastName" };
            String[] personalInfoRecords = { phoneNumber, password, firstName, lastName };

            long id = dbManager.addRecord(new ContentValues(), "tbl_account", personalInfoFields, personalInfoRecords);

            if(id > -1 ){
                Toast.makeText(this, phoneNumber + " registered.", Toast.LENGTH_SHORT).show();

                // If successfully registered set the authentication expiry time
                String[] authenticationFields = { "phoneNumber", "code", "expiryTime" };
                String[] authenticationRecords = { phoneNumber, "", "" };

                // Add the time with code to the database
                dbManager.addRecord(new ContentValues(),"tbl_authentication", authenticationFields, authenticationRecords);
                // Generate Code for the first time
                dbManager.generateNewAuthenticationCode(phoneNumber, 4, 5);

                // Back to the previous page
                finish();
            }
            else {
                Toast.makeText(this, "Unable to register.", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
