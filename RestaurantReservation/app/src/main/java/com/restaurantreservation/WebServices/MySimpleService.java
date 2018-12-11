package com.restaurantreservation.WebServices;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.provider.Settings;
import android.widget.Toast;

import com.restaurantreservation.R;

public class MySimpleService extends Service{
    private MediaPlayer player;
    public static final String INFO_INTENT = "com.net.learn2develop.Services.INFO_UPDATE";

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //getting systems default ringtone
        // TODO change the default ringtone to something else

        player = MediaPlayer.create(this,
                Settings.System.DEFAULT_RINGTONE_URI);

        player = MediaPlayer.create(this, R.raw.music);



        //setting loop play to true
        //this will make the ringtone continuously playing
        player.setLooping(true);

        //staring the player
        player.start();

        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        player.stop();
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }

}