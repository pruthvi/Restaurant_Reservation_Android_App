package com.restaurantreservation;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public  void onClickMenu(View view)
    {
        Intent intent;
        intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
    public  void onClickContact(View view)
    {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:4377769707"));


        startActivity(callIntent);

    }

}
