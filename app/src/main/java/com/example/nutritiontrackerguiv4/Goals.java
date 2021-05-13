package com.example.nutritiontrackerguiv4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nutritiontrackerguiv4.database.NutritionDatabase;
import com.example.nutritiontrackerguiv4.database.User;
import com.example.nutritiontrackerguiv4.ui.dashboard.DashboardFragment;
import com.example.nutritiontrackerguiv4.ui.settings.SettingsFragment;
import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class Goals extends AppCompatActivity {

    NutritionDatabase db;
    User user;
    long userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);

        db =  NutritionDatabase.getDatabase(getApplicationContext());
        user = db.getUserDAO().getAllUsers().get(0);
        userID = user.getUser_ID();


        TextView popupText = (TextView)findViewById(R.id.popupText);
        popupText.setText("Enter your goals here!");

        Button revertGoalsBtn = (Button)findViewById(R.id.revertGoalsBtn);
        revertGoalsBtn.setOnClickListener(v -> {
            db.getUserDAO().resetDefaultGoals(userID);
            popupText.setText("Reset is completed!");
            System.out.println("The reset did happen "+db.getUserDAO().getCalorieGoal(userID));
            Snackbar.make(findViewById(R.id.goals_id), "Reset is completed!",
                    Snackbar.LENGTH_SHORT)
                    .show();
        });

        Button confirmGoalsBtn = (Button)findViewById(R.id.confirmGoalsBtn);
        confirmGoalsBtn.setOnClickListener(v -> {
            setNewGoals();

            popupText.setText("New goals are set!");
            System.out.println("New goals were set"+db.getUserDAO().getCalorieGoal(userID));
            Snackbar.make(findViewById(R.id.goals_id), "New goals are set!",
                    Snackbar.LENGTH_SHORT)
                    .show();
        });
    }

    void setNewGoals()
    {
        ArrayList<String> goals = getEditTexts();
        if (!goals.get(0).equals("")) {
            double d = attemptToSet(goals.get(0));
            if (d != -1.0)
                db.getUserDAO().setCalorieGoal(d, userID);
        }
        if (!goals.get(1).equals("")) {
            double d = attemptToSet(goals.get(1));
            if (d != -1.0)
                db.getUserDAO().setVitAGoal(d, userID);
        }
        if (!goals.get(2).equals("")) {
            double d = attemptToSet(goals.get(2));
            if (d != -1.0)
                db.getUserDAO().setVitCGoal(d, userID);
        }
        if (!goals.get(3).equals("")) {
            double d = attemptToSet(goals.get(3));
            if (d != -1.0)
                db.getUserDAO().setTotalFatGoal(d, userID);
        }
        if (!goals.get(4).equals("")) {
            double d = attemptToSet(goals.get(4));
            if (d != -1.0)
                db.getUserDAO().setSatFatGoal(d, userID);
        }
        if (!goals.get(5).equals("")) {
            double d = attemptToSet(goals.get(5));
            if (d != -1.0)
                db.getUserDAO().setTransFatGoal(d, userID);
        }
        if (!goals.get(6).equals("")) {
            double d = attemptToSet(goals.get(6));
            if (d != -1.0)
                db.getUserDAO().setCholesterolGoal(d, userID);
        }
        if (!goals.get(7).equals("")) {
            double d = attemptToSet(goals.get(7));
            if (d != -1.0)
                db.getUserDAO().setSodiumGoal(d, userID);
        }
        if (!goals.get(8).equals("")) {
            double d = attemptToSet(goals.get(8));
            if (d != -1.0)
                db.getUserDAO().setTotalCarbsGoal(d, userID);
        }
        if (!goals.get(9).equals("")) {
            double d = attemptToSet(goals.get(9));
            if (d != -1.0)
                db.getUserDAO().setFiberGoal(d, userID);
        }
        if (!goals.get(10).equals("")) {
            double d = attemptToSet(goals.get(10));
            if (d != -1.0)
                db.getUserDAO().setSugarGoal(d, userID);
        }
        if (!goals.get(11).equals("")) {
            double d = attemptToSet(goals.get(11));
            if (d != -1.0)
                db.getUserDAO().setProteinGoal(d, userID);
        }
    }

    double attemptToSet(String goal)
    {
        double d = 0.0;
        try {
            d = Double.parseDouble(goal);
        } catch (NumberFormatException nfe){
            System.out.println("NumberFormatException in Goals");
            d = -1.0;
        }
        return d;
    }

    ArrayList<String> getEditTexts()
    {
        ArrayList<String> texts = new ArrayList<>();
        EditText calEditText = (EditText) findViewById(R.id.calEditText);
        texts.add(calEditText.getText().toString());

        EditText vitAEditText = (EditText) findViewById(R.id.VitAEditText);
        texts.add(vitAEditText.getText().toString());

        EditText vitCEditText = (EditText) findViewById(R.id.vitCEditText);
        texts.add(vitCEditText.getText().toString());

        EditText totalFatEditText = (EditText) findViewById(R.id.totalFatEditText);
        texts.add(totalFatEditText.getText().toString());

        EditText satFatEditText = (EditText) findViewById(R.id.satFatEditText);
        texts.add(satFatEditText.getText().toString());

        EditText transFatEditText = (EditText) findViewById(R.id.transFatEditText);
        texts.add(transFatEditText.getText().toString());

        EditText cholesterolEditText = (EditText) findViewById(R.id.cholesterolEditText);
        texts.add(cholesterolEditText.getText().toString());

        EditText sodiumEditText = (EditText) findViewById(R.id.sodiumEditText);
        texts.add(sodiumEditText.getText().toString());

        EditText carbsEditText = (EditText) findViewById(R.id.totalCarbsEditText);
        texts.add(carbsEditText.getText().toString());

        EditText fiberEditText = (EditText) findViewById(R.id.fiberEditText);
        texts.add(fiberEditText.getText().toString());

        EditText sugarEditText = (EditText) findViewById(R.id.sugarEditText);
        texts.add(sugarEditText.getText().toString());

        EditText proteinEditText = (EditText) findViewById(R.id.proteinEditText);
        texts.add(proteinEditText.getText().toString());

        return texts;
    }
}