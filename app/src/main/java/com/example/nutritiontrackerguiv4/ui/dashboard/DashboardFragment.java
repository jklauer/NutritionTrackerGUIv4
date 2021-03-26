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

import java.util.Calendar;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private NutritionDatabase db;

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

        Integer calories = db.getIngredientDAO().findCaloriesOnDay(date).get(0);
        Integer vitA = db.getIngredientDAO().findVitAOnDay(date).get(0);
        Integer vitC = db.getIngredientDAO().findVitCOnDay(date).get(0);

        calView.setText("Calories: " + calories);
        vitAView.setText("Vitamin A: " + vitA);
        vitCView.setText("Vitamin C: " + vitC);

        return root;
    }
}