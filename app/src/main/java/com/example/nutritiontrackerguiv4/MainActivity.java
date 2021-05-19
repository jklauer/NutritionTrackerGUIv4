package com.example.nutritiontrackerguiv4;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

import com.example.nutritiontrackerguiv4.Notifications.ReminderBroadcast;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.nutritiontrackerguiv4.database.NutritionDatabase;
import com.example.nutritiontrackerguiv4.database.Notifications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.io.File;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(handleStartPage()){
            System.out.println("Trying to load start page.");
            Intent loadStartPage = new Intent(getApplicationContext(), StartPage.class);
            startActivity(loadStartPage);
        }else{
            BottomNavigationView navView = findViewById(R.id.nav_view);
            // Passing each menu ID as a set of Ids because each
            // menu should be considered as top level destinations.
            AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.navigation_dashboard, R.id.navigation_meals, R.id.navigation_settings, R.id.navigation_recipes)
                    .build();
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
            NavigationUI.setupWithNavController(navView, navController);


        }

    }

    public boolean handleStartPage(){

        File fileStartPage = new File(getFilesDir(), "User_ID.txt");
        if(fileStartPage.exists()){
            return false;
        }else{
            return true;
        }

    }





}