package com.example.user.hw4_2;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {

    static int id = 70000;

    public MyReceiver() {
    }

    @Override
    @SuppressLint("NewApi")
    public void onReceive(Context context, Intent intent) {
        String name = intent.getStringExtra("Name");

        Intent newIntent = new Intent();
        newIntent.setClass(context, MainActivity.class);
        newIntent.putExtra("Name",name);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, newIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentTitle("Hello");
        builder.setContentText(name);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentIntent(pendingIntent);
        builder.setTicker(name);
        builder.setWhen(System.currentTimeMillis());
        builder.setAutoCancel(true);
        Notification notify = builder.build();

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(id++, notify);
    }
}