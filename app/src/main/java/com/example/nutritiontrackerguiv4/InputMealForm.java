package com.example.nutritiontrackerguiv4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nutritiontrackerguiv4.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class InputMealForm extends Activity {

    TextView content;
    EditText fname, email, login, pass;
    String Name, Email, Login, Pass;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_meal_form);

        dataEntryButton();
    }

    public void dataEntryButton(){
        Button EntryButton = (Button) findViewById(R.id.enterMeal);
        EntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File mealsPage = new File(getFilesDir(), "userMealData.txt");
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(mealsPage));
                    if( ((EditText)findViewById(R.id.mealDate)).getText().toString().isEmpty()){
                        bw.write("Meal Name");
                    }else{
                        bw.write(((EditText)findViewById(R.id.mealDate)).getText().toString());
                    }
                    bw.newLine();
                    if( ((EditText)findViewById(R.id.mealTime)).getText().toString().isEmpty()){
                        bw.write("Meal Name");
                    }else{
                        bw.write(((EditText)findViewById(R.id.mealTime)).getText().toString());
                    }
                    bw.newLine();
                    if( ((EditText)findViewById(R.id.mealName)).getText().toString().isEmpty()){
                        bw.write("Meal Name");
                    }else{
                        bw.write(((EditText)findViewById(R.id.mealName)).getText().toString());
                    }
                    bw.newLine();
                    if( ((EditText)findViewById(R.id.caloriesEntry)).getText().toString().isEmpty()){
                        bw.write("Meal Name");
                    }else{
                        bw.write(((EditText)findViewById(R.id.caloriesEntry)).getText().toString());
                    }
                    bw.newLine();
                    if( ((EditText)findViewById(R.id.vitaminA)).getText().toString().isEmpty()){
                        bw.write("Meal Name");
                    }else{
                        bw.write(((EditText)findViewById(R.id.vitaminA)).getText().toString());
                    }
                    bw.newLine();
                    if( ((EditText)findViewById(R.id.vitaminC)).getText().toString().isEmpty()){
                        bw.write("Meal Name");
                    }else{
                        bw.write(((EditText)findViewById(R.id.vitaminC)).getText().toString());
                    }
                    bw.newLine();
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent loadApp = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(loadApp);
            }
        });
    }
}