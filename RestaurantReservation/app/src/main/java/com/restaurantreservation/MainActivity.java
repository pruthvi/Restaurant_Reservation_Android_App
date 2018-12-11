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
import android.widget.ToggleButton;

import com.restaurantreservation.WebServices.MySimpleService;

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

    public void onResume()
    {
        super.onResume();

    }

    public void onToggleClicked(View view) {
        // Is the toggle on?
        boolean on = ((ToggleButton) view).isChecked();

        if (on) {
            // Enable vibrate
            //startService(view);
            startService(new Intent(getBaseContext(), MySimpleService.class));
        } else {
            // Disable vibrate
            //stopService(view);
            stopService(new Intent(getBaseContext(), MySimpleService.class));
        }
    }
}
