package com.restaurantreservation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Random;

public class ReservationActivity extends AppCompatActivity {

    private static int totalTable = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
    }

    public void MakeRegistration(View view){
        startActivity(new Intent(this,MakeRegistrationActivity.class));
    }


    public void MyRegistration(View view){
        startActivity(new Intent(this, ViewReservationActivity.class));
    }

    public static String GetFreeTable(){

        int count = 0;
        Random rng = new Random();
        // Run at least once
        do{
            int tableNumber = rng.nextInt(totalTable) + 1;

            if(LoginActivity.dbManager.TableAvaliable(tableNumber)){
                return String.valueOf(tableNumber);
            }
        }while(count++ < totalTable);

        return null;
    }
}
