package com.example.nutritiontrackerguiv4.ui.Tracking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
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
    LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);
        this.db = NutritionDatabase.getDatabase(getApplicationContext());

        fillChartWithCal();

        TextView calView = findViewById(R.id.averageCal);
        //TextView vitAView = findViewById(R.id.averageVitA);
        //TextView vitCView = findViewById(R.id.averageVitC);

        double [] averages = averages();
        double averageCal = averages[0];
        double averageVitA = averages[1];
        double averageVitC = averages[2];

        calView.setText("Average Calories: " + averageCal);
        //vitAView.setText("Average Vitamin A: " + averageVitA);
        //vitCView.setText("Average Vitamin C: " + averageVitC);

        Button calBtn = findViewById(R.id.calGraphBtn);
        calBtn.setOnClickListener(v -> {
            fillChartWithCal();
        });

        Button vitABtn = findViewById(R.id.vitAGraphBtn);
        vitABtn.setOnClickListener(v -> {
            fillChartWithVitA();
        });

        Button vitB12Btn = findViewById(R.id.vitB12GraphBtn);
        vitB12Btn.setOnClickListener(v -> {
            fillChartWithVitB12();
        });

        Button totalFatBtn = findViewById(R.id.totalFatGraphBtn);
        totalFatBtn.setOnClickListener(v -> {
            fillChartWithTotalFat();
        });

        Button cholesterolBtn = findViewById(R.id.CholesterolGraphBtn);
        cholesterolBtn.setOnClickListener(v -> {
            fillChartWithCholesterol();
        });

        Button sodiumBtn = findViewById(R.id.SodiumGraphBtn);
        sodiumBtn.setOnClickListener(v -> {
            fillChartWithSodium();
        });

        Button totalCarbsBtn = findViewById(R.id.totalCarbsGraphBtn);
        totalCarbsBtn.setOnClickListener(v -> {
            fillChartWithTotalCarbs();
        });

        Button fiberBtn = findViewById(R.id.fiberGraphBtn);
        fiberBtn.setOnClickListener(v -> {
            fillChartWithFiber();
        });

        Button sugarBtn = findViewById(R.id.sugarGraphBtn);
        sugarBtn.setOnClickListener(v -> {
            fillChartWithSugar();
        });

        Button proteinBtn = findViewById(R.id.proteinGraphBtn);
        proteinBtn.setOnClickListener(v -> {
            fillChartWithProtein();
        });

        Button transFatBtn = findViewById(R.id.transFatGraphBtn);
        transFatBtn.setOnClickListener(v -> {
            fillChartWithTransFat();
        });

        Button satFatBtn = findViewById(R.id.satFatGraphBtn);
        satFatBtn.setOnClickListener(v -> {
            fillChartWithSatFat();
        });

    }

    private void fillChartWithCal()
    {
        this.lineChart = findViewById(R.id.lineChart);
        getCalEntries();
        lineDataSet = new LineDataSet(lineEntries, "Calories for the last seven days");
        lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        lineDataSet.setValueTextColor(Color.BLACK);
        lineDataSet.setValueTextSize(18f);
    }

    private void fillChartWithVitA()
    {
        this.lineChart = findViewById(R.id.lineChart);
        getVitAEntries();
        lineDataSet = new LineDataSet(lineEntries, "Vitamin A for the last seven days");
        lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        lineDataSet.setValueTextColor(Color.BLACK);
        lineDataSet.setValueTextSize(18f);
    }

    private void fillChartWithVitB12()
    {
        this.lineChart = findViewById(R.id.lineChart);
        getVitCEntries();
        lineDataSet = new LineDataSet(lineEntries, "Vitamin B12 for the last seven days");
        lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        lineDataSet.setValueTextColor(Color.BLACK);
        lineDataSet.setValueTextSize(18f);
    }

    private void fillChartWithTotalFat()
    {
        this.lineChart = findViewById(R.id.lineChart);
        getTotalFatEntries();
        lineDataSet = new LineDataSet(lineEntries, "Total Fat for the last seven days");
        lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        lineDataSet.setValueTextColor(Color.BLACK);
        lineDataSet.setValueTextSize(18f);
    }

    private void fillChartWithSatFat()
    {
        this.lineChart = findViewById(R.id.lineChart);
        getSatFatEntries();
        lineDataSet = new LineDataSet(lineEntries, "Saturated Fat for the last seven days");
        lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        lineDataSet.setValueTextColor(Color.BLACK);
        lineDataSet.setValueTextSize(18f);
    }

    private void fillChartWithTransFat()
    {
        this.lineChart = findViewById(R.id.lineChart);
        getTransFatEntries();
        lineDataSet = new LineDataSet(lineEntries, "Trans Fat for the last seven days");
        lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        lineDataSet.setValueTextColor(Color.BLACK);
        lineDataSet.setValueTextSize(18f);
    }

    private void fillChartWithCholesterol()
    {
        this.lineChart = findViewById(R.id.lineChart);
        getCholesterolEntries();
        lineDataSet = new LineDataSet(lineEntries, "Cholesterol for the last seven days");
        lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        lineDataSet.setValueTextColor(Color.BLACK);
        lineDataSet.setValueTextSize(18f);
    }

    private void fillChartWithSodium()
    {
        this.lineChart = findViewById(R.id.lineChart);
        getSodiumEntries();
        lineDataSet = new LineDataSet(lineEntries, "Sodium for the last seven days");
        lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        lineDataSet.setValueTextColor(Color.BLACK);
        lineDataSet.setValueTextSize(18f);
    }

    private void fillChartWithTotalCarbs()
    {
        this.lineChart = findViewById(R.id.lineChart);
        getTotalCarbsEntries();
        lineDataSet = new LineDataSet(lineEntries, "Carbs for the last seven days");
        lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        lineDataSet.setValueTextColor(Color.BLACK);
        lineDataSet.setValueTextSize(18f);
    }

    private void fillChartWithFiber()
    {
        this.lineChart = findViewById(R.id.lineChart);
        getTotalFiberEntries();
        lineDataSet = new LineDataSet(lineEntries, "Fiber for the last seven days");
        lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        lineDataSet.setValueTextColor(Color.BLACK);
        lineDataSet.setValueTextSize(18f);
    }

    private void fillChartWithSugar()
    {
        this.lineChart = findViewById(R.id.lineChart);
        getTotalSugarEntries();
        lineDataSet = new LineDataSet(lineEntries, "Sugar for the last seven days");
        lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        lineDataSet.setValueTextColor(Color.BLACK);
        lineDataSet.setValueTextSize(18f);
    }

    private void fillChartWithProtein()
    {
        this.lineChart = findViewById(R.id.lineChart);
        getTotalProtienEntries();
        lineDataSet = new LineDataSet(lineEntries, "Protein for the last seven days");
        lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        lineDataSet.setValueTextColor(Color.BLACK);
        lineDataSet.setValueTextSize(18f);
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

    private void getCalEntries() {
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

    private int getVitAHelper(String day)
    {
        int vits = 0;
        try
        {
            vits = db.getIngredientDAO().findVitAOnDay(day).get(0);
        }catch (NullPointerException e)
        {
            System.out.println("No vitamin A that day");
        }
        return vits;
    }

    private void getVitAEntries() {
        ArrayList<String> lastSeven = lastSevenDays();
        lineEntries = new ArrayList<>();
        lineEntries.add(new Entry(1, getVitAHelper(lastSeven.get(0))));
        lineEntries.add(new Entry(2, getVitAHelper(lastSeven.get(1))));
        lineEntries.add(new Entry(3, getVitAHelper(lastSeven.get(2))));
        lineEntries.add(new Entry(4, getVitAHelper(lastSeven.get(3))));
        lineEntries.add(new Entry(5, getVitAHelper(lastSeven.get(4))));
        lineEntries.add(new Entry(6, getVitAHelper(lastSeven.get(5))));
        lineEntries.add(new Entry(7, getVitAHelper(lastSeven.get(6))));
    }

    private int getVitCHelper(String day)
    {
        int vits = 0;
        try
        {
            vits = db.getIngredientDAO().findVitCOnDay(day).get(0);
        }catch (NullPointerException e)
        {
            System.out.println("No vitamin C that day");
        }
        return vits;
    }

    private void getVitCEntries() {
        ArrayList<String> lastSeven = lastSevenDays();
        lineEntries = new ArrayList<>();
        lineEntries.add(new Entry(1, getVitCHelper(lastSeven.get(0))));
        lineEntries.add(new Entry(2, getVitCHelper(lastSeven.get(1))));
        lineEntries.add(new Entry(3, getVitCHelper(lastSeven.get(2))));
        lineEntries.add(new Entry(4, getVitCHelper(lastSeven.get(3))));
        lineEntries.add(new Entry(5, getVitCHelper(lastSeven.get(4))));
        lineEntries.add(new Entry(6, getVitCHelper(lastSeven.get(5))));
        lineEntries.add(new Entry(7, getVitCHelper(lastSeven.get(6))));
    }

    private int getTotalFatHelper(String day)
    {
        int vits = 0;
        try
        {
            vits = db.getIngredientDAO().findTotalFatOnDay(day).get(0);
        }catch (NullPointerException e)
        {
            System.out.println("No Total Fat that day");
        }
        return vits;
    }

    private void getTotalFatEntries() {
        ArrayList<String> lastSeven = lastSevenDays();
        lineEntries = new ArrayList<>();
        lineEntries.add(new Entry(1, getTotalFatHelper(lastSeven.get(0))));
        lineEntries.add(new Entry(2, getTotalFatHelper(lastSeven.get(1))));
        lineEntries.add(new Entry(3, getTotalFatHelper(lastSeven.get(2))));
        lineEntries.add(new Entry(4, getTotalFatHelper(lastSeven.get(3))));
        lineEntries.add(new Entry(5, getTotalFatHelper(lastSeven.get(4))));
        lineEntries.add(new Entry(6, getTotalFatHelper(lastSeven.get(5))));
        lineEntries.add(new Entry(7, getTotalFatHelper(lastSeven.get(6))));
    }

    private int getSatFatHelper(String day)
    {
        int vits = 0;
        try
        {
            vits = db.getIngredientDAO().findTotalFatOnDay(day).get(0);
        }catch (NullPointerException e)
        {
            System.out.println("No Saturated Fat that day");
        }
        return vits;
    }

    private void getSatFatEntries() {
        ArrayList<String> lastSeven = lastSevenDays();
        lineEntries = new ArrayList<>();
        lineEntries.add(new Entry(1, getSatFatHelper(lastSeven.get(0))));
        lineEntries.add(new Entry(2, getSatFatHelper(lastSeven.get(1))));
        lineEntries.add(new Entry(3, getSatFatHelper(lastSeven.get(2))));
        lineEntries.add(new Entry(4, getSatFatHelper(lastSeven.get(3))));
        lineEntries.add(new Entry(5, getSatFatHelper(lastSeven.get(4))));
        lineEntries.add(new Entry(6, getSatFatHelper(lastSeven.get(5))));
        lineEntries.add(new Entry(7, getSatFatHelper(lastSeven.get(6))));
    }

    private int getTransFatHelper(String day)
    {
        int vits = 0;
        try
        {
            vits = db.getIngredientDAO().findTransFatOnDay(day).get(0);
        }catch (NullPointerException e)
        {
            System.out.println("No Trans Fat that day");
        }
        return vits;
    }

    private void getTransFatEntries() {
        ArrayList<String> lastSeven = lastSevenDays();
        lineEntries = new ArrayList<>();
        lineEntries.add(new Entry(1, getTransFatHelper(lastSeven.get(0))));
        lineEntries.add(new Entry(2, getTransFatHelper(lastSeven.get(1))));
        lineEntries.add(new Entry(3, getTransFatHelper(lastSeven.get(2))));
        lineEntries.add(new Entry(4, getTransFatHelper(lastSeven.get(3))));
        lineEntries.add(new Entry(5, getTransFatHelper(lastSeven.get(4))));
        lineEntries.add(new Entry(6, getTransFatHelper(lastSeven.get(5))));
        lineEntries.add(new Entry(7, getTransFatHelper(lastSeven.get(6))));
    }

    private int getCholesterolHelper(String day)
    {
        int vits = 0;
        try
        {
            vits = db.getIngredientDAO().findCholesterolOnDay(day).get(0);
        }catch (NullPointerException e)
        {
            System.out.println("No cholesterol that day");
        }
        return vits;
    }

    private void getCholesterolEntries() {
        ArrayList<String> lastSeven = lastSevenDays();
        lineEntries = new ArrayList<>();
        lineEntries.add(new Entry(1, getCholesterolHelper(lastSeven.get(0))));
        lineEntries.add(new Entry(2, getCholesterolHelper(lastSeven.get(1))));
        lineEntries.add(new Entry(3, getCholesterolHelper(lastSeven.get(2))));
        lineEntries.add(new Entry(4, getCholesterolHelper(lastSeven.get(3))));
        lineEntries.add(new Entry(5, getCholesterolHelper(lastSeven.get(4))));
        lineEntries.add(new Entry(6, getCholesterolHelper(lastSeven.get(5))));
        lineEntries.add(new Entry(7, getCholesterolHelper(lastSeven.get(6))));
    }

    private int getSodiumHelper(String day)
    {
        int vits = 0;
        try
        {
            vits = db.getIngredientDAO().findSodiumOnDay(day).get(0);
        }catch (NullPointerException e)
        {
            System.out.println("No sodium that day");
        }
        return vits;
    }

    private void getSodiumEntries() {
        ArrayList<String> lastSeven = lastSevenDays();
        lineEntries = new ArrayList<>();
        lineEntries.add(new Entry(1, getSodiumHelper(lastSeven.get(0))));
        lineEntries.add(new Entry(2, getSodiumHelper(lastSeven.get(1))));
        lineEntries.add(new Entry(3, getSodiumHelper(lastSeven.get(2))));
        lineEntries.add(new Entry(4, getSodiumHelper(lastSeven.get(3))));
        lineEntries.add(new Entry(5, getSodiumHelper(lastSeven.get(4))));
        lineEntries.add(new Entry(6, getSodiumHelper(lastSeven.get(5))));
        lineEntries.add(new Entry(7, getSodiumHelper(lastSeven.get(6))));
    }

    private int getTotalCarbsHelper(String day)
    {
        int vits = 0;
        try
        {
            vits = db.getIngredientDAO().findTotalCarbsOnDay(day).get(0);
        }catch (NullPointerException e)
        {
            System.out.println("No total carbs that day");
        }
        return vits;
    }

    private void getTotalCarbsEntries() {
        ArrayList<String> lastSeven = lastSevenDays();
        lineEntries = new ArrayList<>();
        lineEntries.add(new Entry(1, getTotalCarbsHelper(lastSeven.get(0))));
        lineEntries.add(new Entry(2, getTotalCarbsHelper(lastSeven.get(1))));
        lineEntries.add(new Entry(3, getTotalCarbsHelper(lastSeven.get(2))));
        lineEntries.add(new Entry(4, getTotalCarbsHelper(lastSeven.get(3))));
        lineEntries.add(new Entry(5, getTotalCarbsHelper(lastSeven.get(4))));
        lineEntries.add(new Entry(6, getTotalCarbsHelper(lastSeven.get(5))));
        lineEntries.add(new Entry(7, getTotalCarbsHelper(lastSeven.get(6))));
    }

    private int getTotalFiberHelper(String day)
    {
        int vits = 0;
        try
        {
            vits = db.getIngredientDAO().findFiberOnDay(day).get(0);
        }catch (NullPointerException e)
        {
            System.out.println("No fiber that day");
        }
        return vits;
    }

    private void getTotalFiberEntries() {
        ArrayList<String> lastSeven = lastSevenDays();
        lineEntries = new ArrayList<>();
        lineEntries.add(new Entry(1, getTotalFiberHelper(lastSeven.get(0))));
        lineEntries.add(new Entry(2, getTotalFiberHelper(lastSeven.get(1))));
        lineEntries.add(new Entry(3, getTotalFiberHelper(lastSeven.get(2))));
        lineEntries.add(new Entry(4, getTotalFiberHelper(lastSeven.get(3))));
        lineEntries.add(new Entry(5, getTotalFiberHelper(lastSeven.get(4))));
        lineEntries.add(new Entry(6, getTotalFiberHelper(lastSeven.get(5))));
        lineEntries.add(new Entry(7, getTotalFiberHelper(lastSeven.get(6))));
    }

    private int getTotalSugarHelper(String day)
    {
        int vits = 0;
        try
        {
            vits = db.getIngredientDAO().findTotalSugarOnDay(day).get(0);
        }catch (NullPointerException e)
        {
            System.out.println("No sugar that day");
        }
        return vits;
    }

    private void getTotalSugarEntries() {
        ArrayList<String> lastSeven = lastSevenDays();
        lineEntries = new ArrayList<>();
        lineEntries.add(new Entry(1, getTotalSugarHelper(lastSeven.get(0))));
        lineEntries.add(new Entry(2, getTotalSugarHelper(lastSeven.get(1))));
        lineEntries.add(new Entry(3, getTotalSugarHelper(lastSeven.get(2))));
        lineEntries.add(new Entry(4, getTotalSugarHelper(lastSeven.get(3))));
        lineEntries.add(new Entry(5, getTotalSugarHelper(lastSeven.get(4))));
        lineEntries.add(new Entry(6, getTotalSugarHelper(lastSeven.get(5))));
        lineEntries.add(new Entry(7, getTotalSugarHelper(lastSeven.get(6))));
    }

    private int getTotalProteinHelper(String day)
    {
        int vits = 0;
        try
        {
            vits = db.getIngredientDAO().findTotalSugarOnDay(day).get(0);
        }catch (NullPointerException e)
        {
            System.out.println("No protein that day");
        }
        return vits;
    }

    private void getTotalProtienEntries() {
        ArrayList<String> lastSeven = lastSevenDays();
        lineEntries = new ArrayList<>();
        lineEntries.add(new Entry(1, getTotalProteinHelper(lastSeven.get(0))));
        lineEntries.add(new Entry(2, getTotalProteinHelper(lastSeven.get(1))));
        lineEntries.add(new Entry(3, getTotalProteinHelper(lastSeven.get(2))));
        lineEntries.add(new Entry(4, getTotalProteinHelper(lastSeven.get(3))));
        lineEntries.add(new Entry(5, getTotalProteinHelper(lastSeven.get(4))));
        lineEntries.add(new Entry(6, getTotalProteinHelper(lastSeven.get(5))));
        lineEntries.add(new Entry(7, getTotalProteinHelper(lastSeven.get(6))));
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