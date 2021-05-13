package com.example.nutritiontrackerguiv4.ui.dashboard;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.nutritiontrackerguiv4.InputMealForm;
import com.example.nutritiontrackerguiv4.R;
import com.example.nutritiontrackerguiv4.StartPage;
import com.example.nutritiontrackerguiv4.database.NutritionDatabase;
import com.example.nutritiontrackerguiv4.database.User;
import com.example.nutritiontrackerguiv4.ui.Tracking.TrackingActivity;

import java.io.File;
import java.util.Calendar;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private NutritionDatabase db;

    private Integer calories;
    private Integer totFat;
    private Integer satFat;
    private Integer cholesterol;
    private Integer totalCarbs;
    private Integer fiber;
    private Integer sugar;
    private Integer protein;
    private Integer calcium;
    private Integer potassium;
    private Integer vitB6;
    private Integer vitC;
    private Double calorieGoal;
    private Double totalFatGoal;
    private Double satFatGoal;
    private Double cholesterolGoal;
    private Double carbsGoal;
    private Double fiberGoal;
    private Double sugarGoal;
    private Double proteinGoal;
    private Double calciumGoal;
    private Double potassiumGoal;
    private Double B6Goal;
    private Double CGoal;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);


        if(handleStartPage()){
            Intent loadStartPage = new Intent(getActivity().getApplicationContext(), StartPage.class);
            startActivity(loadStartPage);
        }else{
            doBarStuff(root);
        }




        return root;
    }

    public boolean handleStartPage(){

        File fileStartPage = new File(getActivity().getFilesDir(), "User_ID.txt");
        if(fileStartPage.exists()){
            return false;
        }else{
            return true;
        }

    }

    @SuppressLint("SetTextI18n")
    public void doBarStuff(View root){
        db = NutritionDatabase.getDatabase(getContext());
        TextView calView = root.findViewById(R.id.CalorieView);
        TextView calOver = root.findViewById(R.id.CalorieOver);
        TextView totFatOver = root.findViewById(R.id.totalFatOver);
        TextView satFatOver = root.findViewById(R.id.satFatOver);
        TextView cholesterolOver = root.findViewById(R.id.cholesterolOver);
        TextView totCarbsOver = root.findViewById(R.id.carbsOver);
        TextView fiberOver = root.findViewById(R.id.fiberOver);
        TextView sugarOver = root.findViewById(R.id.sugarOver);
        TextView proteinOver = root.findViewById(R.id.proteinOver);
        TextView calciumOver = root.findViewById(R.id.calciumOver);
        TextView potassiumOver = root.findViewById(R.id.potassiumOver);
        TextView B6Over = root.findViewById(R.id.B6Over);
        TextView COver = root.findViewById(R.id.COver);



        User user = db.getUserDAO().getAllUsers().get(0);
        long userID = user.getUser_ID();
        calorieGoal = db.getUserDAO().getCalorieGoal(userID).get(0);
        totalFatGoal = db.getUserDAO().getTotalFatGoal(userID).get(0);
        satFatGoal = db.getUserDAO().getSatFatGoal(userID).get(0);
        cholesterolGoal = db.getUserDAO().getCholesterolGoal(userID).get(0);
        carbsGoal = db.getUserDAO().getTotalCarbsGoal(userID).get(0);
        fiberGoal = db.getUserDAO().getFiberGoal(userID).get(0);
        sugarGoal = db.getUserDAO().getSugarGoal(userID).get(0);
        proteinGoal = db.getUserDAO().getProteinGoal(userID).get(0);
        calciumGoal = db.getUserDAO().getCalciumGoal(userID).get(0);
        potassiumGoal = db.getUserDAO().getPotassiumGoal(userID).get(0);
        B6Goal = db.getUserDAO().getVitAGoal(userID).get(0);
        CGoal = db.getUserDAO().getVitCGoal(userID).get(0);


        String date = java.text.DateFormat.getDateInstance().format(Calendar.getInstance().getTime());

        calories = db.getIngredientDAO().findCaloriesOnDay(date).get(0);
        totFat = db.getIngredientDAO().findTotalFatOnDay(date).get(0);
        satFat = db.getIngredientDAO().findSatFatOnDay(date).get(0);
        cholesterol = db.getIngredientDAO().findCholesterolOnDay(date).get(0);
        totalCarbs = db.getIngredientDAO().findTotalCarbsOnDay(date).get(0);
        fiber = db.getIngredientDAO().findFiberOnDay(date).get(0);
        sugar = db.getIngredientDAO().findTotalSugarOnDay(date).get(0);
        protein = db.getIngredientDAO().findTotalProteinOnDay(date).get(0);
        calcium = db.getIngredientDAO().findTotalCalciumOnDay(date).get(0);
        potassium = db.getIngredientDAO().findTotalPotassiumOnDay(date).get(0);
        vitB6 = db.getIngredientDAO().findVitAOnDay(date).get(0);
        vitC = db.getIngredientDAO().findVitCOnDay(date).get(0);

        if (calories == null) { calories = 0;}
        if (totFat == null) { totFat = 0;}
        if (satFat == null) { satFat = 0;}
        if (cholesterol == null) { cholesterol = 0;}
        if (totalCarbs == null) { totalCarbs = 0;}
        if (fiber == null) { fiber = 0;}
        if (sugar == null) { sugar = 0;}
        if (protein == null) { protein = 0;}
        if (calcium == null) { calcium = 0;}
        if (potassium == null) { potassium = 0;}
        if (vitB6 == null) { vitB6 = 0;}
        if (vitC == null) { vitC = 0;}

        calView.setText("Calories: " + calories);
        calOver.setText(" Calories                                                            " + calories + " / " + calorieGoal);
        totFatOver.setText(" Total Fat                                                          " + totFat + " / " + totalFatGoal + "g");
        satFatOver.setText(" Saturated Fat                                                       " + satFat + " / " + satFatGoal + "g");
        cholesterolOver.setText(" Cholesterol                                                   " + cholesterol + " / " + cholesterolGoal + "mg");
        totCarbsOver.setText(" Total Carbohydrates                                            " + totalCarbs + " / " + carbsGoal + "g");
        fiberOver.setText(" Fiber                                                          " + fiber + " / " + fiberGoal + "g");
        sugarOver.setText(" Sugar                                                          " + sugar + " / " + sugarGoal + "g");
        proteinOver.setText(" Protein                                                           " + protein + " / " + proteinGoal + "g");
        calciumOver.setText(" Calcium                                                          " + calcium + " / " + calciumGoal + "mg");
        potassiumOver.setText(" Potassium                                                        " + potassium + " / " + potassiumGoal + "mg");
        B6Over.setText(" Vitamin B6                                                       " + vitB6 + " / " + B6Goal + "mg");
        COver.setText(" Vitamin C                                                        " + vitC + " / " + CGoal + "mg");






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

        setBars(root);
    }


    public void setBars(View root){
        System.out.println("Calories today: "+calories);

        String date = java.text.DateFormat.getDateInstance().format(Calendar.getInstance().getTime());

        Integer numOfMeals = db.getIngredientDAO().getNumOfMeals(date).size();

        System.out.println("Num of meals: "+numOfMeals);

        if(numOfMeals < 1){

        }else{

            ProgressBar Bar1 = root.findViewById(R.id.firstBar);

            if(calories > calorieGoal){
                Bar1.setProgress(100);
            } else {
                double holder = 100 * (calories / calorieGoal);
                Bar1.setProgress((int)holder);
            }

            ProgressBar Bar2 = root.findViewById(R.id.secondBar);

            if(totFat > totalFatGoal){
                Bar2.setProgress(100);
            } else {
                double holder = 100 * (totFat / totalFatGoal);
                Bar2.setProgress((int)holder);
            }

            ProgressBar Bar3 = root.findViewById(R.id.thirdBar);

            if(satFat > satFatGoal){
                Bar3.setProgress(100);
            } else {
                double holder = 100 * (satFat / satFatGoal);
                Bar3.setProgress((int)holder);
            }

            ProgressBar Bar4 = root.findViewById(R.id.fourthBar);

            if(cholesterol > cholesterolGoal){
                Bar4.setProgress(100);
            } else {
                double holder = 100 * (cholesterol / cholesterolGoal);
                Bar4.setProgress((int)holder);
            }

            ProgressBar Bar5 = root.findViewById(R.id.fifthBar);

            if(totalCarbs > carbsGoal){
                Bar5.setProgress(100);
            } else {
                double holder = 100 * (totalCarbs / carbsGoal);
                Bar5.setProgress((int)holder);
            }

            ProgressBar Bar6 = root.findViewById(R.id.sixthBar);

            if(fiber > fiberGoal){
                Bar6.setProgress(100);
            } else {
                double holder = 100 * (fiber / fiberGoal);
                Bar6.setProgress((int)holder);
            }

            ProgressBar Bar7 = root.findViewById(R.id.seventhBar);

            if(sugar > sugarGoal){
                Bar7.setProgress(100);
            } else {
                double holder = 100 * (sugar / sugarGoal);
                Bar7.setProgress((int)holder);
            }

            ProgressBar Bar8 = root.findViewById(R.id.eigthBar);

            if(protein > proteinGoal){
                Bar8.setProgress(100);
            } else {
                double holder = 100 * (protein / proteinGoal);
                Bar8.setProgress((int)holder);
            }

            ProgressBar Bar9 = root.findViewById(R.id.ninthBar);

            if(calcium > calciumGoal){
                Bar9.setProgress(100);
            } else {
                double holder = 100 * (calcium / calciumGoal);
                Bar9.setProgress((int)holder);
            }

            ProgressBar Bar10 = root.findViewById(R.id.tenthBar);

            if(potassium > potassiumGoal){
                Bar10.setProgress(100);
            } else {
                double holder = 100 * (potassium / potassiumGoal);
                Bar10.setProgress((int)holder);
            }

            ProgressBar Bar11 = root.findViewById(R.id.eleventhBar);

            if(vitB6 > B6Goal){
                Bar11.setProgress(100);
            } else {
                double holder = 100 * (vitB6 / B6Goal);
                Bar11.setProgress((int)holder);
            }

            ProgressBar Bar12 = root.findViewById(R.id.twelvthBar);

            if(vitC > CGoal){
                Bar12.setProgress(100);
            } else {
                double holder = 100 * (vitC / CGoal);
                Bar12.setProgress((int)holder);
            }


        }


    }



}