package com.example.nutritiontrackerguiv4;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.nutritiontrackerguiv4.R;
import com.example.nutritiontrackerguiv4.database.Ingredient;
import com.example.nutritiontrackerguiv4.database.NutritionDatabase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class InputMealForm extends Activity {
    private String date;
    private String time;
    private String name;
    private String calories;
    private String vitaminA;
    private String vitaminC;
    private String ingr_id;

    private NutritionDatabase db;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_meal_form);

        db = NutritionDatabase.getDatabase(getApplicationContext());

        date = java.text.DateFormat.getDateInstance().format(Calendar.getInstance().getTime());
        time = getIntent().getStringExtra("ingr_time");
        name = getIntent().getStringExtra("ingr_name");
        calories = getIntent().getStringExtra("ingr_calories");
        vitaminA = getIntent().getStringExtra("ingr_vita");
        vitaminC = getIntent().getStringExtra("ingr_vitc");
        ingr_id = getIntent().getStringExtra("ingr_id");
        if(
                time!=null
                &&name!=null
                &&calories!=null
                &&vitaminA!=null
                &&vitaminC!=null
        ){
            System.out.println("Updating a meal...");

            ((EditText)findViewById(R.id.mealDate)).setText(date);
            ((EditText)findViewById(R.id.mealDate)).setEnabled(false);
            ((EditText)findViewById(R.id.mealTime)).setText(time);
            ((EditText)findViewById(R.id.mealName)).setText(name);
            ((EditText)findViewById(R.id.vitaminA)).setText(vitaminA);
            ((EditText)findViewById(R.id.vitaminC)).setText(vitaminC);
            ((EditText)findViewById(R.id.caloriesEntry)).setText(calories);

            update();
        }else{
            System.out.println("Adding a meal...");

            ((EditText)findViewById(R.id.mealDate)).setText(date);
            ((EditText)findViewById(R.id.mealDate)).setEnabled(false);
            ((EditText)findViewById(R.id.mealTime)).setText(
                    java.text.DateFormat.getTimeInstance().format(Calendar.getInstance().getTime()));
            ((EditText)findViewById(R.id.mealName)).setText("");
            ((EditText)findViewById(R.id.vitaminA)).setText("");
            ((EditText)findViewById(R.id.vitaminC)).setText("");
            ((EditText)findViewById(R.id.caloriesEntry)).setText("");

            add();
        }

    }

    public void update(){

        ((Button)findViewById(R.id.enterMeal)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    Ingredient update_ingr = db.getIngredientDAO().findAllInfoForIngredient(Long.parseLong(ingr_id)).get(0);
                    update_ingr.setCalories(Integer.parseInt(((EditText)findViewById(R.id.caloriesEntry)).getText().toString()));
                    update_ingr.setName(((EditText)findViewById(R.id.mealName)).getText().toString());
                    update_ingr.setTime(((EditText)findViewById(R.id.mealTime)).getText().toString());
                    update_ingr.setVitaminA(Integer.parseInt(((EditText)findViewById(R.id.vitaminA)).getText().toString()));
                    update_ingr.setVitaminC(Integer.parseInt(((EditText)findViewById(R.id.vitaminC)).getText().toString()));
                    db.getIngredientDAO().update(update_ingr);
                    Intent loadApp = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(loadApp);
                }catch(NumberFormatException e){
                    System.out.println("Invalid formatting...");
                }




            }
        });

    }

    public void add(){

        ((Button)findViewById(R.id.enterMeal)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Button clicked!");
                try{
                    Ingredient add_ingr = new Ingredient(
                            ((EditText)findViewById(R.id.mealName)).getText().toString(),
                            Integer.parseInt(
                                    ((EditText)findViewById(R.id.caloriesEntry)).getText().toString()),
                            Integer.parseInt(
                                    ((EditText)findViewById(R.id.vitaminA)).getText().toString()),
                            Integer.parseInt(
                                    ((EditText)findViewById(R.id.vitaminC)).getText().toString()),
                            ((EditText)findViewById(R.id.mealTime)).getText().toString());
                    db.getIngredientDAO().insert(add_ingr);
                    Intent loadApp = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(loadApp);
                }catch(NumberFormatException e){
                    System.out.println("Invalid formatting...");
                }



            }
        });

    }
}