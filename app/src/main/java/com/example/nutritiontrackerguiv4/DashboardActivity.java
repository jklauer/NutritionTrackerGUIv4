package com.example.nutritiontrackerguiv4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nutritiontrackerguiv4.ui.dashboard.DashboardFragment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class DashboardActivity extends AppCompatActivity {

    File mealsPage;

    public class Tuple {
        public String name = "";
        public int value = 0;
        public Tuple(String name, int value) {
            this.name = name;
            this.value = value;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_dashboard);

        LinkedList<Tuple> arr = new LinkedList<Tuple>();

        //test items
        arr.add(new Tuple("Calories", 1980));
        arr.add(new Tuple("Vitamin A", 456));
        arr.add(new Tuple("Protein", 154));

        LinearLayout l = (LinearLayout) findViewById(R.id.dashboardLinearLayout);

        for(Tuple i : arr) {
            String str = i.name + ": " + i.value;
            TextView text = new TextView(this);
            text.setText(str);
            text.setGravity(Gravity.CENTER_HORIZONTAL);
            text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24f);
            text.setTextColor(Color.BLACK);
            l.addView(text);
        }
    }

    public  int extractNums(String s) {
        s = s.replaceAll("[^\\d]", " ");
        s = s.trim();
        return Integer.parseInt(s);
    }

    public  int[] readMeals(Context ctx) {
        int calorie_count = 0;
        int[] info = new int[1];
        mealsPage = new File(getFilesDir(), "userMealData.txt");

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