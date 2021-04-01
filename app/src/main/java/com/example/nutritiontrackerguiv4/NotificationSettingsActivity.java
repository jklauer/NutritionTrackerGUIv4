package com.example.nutritiontrackerguiv4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.nutritiontrackerguiv4.Notifications.CreateNotification;

public class NotificationSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_settings);


        Button notificationEntry = (Button)findViewById(R.id.enterNotification);
        notificationEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int hours = Integer.parseInt(((EditText)findViewById(R.id.editHour)).getText().toString());
                int minutes = Integer.parseInt(((EditText)findViewById(R.id.editMinute)).getText().toString());

                CreateNotification.CreateNotificationAtTime(getApplicationContext(), "the title", "the body", hours,minutes,0);

                Intent loadApp = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(loadApp);
            }
        });




    }
}