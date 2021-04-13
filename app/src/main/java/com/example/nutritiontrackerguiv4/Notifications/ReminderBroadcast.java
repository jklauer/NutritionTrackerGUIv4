package com.example.nutritiontrackerguiv4.Notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.nutritiontrackerguiv4.GlobalVars;
import com.example.nutritiontrackerguiv4.R;

import java.util.Random;

public class ReminderBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //System.out.println("Here");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context.getApplicationContext(), Integer.toString(GlobalVars.cID))
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setContentTitle(CreateNotification.title)
                .setContentText(CreateNotification.body)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context.getApplicationContext());

        notificationManager.notify(GlobalVars.cID, builder.build());
    }
}
