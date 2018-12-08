package com.restaurantreservation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ReservationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
    }

    public void MakeRegistration(View view){
        startActivity(new Intent(this,MakeRegistrationActivity.class));
    }


    public void MyRegistration(View view){
        startActivity(new Intent(this, ViewRegistration.class));
    }
}
