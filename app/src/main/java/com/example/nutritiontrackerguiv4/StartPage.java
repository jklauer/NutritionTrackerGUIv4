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

    private boolean user_exists = false; //boolean for if it is first startup or not
    private long user_id; //the user's id in database
    private long allergies_id; //the allergy id for the user in the database
    NutritionDatabase db;

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        db = NutritionDatabase.getDatabase(getApplicationContext());

        //Check if the user exists by seeing if the User_ID.txt already exists
        File user_id_file = new File(getFilesDir(), "User_ID.txt");
        if(user_id_file.exists()){
            user_exists = true;
        }

        //System.out.println("User exists = "+user_exists);

        //If user exists, load the user id and allergies id of that user.
        if(user_exists){
            try {
                //read in the user id from the User_ID.txt file
                BufferedReader br = new BufferedReader(new FileReader(user_id_file));
                this.user_id = Long.parseLong(br.readLine()); //get the user's id
                this.allergies_id = db.getUserDAO().findAllInfoForUser(user_id).get(0).getAllergy_ID(); //get the user's allergies
                br.close();

                loadInformation(); //load the information of the user and their allergies on the startpage form
                handleContinueButton(); //set up the listener for the continue button

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else{

            //if the file did not exist, it was the first start up
            Button ContinueButton = (Button) findViewById(R.id.continue_button);

            //determine what the continue button does on first startup
            ContinueButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    File fileStartPage = new File(getFilesDir(), "User_ID.txt");
                    try {
                        //get the allergy information inputted by the user
                        BufferedWriter bw = new BufferedWriter(new FileWriter(fileStartPage));
                        NutritionDatabase db = NutritionDatabase.getDatabase(getApplicationContext());

                        boolean tree_nuts = ((CheckBox)findViewById(R.id.allergy_tree_nuts_start_page)).isChecked();
                        boolean fish = ((CheckBox)findViewById(R.id.allergy_fish_start_page)).isChecked();
                        Allergies new_allergies = new Allergies(
                                tree_nuts,
                                fish
                        );

                        db.getAllergiesDAO().insert(new_allergies); //insert the allergy information into the database
                        allergies_id = db.getAllergiesDAO().getAllAllergies().get(0).getAllergy_ID();

                        //create a new user with the already created allergy information and add it to the database
                        User new_user = new User(allergies_id, ((EditText)findViewById(R.id.username_input_Start_Page)).getText().toString());
                        db.getUserDAO().insert(new_user);
                        user_id = db.getUserDAO().getAllUsers().get(0).getUser_ID();

                        //save the user id in User_ID.txt file
                        bw.write(Long.toString(user_id));

                        bw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    //load main activity
                    Intent loadApp = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(loadApp);
                }
            });
        }


    }

    //Loads the information of the user and their allergies into the start page form
    public void loadInformation(){

        //Get the user's allergies
        Allergies load_allergies = db.getAllergiesDAO().findAllInfoForAllergies(allergies_id).get(0);
        boolean tree_nuts = load_allergies.getNuts();
        //System.out.println("User id: "+user_id);
        //System.out.println("Allergies id: "+allergies_id);
        //System.out.println("Tree nuts: "+tree_nuts);
        boolean fish = load_allergies.getSeafood();
        //System.out.println("Fish: "+fish);
        User load_user = db.getUserDAO().findAllInfoForUser(user_id).get(0);
        String user_name = load_user.getName();

        //set the allergies and username in the start page form
        ((CheckBox)findViewById(R.id.allergy_tree_nuts_start_page)).setChecked(tree_nuts);
        ((CheckBox)findViewById(R.id.allergy_fish_start_page)).setChecked(fish);
        ((EditText)findViewById(R.id.username_input_Start_Page)).setText(user_name);
    }

    //handles the continue button for when the user already existed
    public void handleContinueButton(){

        //set up continue button listener
        Button ContinueButton = (Button) findViewById(R.id.continue_button);
        ContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NutritionDatabase db = NutritionDatabase.getDatabase(getApplicationContext());

                //create new allergy based on inputted information
                boolean tree_nuts = ((CheckBox)findViewById(R.id.allergy_tree_nuts_start_page)).isChecked();
                boolean fish = ((CheckBox)findViewById(R.id.allergy_fish_start_page)).isChecked();
                Allergies update_allergies = new Allergies(
                        tree_nuts,
                        fish
                );
                update_allergies.setAllergy_ID(allergies_id);

                //update the new allergy_id (this is redundant)
                User update_user = new User(allergies_id, ((EditText)findViewById(R.id.username_input_Start_Page)).getText().toString());
                update_user.setUser_ID(user_id);
                db.getAllergiesDAO().update(update_allergies);
                db.getUserDAO().update(update_user);

                //load main activity
                Intent loadApp = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(loadApp);
            }
        });
    }


}