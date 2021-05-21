package com.example.nutritiontrackerguiv4.Notifications;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.nutritiontrackerguiv4.GlobalVars;
import com.example.nutritiontrackerguiv4.MainActivity;
import com.example.nutritiontrackerguiv4.NotificationSettingsActivity;
import com.example.nutritiontrackerguiv4.R;
import com.example.nutritiontrackerguiv4.ui.meals.MealsFragment;

import java.util.Random;

public class ReminderBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //System.out.println("Here");
        Intent loadNotification = new Intent(context.getApplicationContext(), MealsFragment.class);
        loadNotification.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pI = PendingIntent.getActivity(context.getApplicationContext(), 0, loadNotification,0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context.getApplicationContext(), Integer.toString(GlobalVars.cID))
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setContentTitle(CreateNotification.title)
                .setContentText(CreateNotification.body)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pI)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context.getApplicationContext());

        notificationManager.notify(GlobalVars.cID, builder.build());
    }
}
