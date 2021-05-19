package com.example.nutritiontrackerguiv4;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

import com.example.nutritiontrackerguiv4.Notifications.ReminderBroadcast;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.nutritiontrackerguiv4.database.NutritionDatabase;
import com.example.nutritiontrackerguiv4.database.Notifications;
import com.example.nutritiontrackerguiv4.NotificationSettingsActivity;

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

            /*NutritionDatabase db = NutritionDatabase.getDatabase(getApplicationContext());

            Notifications not = db.getNotificationsDAO().getAllNotifications().get(0);

            Calendar timeOff9 = Calendar.getInstance();
            int curHour = timeOff9.get(Calendar.HOUR_OF_DAY); // current hour of the day
            int curMin = timeOff9.get(Calendar.MINUTE);
            int curSec = timeOff9.get(Calendar.SECOND);

            if(curHour == 11 && curMin == 30 && curSec == 0) {

                try {
                    Integer h1 = not.getHours_one();
                    Integer h2 = not.getHours_two();
                    Integer h3 = not.getHours_three();
                    Integer m1 = not.getMinutes_one();
                    Integer m2 = not.getMinutes_two();
                    Integer m3 = not.getMinutes_three();
                    String H1 = h1.toString();
                    String H2 = h2.toString();
                    String H3 = h3.toString();
                    String M1 = m1.toString();
                    String M2 = m2.toString();
                    String M3 = m3.toString();
                    not.setRepeat(true);


                    System.out.println(H1);
                    System.out.println(H2);
                    System.out.println(H3);
                    System.out.println(M1);
                    System.out.println(M2);
                    System.out.println(M3);

                    Intent loadNotification = new Intent(getApplicationContext(), NotificationSettingsActivity.class);
                    startActivity(loadNotification);


                if (!(H1.equals("0") && M1.equals("0"))) {
                    res.makeNotification(H1, M1);
                }
                if (!(H2.equals("0") && M2.equals("0"))) {
                    res.makeNotification(H2, M2);
                }
                if (!(H3.equals("0") && M3.equals("0"))) {
                    res.makeNotification(H3, M3);
                }


                } catch (Exception e) {

                }
            }*/
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