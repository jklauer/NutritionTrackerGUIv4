package com.example.nutritiontrackerguiv4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.nutritiontrackerguiv4.database.NutritionDatabase;
import com.example.nutritiontrackerguiv4.database.Notifications;
import com.example.nutritiontrackerguiv4.Notifications.CreateNotification;

import java.util.Calendar;
import java.util.Random;

public class NotificationSettingsActivity extends AppCompatActivity {

    private NutritionDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_settings);


        Button notificationEntry = (Button)findViewById(R.id.enterNotification);
        notificationEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {


                    String hour = ((EditText)findViewById(R.id.editHour)).getText().toString(); // user input for hours
                    String minute = ((EditText)findViewById(R.id.editMinute)).getText().toString(); // user input for minutes
                    makeNotification(hour,minute);

                } catch (Throwable e) {
                    CreateNotification.CreateNotificationWithDelay(getApplicationContext(), "Nutrition Tracker", "Invalid Notification: Please re-enter your notification", 2000);
                }

                try {
                    String hour = ((EditText)findViewById(R.id.editHour2)).getText().toString(); // user input for hours
                    String minute = ((EditText)findViewById(R.id.editMinute2)).getText().toString(); // user input for minutes
                    makeNotification(hour,minute);

                } catch (Throwable e) {

                }

                try {
                    String hour = ((EditText)findViewById(R.id.editHour3)).getText().toString(); // user input for hours
                    String minute = ((EditText)findViewById(R.id.editMinute3)).getText().toString(); // user input for minutes
                    makeNotification(hour,minute);

                } catch (Throwable e) {

                }




                Intent loadApp = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(loadApp);
            }
        });




    }

    public void makeNotification(String hourIn, String minuteIn){

        int hours = Integer.parseInt(hourIn); // user input for hours
        int minutes = Integer.parseInt(minuteIn); // user input for minutes

        if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59) {
            CreateNotification.CreateNotificationWithDelay(getApplicationContext(), "Nutrition Tracker", "Invalid Notification: Please re-enter your notification", 2000);
        } else {
            Calendar timeOff9 = Calendar.getInstance();
            int curHour = timeOff9.get(Calendar.HOUR_OF_DAY); // current hour of the day
            int curMin = timeOff9.get(Calendar.MINUTE); // current minute in the hour
            int milliInDay = 86400000;

            if (hours < curHour) {
                int hoursApart = 24 - (curHour - hours); // number of hours to wait
                int milliApart = (hoursApart * 3600) * 1000; // number of milliseconds to wait (for hours)

                if (minutes < curMin) {
                    int minApart = curMin - minutes; // how far apart are the minutes
                    int minMilliApart = (minApart * 60) * 1000; // find the minutes apart in milliseconds
                    int finalMilli = milliApart - minMilliApart; // find how long to delay
                    CreateNotification.CreateNotificationWithDelay(getApplicationContext(), "Nutrition Tracker", "Reminder to enter Meal Data!", finalMilli);

                } else if (minutes == curMin) {
                    CreateNotification.CreateNotificationWithDelay(getApplicationContext(), "Nutrition Tracker", "Reminder to enter Meal Data!", milliApart);
                } else  {
                    int minApart = minutes - curMin;
                    int minMilliApart = (minApart * 60) * 1000;
                    int finalMilli = milliApart + minMilliApart;
                    CreateNotification.CreateNotificationWithDelay(getApplicationContext(), "Nutrition Tracker", "Reminder to enter Meal Data!", finalMilli);
                }

            } else if (hours == curHour) {
                if (minutes < curMin) {
                    int minApart = curMin - minutes; // minutes apart from actual time
                    int milliApart = (minApart * 60) * 1000; // milliseconds apart from actual time
                    int finalMilli = milliInDay - milliApart; // milliseconds to wait
                    CreateNotification.CreateNotificationWithDelay(getApplicationContext(), "Nutrition Tracker", "Reminder to enter Meal Data!", finalMilli);

                } else if (minutes == curMin) {
                    CreateNotification.CreateNotificationWithDelay(getApplicationContext(), "Nutrition Tracker", "Reminder to enter Meal Data!", 86400000);
                } else {
                    CreateNotification.CreateNotificationAtTime(getApplicationContext(), "Nutrition Tracker", "Reminder to enter Meal Data!", hours,minutes,0);
                }

            } else {
                CreateNotification.CreateNotificationAtTime(getApplicationContext(), "Nutrition Tracker", "Reminder to enter Meal Data!", hours,minutes,0);
            }
        }



    }
}