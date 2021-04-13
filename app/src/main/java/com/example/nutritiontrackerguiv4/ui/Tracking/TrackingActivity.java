package com.example.nutritiontrackerguiv4.ui.Tracking;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;

import com.example.nutritiontrackerguiv4.R;
import com.example.nutritiontrackerguiv4.database.NutritionDatabase;

public class TrackingActivity extends AppCompatActivity {

    NutritionDatabase db;
    ArrayList lineEntries;
    LineData lineData;
    LineDataSet lineDataSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);
        this.db = NutritionDatabase.getDatabase(getApplicationContext());

        LineChart lineChart = findViewById(R.id.lineChart);
        getEntries();
        lineDataSet = new LineDataSet(lineEntries, "Calories for the last seven days");
        lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        lineDataSet.setValueTextColor(Color.BLACK);
        lineDataSet.setValueTextSize(18f);

        TextView calView = findViewById(R.id.averageCal);
        TextView vitAView = findViewById(R.id.averageVitA);
        TextView vitCView = findViewById(R.id.averageVitC);

        double [] averages = averages();
        double averageCal = averages[0];
        double averageVitA = averages[1];
        double averageVitC = averages[2];

        calView.setText("Average Calories: " + averageCal);
        vitAView.setText("Average Vitamin A: " + averageVitA);
        vitCView.setText("Average Vitamin C: " + averageVitC);
    }

    private int getCalorieHelper(String day)
    {
        int cals = 0;
        try
        {
            cals = db.getIngredientDAO().findCaloriesOnDay(day).get(0);
        }catch (NullPointerException e)
        {
            System.out.println("No calories that day");
        }
        return cals;
    }

    private void getEntries() {
        ArrayList<String> lastSeven = lastSevenDays();
        lineEntries = new ArrayList<>();
        lineEntries.add(new Entry(1, getCalorieHelper(lastSeven.get(0))));
        lineEntries.add(new Entry(2, getCalorieHelper(lastSeven.get(1))));
        lineEntries.add(new Entry(3, getCalorieHelper(lastSeven.get(2))));
        lineEntries.add(new Entry(4, getCalorieHelper(lastSeven.get(3))));
        lineEntries.add(new Entry(5, getCalorieHelper(lastSeven.get(4))));
        lineEntries.add(new Entry(6, getCalorieHelper(lastSeven.get(5))));
        lineEntries.add(new Entry(7, getCalorieHelper(lastSeven.get(6))));
    }

    //Returns the average calories, vitamin A, and vitamin C, in that order
    private double[] averages()
    {
        ArrayList<String> week = lastSevenDays();
        double Calsum = 0.0;
        double vitASum = 0.0;
        double vitCSum = 0.0;
        for (String i: week) {
            int calOnDay = 0;
            int VitAOnDay = 0;
            int VitCOnDay = 0;
            try {
                calOnDay = db.getIngredientDAO().findCaloriesOnDay(i).get(0);
            }catch (NullPointerException e)
            {System.out.println("Null value for calories");}
            finally
            {
                System.out.println("calOnDay: "+calOnDay);
                Calsum = Calsum + calOnDay;
            }

            try{VitAOnDay = db.getIngredientDAO().findVitAOnDay(i).get(0);
            }catch (NullPointerException e)
            {System.out.println("Null value for Vitamin A");}
            finally
            {
                System.out.println("VitAOnDay: "+VitAOnDay);
                vitASum = vitASum + VitAOnDay;
            }

            try{VitCOnDay = db.getIngredientDAO().findVitCOnDay(i).get(0);
            }catch (NullPointerException e)
            {System.out.println("Null value for Vitamin C");}
            finally
            {
                System.out.println("VitCOnDay: "+VitCOnDay);
                vitCSum = vitCSum + VitCOnDay;
            }
        }

        return new double[]{Calsum / 7, vitASum / 7, vitCSum / 7};
    }

    private ArrayList<String> lastSevenDays()
    {
        Calendar cal = Calendar.getInstance();
        String today = java.text.DateFormat.getDateInstance().format(cal.getTime());
        ArrayList<String> week = new ArrayList<>(7);
        week.add(today);
        for (int i = -1; i >= -6; i--)
        {
            cal.add(Calendar.DATE, i);
            String day = java.text.DateFormat.getDateInstance().format(cal.getTime());
            week.add(day);
            cal = Calendar.getInstance();
        }
        System.out.println(week.toString());
        return week;
    }
}