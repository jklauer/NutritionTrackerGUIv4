package com.example.nutritiontrackerguiv4.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.nutritiontrackerguiv4.InputMealForm;
import com.example.nutritiontrackerguiv4.R;
import com.example.nutritiontrackerguiv4.database.NutritionDatabase;
import com.example.nutritiontrackerguiv4.ui.Tracking.TrackingActivity;

import java.util.Calendar;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private NutritionDatabase db;

    private Integer calories;
    private Integer totalFat;
    private Integer tranFat;
    private Integer cholesterol;
    private Integer satFat;
    private Integer sodium;
    private Integer carbs;
    private Integer fiber;
    private Integer sugar;
    private Integer protein;
    private Integer calcium;
    private Integer potassium;
    private Integer vitB6;
    private Integer vitC;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        db = NutritionDatabase.getDatabase(getContext());
        TextView calView = root.findViewById(R.id.CalorieView);
        TextView vitAView = root.findViewById(R.id.VitaminAView);
        TextView vitCView = root.findViewById(R.id.VitaminCView);

        String date = java.text.DateFormat.getDateInstance().format(Calendar.getInstance().getTime());

        calories = db.getIngredientDAO().findCaloriesOnDay(date).get(0);
        tranFat = db.getIngredientDAO().findTransFatOnDay(date).get(0);
        satFat = db.getIngredientDAO().findSatFatOnDay(date).get(0);
        cholesterol = db.getIngredientDAO().findCholesterolOnDay(date).get(0);
        sodium = db.getIngredientDAO().findSodiumOnDay(date).get(0);
        carbs = db.getIngredientDAO().findTotalCarbsOnDay(date).get(0);
        fiber = db.getIngredientDAO().findFiberOnDay(date).get(0);
        sugar = db.getIngredientDAO().findTotalSugarOnDay(date).get(0);
        protein = db.getIngredientDAO().findTotalProteinOnDay(date).get(0);
        calcium = db.getIngredientDAO().findTotalCalciumOnDay(date).get(0);
        potassium = db.getIngredientDAO().findTotalPotassiumOnDay(date).get(0);
        if(tranFat != null && satFat != null){
            totalFat = tranFat + satFat;
        }
        vitB6 = db.getIngredientDAO().findVitAOnDay(date).get(0);
        vitC = db.getIngredientDAO().findVitCOnDay(date).get(0);

        calView.setText("Calories: " + calories);
        vitAView.setText("Vitamin C: " + vitC);
        vitCView.setText("Vitamin B6: " + vitB6);

        Button goToGraphBtn = root.findViewById(R.id.goToGraphBtn);
        goToGraphBtn.setOnClickListener(v -> {
            Intent startIntent = new Intent(getContext(), TrackingActivity.class);
            startActivity(startIntent);
        });

        Button goToRecapBtn = root.findViewById(R.id.goToRecapBtn);
        goToRecapBtn.setOnClickListener(v -> {
            Intent startIntent = new Intent(getContext(), recap.class);
            startActivity(startIntent);
        });

        nutrientsRecommended(root);

        return root;
    }


    public void nutrientsRecommended(View root){
        System.out.println("Calories today: "+calories);
        System.out.println("b6 today: "+vitB6);
        System.out.println("c today: "+vitC);


        String date = java.text.DateFormat.getDateInstance().format(Calendar.getInstance().getTime());

        Integer numOfMeals = db.getIngredientDAO().getNumOfMeals(date).size();

        System.out.println("Num of meals: "+numOfMeals);

        String recommendationString = "";

        if(numOfMeals < 2){
            System.out.println("Not enough meals to give feedback.");
            recommendationString += "Not enough meals to give feedback.";
        }else{

            if(calories > 2000){
                System.out.println("You've gone over the daily limit for calories.");
                recommendationString += "You've gone over the daily limit for calories.\n";
            }
            if(totalFat > 78){
                System.out.println("You've gone over the daily limit for calories.");
                recommendationString += "You've gone over the daily limit for calories.\n";
            }
            if(satFat > 20){
                System.out.println("You've gone over the daily limit for satFat.");
                recommendationString += "You've gone over the daily limit for saturated fat.\n";
            }
            if(tranFat > 0){
                System.out.println("You've gone over the daily limit for trans fat.");
                recommendationString += "You've gone over the daily limit for trans fat.\n";
            }
            if(cholesterol > 300){
                System.out.println("You've gone over the daily limit for cholesterol.");
                recommendationString += "You've gone over the daily limit for cholesterol.\n";
            }
            if(sodium > 2300){
                System.out.println("You've gone over the daily limit for sodium.");
                recommendationString += "You've gone over the daily limit for sodium.\n";
            }
            if(carbs > 275){
                System.out.println("You've gone over the daily limit for carbs.");
                recommendationString += "You've gone over the daily limit for carbs.\n";
            }
            if(fiber < 28){
                System.out.println("Try eating a meal with more fiber.");
                recommendationString += "Try eating a meal with more fiber.\n";
            }
            if(sugar > 50){
                System.out.println("You've gone over the daily limit for sugar.");
                recommendationString += "You've gone over the daily limit for sugar.\n";
            }
            if(protein < 50){
                System.out.println("Try eating a meal with more protein.");
                recommendationString += "Try eating a meal with more protein.\n";
            }
            if(calcium < 1300){
                System.out.println("Try eating a meal with more calcium.");
                recommendationString += "Try eating a meal with more calcium.\n";
            }
            if(potassium < 4700){
                System.out.println("Try eating a meal with more potassium.");
                recommendationString += "Try eating a meal with more potassium.\n";
            }


            if(vitC < 90){
                System.out.println("Try eating a meal with more Vitamin C.");
                recommendationString += "Try eating a meal with more Vitamin C.\n";
            }
            if(vitB6 < 90){
                System.out.println("Try eating a meal with more Vitamin B6.");
                recommendationString += "Try eating a meal with more Vitamin B6.\n";
            }


        }

        ((TextView)root.findViewById(R.id.RecommendationView)).setText(recommendationString);


    }



}