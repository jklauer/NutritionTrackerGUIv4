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
    private Integer vitA;
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
        vitA = db.getIngredientDAO().findVitAOnDay(date).get(0);
        vitC = db.getIngredientDAO().findVitCOnDay(date).get(0);

        calView.setText("Calories: " + calories);
        vitAView.setText("Vitamin A: " + vitA);
        vitCView.setText("Vitamin C: " + vitC);

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
        System.out.println("a today: "+vitA);
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
            if(vitA*1000 < 900){
                System.out.println("Try eating a meal with more Vitamin A.");
                recommendationString += "Try eating a meal with more Vitamin A.\n";
            }
            if(vitC < 90){
                System.out.println("Try eating a meal with more Vitamin C.");
                recommendationString += "Try eating a meal with more Vitamin C.\n";
            }


        }

        ((TextView)root.findViewById(R.id.RecommendationView)).setText(recommendationString);


    }



}