package com.example.nutritiontrackerguiv4;

import android.app.Activity;
import android.app.DatePickerDialog;
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

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_meal_form);

        String date = java.text.DateFormat.getDateInstance().format(Calendar.getInstance().getTime());
        String time = java.text.DateFormat.getTimeInstance().format(Calendar.getInstance().getTime());
        String name = "";
        String calories = "";
        String vitaminA = "";
        String vitaminC = "";

        ArrayList<String> data = getIntent().getStringArrayListExtra("data");
        if(data != null){
            date = data.get(0);
            time = data.get(1);
            name = data.get(2);
            calories = data.get(3);
            vitaminA = data.get(4);
            vitaminC = data.get(5);
        }

        ((EditText)findViewById(R.id.mealDate)).setText(date);
        ((EditText)findViewById(R.id.mealDate)).setEnabled(false);
        ((EditText)findViewById(R.id.mealTime)).setText(time);
        if(data != null){
            ((EditText)findViewById(R.id.mealName)).setText(name);
            ((EditText)findViewById(R.id.caloriesEntry)).setText(calories);
            ((EditText)findViewById(R.id.vitaminA)).setText(vitaminA);
            ((EditText)findViewById(R.id.vitaminC)).setText(vitaminC);
        }

        dataEntryButton(data);


    }

    public void dataEntryButton(ArrayList<String> data){

        if(data == null){
            dataEntry();
        }else{
            try {
                dataUpdate(data);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void dataUpdate(ArrayList<String> data) throws IOException {
        Button EntryButton = (Button) findViewById(R.id.enterMeal);
        EntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if(data == null){
                        dataEntry();
                    }else{
                        try {
                            dataUpdate(data);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    String date = data.get(0);
                    String time = data.get(1);
                    String name = data.get(2);
                    String calories = data.get(3);
                    String vitaminA = data.get(4);
                    String vitaminC = data.get(5);

                    ArrayList<String> firstLines = new ArrayList<String>();
                    BufferedReader br = new BufferedReader(new FileReader(new File(getFilesDir(), "userMealData.txt")));
                    String line = br.readLine();
                    while(line != null){
                        firstLines.add(line);
                        if(firstLines.size() >= 3 && firstLines.get(firstLines.size()-3).equals(date)){
                            if(firstLines.get(firstLines.size()-2).equals(time)){
                                if(firstLines.get(firstLines.size()-1).equals(name)){
                                    firstLines.set(firstLines.size()-3, ((EditText)findViewById(R.id.mealDate)).getText().toString());
                                    firstLines.set(firstLines.size()-2, ((EditText)findViewById(R.id.mealTime)).getText().toString());
                                    firstLines.set(firstLines.size()-1, ((EditText)findViewById(R.id.mealName)).getText().toString());
                                    firstLines.add(((EditText)findViewById(R.id.caloriesEntry)).getText().toString());
                                    firstLines.add(((EditText)findViewById(R.id.vitaminA)).getText().toString());
                                    firstLines.add(((EditText)findViewById(R.id.vitaminC)).getText().toString());
                                    break;
                                }
                            }
                        }
                        line = br.readLine();
                    }
                    br.readLine();
                    br.readLine();
                    br.readLine();
                    line = br.readLine();
                    while(line != null){
                        firstLines.add(line);
                        line = br.readLine();
                    }
                    br.close();

                    BufferedWriter bw = new BufferedWriter(new FileWriter(new File(getFilesDir(), "userMealData.txt")));
                    for(String s:firstLines){
                        System.out.println(s);
                        bw.write(s);
                        bw.newLine();
                    }
                    bw.close();
                    Intent loadApp = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(loadApp);
                }catch(IOException e){

                }

            }

        });

    }

    public void dataEntry(){
        Button EntryButton = (Button) findViewById(R.id.enterMeal);
        EntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File mealsPage = new File(getFilesDir(), "userMealData.txt");
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(mealsPage, true));
                    if( ((EditText)findViewById(R.id.mealDate)).getText().toString().isEmpty()){
                        bw.write("Meal Date");
                    }else{
                        bw.write(((EditText)findViewById(R.id.mealDate)).getText().toString());
                    }
                    bw.newLine();
                    //in this line
                    if( ((EditText)findViewById(R.id.mealTime)).getText().toString().isEmpty()){
                        bw.write("Meal Time");
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
                        bw.write("Meal Calories");
                    }else{
                        bw.write(((EditText)findViewById(R.id.caloriesEntry)).getText().toString());
                    }
                    bw.newLine();
                    if( ((EditText)findViewById(R.id.vitaminA)).getText().toString().isEmpty()){
                        bw.write("Meal Vitamin A");
                    }else{
                        bw.write(((EditText)findViewById(R.id.vitaminA)).getText().toString());
                    }
                    bw.newLine();
                    if( ((EditText)findViewById(R.id.vitaminC)).getText().toString().isEmpty()){
                        bw.write("Meal Vitamin C");
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