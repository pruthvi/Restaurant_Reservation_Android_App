package com.restaurantreservation;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private Button btnDownload;
    String downloadURL="http://pruthv.com/android/menu.pdf";
    private int pgNo;
    WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);



        myWebView = (WebView) findViewById(R.id.menu_view);
        myWebView.getSettings().setBuiltInZoomControls(true);


        myWebView.loadUrl("http://pruthv.com/android/title.jpg");
        pgNo =1;

    }

    public void onClickNext(View view){
        if(pgNo == 1){
            myWebView.loadUrl("http://pruthv.com/android/menu.jpg");
            pgNo = 2;
        }
        else if (pgNo == 2){
            myWebView.loadUrl("http://pruthv.com/android/title.jpg");
            pgNo = 1;
        }

    }

    public void onDownload(View view){

    }



}
