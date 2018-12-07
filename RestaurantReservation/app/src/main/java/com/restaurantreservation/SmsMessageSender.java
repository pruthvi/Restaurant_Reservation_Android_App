package com.restaurantreservation;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;

public class SmsMessageSender {

    String SENT = "SMS_SENT";
    String DELIVERED = "SMS_DELIVERED";
    PendingIntent sentPI, deliveredPI;

    public SmsMessageSender(Context context){
        sentPI = PendingIntent.getBroadcast(context, 0,
                new Intent(SENT), 0);
        deliveredPI = PendingIntent.getBroadcast(context, 0,
                new Intent(DELIVERED), 0);
    }

    public void SendMessage(String phoneNumber, String message){
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);
    }
}
