package com.example.nutritiontrackerguiv4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

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
import java.security.acl.Owner;
import java.time.Period;
import java.util.Date;
import java.util.List;

public class StartPage extends AppCompatActivity {

    private boolean user_exists = false;
    private long user_id;
    private long allergies_id;
    NutritionDatabase db;

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        db = NutritionDatabase.getDatabase(getApplicationContext());

        File user_id_file = new File(getFilesDir(), "User_ID.txt");
        if(user_id_file.exists()){
            user_exists = true;
        }

        //If user exists, load the user id and allergies id of that user.
        if(user_exists){
            try {
                BufferedReader br = new BufferedReader(new FileReader(user_id_file));
                this.user_id = Long.parseLong(br.readLine());
                this.allergies_id = db.getAllergiesDAO().findAllInfoForAllergies(user_id).get(0).getAllergy_ID();

                System.out.println("User loaded... user_id = "+user_id);
                System.out.println("Allergies loaded... allergies_id = "+allergies_id);
                loadInformation();
                handleContinueButton();
                br.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else{
            Button ContinueButton = (Button) findViewById(R.id.continue_button);
            ContinueButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    File fileStartPage = new File(getFilesDir(), "User_ID.txt");
                    try {
                        BufferedWriter bw = new BufferedWriter(new FileWriter(fileStartPage));
                        NutritionDatabase db = NutritionDatabase.getDatabase(getApplicationContext());

                        boolean tree_nuts = ((CheckBox)findViewById(R.id.allergy_tree_nuts_start_page)).isSelected();
                        boolean fish = ((CheckBox)findViewById(R.id.allergy_fish_start_page)).isSelected();
                        Allergies new_allergies = new Allergies(
                                tree_nuts,
                                fish
                        );

                        db.getAllergiesDAO().insert(new_allergies);
                        allergies_id = db.getAllergiesDAO().getAllAllergies().get(0).getAllergy_ID();
                        User new_user = new User(allergies_id, ((EditText)findViewById(R.id.username_input_Start_Page)).getText().toString());
                        db.getUserDAO().insert(new_user);
                        user_id = db.getUserDAO().getAllUsers().get(0).getUser_ID();

                        bw.write(Long.toString(user_id));

                        bw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Intent loadApp = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(loadApp);
                }
            });
        }


        /*
        NutritionDatabase db = NutritionDatabase.getDatabase(getApplicationContext());
        db.clearAllTables();
        Allergies newAllergies = new Allergies(2, true, false);
        db.getAllergiesDAO().insert(newAllergies);
        User newUser = new User(newAllergies.getAllergy_ID(), "tesfewfewt");
        db.getUserDAO().insert(newUser);

         */


    }

    public void loadInformation(){

        Allergies load_allergies = db.getAllergiesDAO().findAllInfoForAllergies(allergies_id).get(0);
        boolean tree_nuts = load_allergies.getNuts();
        boolean fish = load_allergies.getSeafood();
        User load_user = db.getUserDAO().findAllInfoForUser(user_id).get(0);
        String user_name = load_user.getName();


        ((CheckBox)findViewById(R.id.allergy_tree_nuts_start_page)).setChecked(tree_nuts);
        ((CheckBox)findViewById(R.id.allergy_fish_start_page)).setChecked(fish);
        ((EditText)findViewById(R.id.username_input_Start_Page)).setText(user_name);
    }

    public void handleContinueButton(){
        Button ContinueButton = (Button) findViewById(R.id.continue_button);
        ContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File fileStartPage = new File(getFilesDir(), "fileStartPage.txt");
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(fileStartPage));
                    NutritionDatabase db = NutritionDatabase.getDatabase(getApplicationContext());

                    boolean tree_nuts = ((CheckBox)findViewById(R.id.allergy_tree_nuts_start_page)).isChecked();
                    boolean fish = ((CheckBox)findViewById(R.id.allergy_fish_start_page)).isChecked();

                    Allergies update_allergies = new Allergies(
                            tree_nuts,
                            fish
                    );
                    update_allergies.setAllergy_ID(allergies_id);

                    User update_user = new User(allergies_id, ((EditText)findViewById(R.id.username_input_Start_Page)).getText().toString());
                    update_user.setUser_ID(user_id);

                    db.getAllergiesDAO().update(update_allergies);
                    db.getUserDAO().update(update_user);


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