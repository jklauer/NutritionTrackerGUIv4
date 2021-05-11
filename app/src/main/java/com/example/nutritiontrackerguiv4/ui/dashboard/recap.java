package com.example.nutritiontrackerguiv4.ui.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nutritiontrackerguiv4.R;
import com.example.nutritiontrackerguiv4.database.NutritionDatabase;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import ir.drax.constraintaccordionlist.AccordionItem;
import ir.drax.constraintaccordionlist.AccordionList;

public class recap extends AppCompatActivity {
    NutritionDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recap);
        this.db = NutritionDatabase.getDatabase(getApplicationContext());

        LocalDate today = LocalDate.now();
        ArrayList<Date> lastWeek = lastWeek(today);
        AccordionList L = this.findViewById(R.id.accordion);

        for(int i=0; i<lastWeek.size(); i++)
        {
            String day = lastWeek.get(i).toString();
            String formatDay = java.text.DateFormat.getDateInstance().format(lastWeek.get(i));
            System.out.println(day);
            int numCal = getCalorieHelper(formatDay);
            int numVitA = getVitAHelper(formatDay);
            int numVitC = getVitCHelper(formatDay);
            //L.addView(textView1);
            //L.addView(textView2);
            String innerText = numCal+" cal, "+numVitA+" vit. A, "+ numVitC+" vit. C";
            L.push(new AccordionItem(formatDay, innerText));

        }

        L.build();
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

    private int getVitAHelper(String day)
    {
        int vitA = 0;
        try
        {
            vitA = db.getIngredientDAO().findVitAOnDay(day).get(0);
        }catch (NullPointerException e)
        {
            System.out.println("No vit A that day");
        }
        return vitA;
    }

    private int getVitCHelper(String day)
    {
        int vitC = 0;
        try
        {
            vitC = db.getIngredientDAO().findVitCOnDay(day).get(0);
        }catch (NullPointerException e)
        {
            System.out.println("No vit C that day");
        }
        return vitC;
    }


    private ArrayList<Date> lastWeek(LocalDate date)
    {
        final int dayOfWeek = date.getDayOfWeek().getValue();

        Calendar cal = Calendar.getInstance();
        ArrayList<Date> week = new ArrayList<Date>();
        week.add(Sunday());
        week.add(Monday());
        week.add(Tuesday());
        week.add(Wednesday());
        week.add(Thursday());
        week.add(Friday());
        week.add(Saturday());

        return week;
    }

    public static Date getDayFromLastWeek(int n)
    {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
        c.add(Calendar.DATE, -i - n);
        Date start = c.getTime();
        c.add(Calendar.DATE, 6);
        return start;
    }

    public static Date Sunday()
    {
        return getDayFromLastWeek(7);
    }

    public static Date Monday() {
        return getDayFromLastWeek(6);
    }

    public static Date Tuesday() {
        return getDayFromLastWeek(5);
    }

    public static Date Wednesday() {
        return getDayFromLastWeek(4);
    }

    public static Date Thursday() {
        return getDayFromLastWeek(3);
    }

    public static Date Friday() {
        return getDayFromLastWeek(2);
    }

    public static Date Saturday() {
        return getDayFromLastWeek(1);
    }
}