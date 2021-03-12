package com.example.nutritiontrackerguiv4;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_dashboard, R.id.navigation_meals, R.id.navigation_settings)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        if(handleStartPage()){
            Intent loadStartPage = new Intent(getApplicationContext(), StartPage.class);
            startActivity(loadStartPage);
        }

    }

    public boolean handleStartPage(){

        File fileStartPage = new File(getFilesDir(), "fileStartPage.txt");
        if(fileStartPage.exists()){
            return false;
        }else{
            return true;
        }


    }

    /*Helper method extractNums reads the number from a line that contains other text.
     Argument: String s, the string that contains the number
     Retuens the number as an integer.
     */
    public int extractNums(String s) {
        s = s.replaceAll("[^\\d]", " ");
        s = s.trim();
        return Integer.parseInt(s);
    }

    //Prototype method for reading values from userMealData. Right now it only returns calories,
    //but as we have a clearer idea of what is all being stored, the array size can be increased
    //and the logic that extracts the numbers can be repeated.
    //Returns an array of numbers that will be output onto the dashboard.
    public int[] readMeals() {
        File mealsPage = new File(getFilesDir(), "userMealData.txt");
        int calorie_count = 0;
        int[] info = new int[1];
        try {
            BufferedReader br = new BufferedReader(new FileReader(mealsPage));
            String line = br.readLine();
            while (line != null){
                if (line.contains("Meal Calories")) {
                    calorie_count += extractNums(line);
                    info[0] = calorie_count;
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return info;
    }



}