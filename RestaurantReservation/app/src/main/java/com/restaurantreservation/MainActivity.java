package com.restaurantreservation;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;

    static SmsMessageSender smsMessageSender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Request SMS permission IF not permitted
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    MY_PERMISSIONS_REQUEST_SEND_SMS);
        }

        smsMessageSender = new SmsMessageSender(getApplicationContext());
    }

    public  void onClickMenu(View view)
    {
        Intent intent;
        intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
    public  void onClickContact(View view)
    {
        Intent intent;
        intent = new Intent(this, ContactActivity.class);
        startActivity(intent);

    }

    public void onClickReservation(View view) {
        Intent intent;
        intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
