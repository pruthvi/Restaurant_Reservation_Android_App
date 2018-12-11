package com.restaurantreservation;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ViewRegistration extends AppCompatActivity {

    DatabaseManager dbManager;
    private SharedPreferences userInfoPref;
    String phoneNumber;
    TextView displayText;

    String[] display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewregistration);

        dbManager = LoginActivity.dbManager;
        userInfoPref = getSharedPreferences("userInfo", MODE_PRIVATE);

        phoneNumber = userInfoPref.getString("phoneNumber", "");

        displayText = (TextView) findViewById(R.id.txtDisplayReservations);

        display = dbManager.DisplayReservationDetails("tbl_reservation", phoneNumber);

        StringBuilder builder = new StringBuilder();
        for(String details : display){
            builder.append(details + "\n");
        }
        displayText.setText(builder.toString());
    }

}
