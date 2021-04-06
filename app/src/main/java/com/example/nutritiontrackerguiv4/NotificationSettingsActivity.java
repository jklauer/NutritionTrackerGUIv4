package com.example.nutritiontrackerguiv4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.nutritiontrackerguiv4.Notifications.CreateNotification;

import java.util.Calendar;

public class NotificationSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_settings);


        Button notificationEntry = (Button)findViewById(R.id.enterNotification);
        notificationEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int hours = Integer.parseInt(((EditText)findViewById(R.id.editHour)).getText().toString()); // user input for hours
                int minutes = Integer.parseInt(((EditText)findViewById(R.id.editMinute)).getText().toString()); // user input for minutes

                Calendar timeOff9 = Calendar.getInstance();
                int curHour = timeOff9.get(Calendar.HOUR_OF_DAY); // current hour of the day
                int curMin = timeOff9.get(Calendar.MINUTE); // current minute in the hour
                int milliInDay = 86400000;

                if (hours < curHour) {
                    int hoursApart = 24 - (curHour - hours); // number of hours to wait
                    int milliApart = (hoursApart * 3600); // number of milliseconds to wait (for hours)

                    if (minutes < curMin) {
                        int minApart = curMin - minutes;
                        int minMilliApart = (minApart * 60) * 1000;
                        int finalMilli = milliApart - minMilliApart;
                        CreateNotification.CreateNotificationWithDelay(getApplicationContext(), "the title", "the body1", finalMilli);

                    } else if (minutes == curMin) {
                        CreateNotification.CreateNotificationWithDelay(getApplicationContext(), "the title", "the body2", milliApart);
                    } else  {
                        int minApart = minutes - curMin;
                        int minMilliApart = (minApart * 60) * 1000;
                        int finalMilli = milliApart + minMilliApart;
                        CreateNotification.CreateNotificationWithDelay(getApplicationContext(), "the title", "the body3", finalMilli);
                    }

                } else if (hours == curHour) {
                    if (minutes < curMin) {
                        int minApart = curMin - minutes; // minutes apart from actual time
                        int milliApart = (minApart * 60) * 1000; // milliseconds apart from actual time
                        int finalMilli = milliInDay - milliApart; // milliseconds to wait
                        CreateNotification.CreateNotificationWithDelay(getApplicationContext(), "the title", "the body4", finalMilli);

                    } else if (minutes == curMin) {
                        CreateNotification.CreateNotificationWithDelay(getApplicationContext(), "the title", "the body5", 86400000);
                    } else {
                        CreateNotification.CreateNotificationAtTime(getApplicationContext(), "Nutrition Tracker", "Custom Notification", hours,minutes,0);
                    }

                } else {
                    CreateNotification.CreateNotificationAtTime(getApplicationContext(), "Nutrition Tracker", "Custom Notification", hours,minutes,0);
                }


                Intent loadApp = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(loadApp);
            }
        });




    }
}