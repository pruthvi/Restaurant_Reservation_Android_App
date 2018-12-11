package com.restaurantreservation;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ViewReservationActivity extends AppCompatActivity {

    DatabaseManager dbManager;
    private SharedPreferences userInfoPref;
    String phoneNumber;

    ArrayList<Reservation> reservations = new ArrayList<>();

    ListView lstReservations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewreservation);

        dbManager = LoginActivity.dbManager;
        userInfoPref = getSharedPreferences("userInfo", MODE_PRIVATE);

        phoneNumber = userInfoPref.getString("phoneNumber", "");

        lstReservations = findViewById(R.id.lstView);

        reservations = dbManager.GetReservationFromPhoneNumber(phoneNumber);

        ReservationAdapter adapter = new ReservationAdapter(this, reservations);

        lstReservations.setAdapter(adapter);
    }

}
