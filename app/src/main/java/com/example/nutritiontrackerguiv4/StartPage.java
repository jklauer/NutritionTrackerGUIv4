package com.example.nutritiontrackerguiv4;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.nutritiontrackerguiv4.database.Allergies;
import com.example.nutritiontrackerguiv4.database.Day;
import com.example.nutritiontrackerguiv4.database.Meal;
import com.example.nutritiontrackerguiv4.database.NutritionDatabase;
import com.example.nutritiontrackerguiv4.database.User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class StartPage extends AppCompatActivity {

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        /*
        testing the database

        loadInformation();
        handleContinueButton();
        NutritionDatabase db = NutritionDatabase.getDatabase(getApplicationContext());
        db.clearAllTables();
        Allergies newAllergies = new Allergies(2, true, false);
        db.getAllergiesDAO().insert(newAllergies);
        User newUser = new User(1, newAllergies.getAllergy_ID(), "test");
        db.getUserDAO().insert(newUser);

         */
    }

    public void loadInformation(){

        File fileStartPage = new File(getFilesDir(), "fileStartPage.txt");
        if(fileStartPage.exists()){
            try {
                BufferedReader br = new BufferedReader(new FileReader(fileStartPage));
                String line;
                line = br.readLine();
                ((EditText)findViewById(R.id.username_input_Start_Page)).setText(line);
                line = br.readLine();
                if(line.equals("T")){
                    ((CheckBox)findViewById(R.id.allergy_milk_start_page)).setChecked(true);
                }else{
                    ((CheckBox)findViewById(R.id.allergy_milk_start_page)).setChecked(false);
                }
                line = br.readLine();
                if(line.equals("T")){
                    ((CheckBox)findViewById(R.id.allergy_eggs_start_page)).setChecked(true);
                }else{
                    ((CheckBox)findViewById(R.id.allergy_eggs_start_page)).setChecked(false);
                }
                line = br.readLine();
                if(line.equals("T")){
                    ((CheckBox)findViewById(R.id.allergy_fish_start_page)).setChecked(true);
                }else{
                    ((CheckBox)findViewById(R.id.allergy_fish_start_page)).setChecked(false);
                }
                line = br.readLine();
                if(line.equals("T")){
                    ((CheckBox)findViewById(R.id.allergy_shellfish_start_page)).setChecked(true);
                }else{
                    ((CheckBox)findViewById(R.id.allergy_shellfish_start_page)).setChecked(false);
                }
                line = br.readLine();
                if(line.equals("T")){
                    ((CheckBox)findViewById(R.id.allergy_tree_nuts_start_page)).setChecked(true);
                }else{
                    ((CheckBox)findViewById(R.id.allergy_tree_nuts_start_page)).setChecked(false);
                }
                line = br.readLine();
                if(line.equals("T")){
                    ((CheckBox)findViewById(R.id.allergy_peanuts_start_page)).setChecked(true);
                }else{
                    ((CheckBox)findViewById(R.id.allergy_peanuts_start_page)).setChecked(false);
                }
                line = br.readLine();
                if(line.equals("T")){
                    ((CheckBox)findViewById(R.id.allergy_wheat_start_page)).setChecked(true);
                }else{
                    ((CheckBox)findViewById(R.id.allergy_wheat_start_page)).setChecked(false);
                }
                line = br.readLine();
                if(line.equals("T")){
                    ((CheckBox)findViewById(R.id.allergy_soybeans_start_page)).setChecked(true);
                }else{
                    ((CheckBox)findViewById(R.id.allergy_soybeans_start_page)).setChecked(false);
                }
                line = br.readLine();
                if(line != null && !line.isEmpty()){
                    ((EditText)findViewById(R.id.other_allergens_edit_text_start_page)).setText(line);
                }
                br.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void handleContinueButton(){
        Button ContinueButton = (Button) findViewById(R.id.continue_button);
        ContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File fileStartPage = new File(getFilesDir(), "fileStartPage.txt");
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(fileStartPage));
                    if( ((EditText)findViewById(R.id.username_input_Start_Page)).getText().toString().isEmpty()){
                        bw.write("Name");
                    }else{
                        bw.write(((EditText)findViewById(R.id.username_input_Start_Page)).getText().toString());
                    }
                    bw.newLine();
                    if( ((CheckBox)findViewById(R.id.allergy_milk_start_page)).isChecked()){
                        bw.write("T");
                    }else{
                        bw.write("F");
                    }
                    bw.newLine();
                    if( ((CheckBox)findViewById(R.id.allergy_eggs_start_page)).isChecked()){
                        bw.write("T");
                    }else{
                        bw.write("F");
                    }
                    bw.newLine();
                    if( ((CheckBox)findViewById(R.id.allergy_fish_start_page)).isChecked()){
                        bw.write("T");
                    }else{
                        bw.write("F");
                    }
                    bw.newLine();
                    if( ((CheckBox)findViewById(R.id.allergy_shellfish_start_page)).isChecked()){
                        bw.write("T");
                    }else{
                        bw.write("F");
                    }
                    bw.newLine();
                    if( ((CheckBox)findViewById(R.id.allergy_tree_nuts_start_page)).isChecked()){
                        bw.write("T");
                    }else{
                        bw.write("F");
                    }
                    bw.newLine();
                    if( ((CheckBox)findViewById(R.id.allergy_peanuts_start_page)).isChecked()){
                        bw.write("T");
                    }else{
                        bw.write("F");
                    }
                    bw.newLine();
                    if( ((CheckBox)findViewById(R.id.allergy_wheat_start_page)).isChecked()){
                        bw.write("T");
                    }else{
                        bw.write("F");
                    }
                    bw.newLine();
                    if( ((CheckBox)findViewById(R.id.allergy_soybeans_start_page)).isChecked()){
                        bw.write("T");
                    }else{
                        bw.write("F");
                    }
                    bw.newLine();
                    if( !(((EditText)findViewById(R.id.other_allergens_edit_text_start_page)).getText().toString().isEmpty()) ){
                        bw.write(((EditText)findViewById(R.id.other_allergens_edit_text_start_page)).getText().toString());
                    }else{
                        bw.write("");
                    }
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