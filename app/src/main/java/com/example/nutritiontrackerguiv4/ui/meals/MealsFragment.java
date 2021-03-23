package com.example.nutritiontrackerguiv4.ui.meals;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.nutritiontrackerguiv4.GlobalMethods;
import com.example.nutritiontrackerguiv4.InputMealForm;
import com.example.nutritiontrackerguiv4.MainActivity;
import com.example.nutritiontrackerguiv4.R;
import com.example.nutritiontrackerguiv4.database.Meal;
import com.example.nutritiontrackerguiv4.database.NutritionDatabase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class MealsFragment extends Fragment {

    private long user_id;
    private NutritionDatabase db;

    private MealsViewModel mealsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mealsViewModel =
                new ViewModelProvider(this).get(MealsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_meals, container, false);

        db = NutritionDatabase.getDatabase(getContext());

        loadUserID();

        loadButtons(root);

        ((Button)(root.findViewById(R.id.InputMealForm_button))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loadInputMealForm = new Intent(getContext(), InputMealForm.class);
                startActivity(loadInputMealForm);
            }
        });


        return root;
    }

    public void loadUserID(){
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(getActivity().getFilesDir(),"User_ID.txt")));
            this.user_id = Long.parseLong(br.readLine());
            System.out.println("User loaded... user_id = "+user_id);
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadButtons(View root){

        LinearLayout ll = (LinearLayout)root.findViewById(R.id.fragment_meals_linear_layout);

        int numOfButtons = db.getIngredientDAO().getAllIngredients().size();
        for(int i=0; i<numOfButtons; i++){

            Button newButton = new Button(getContext());

            int finalI = i;
            newButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent loadInputMealForm = new Intent(getContext(), InputMealForm.class);

                    String ingr_name = db.getIngredientDAO().getAllIngredients().get(finalI).getName();
                    String ingr_id = Long.toString(db.getIngredientDAO().getAllIngredients().get(finalI).getIngredient_ID());
                    String ingr_calories = Integer.toString(db.getIngredientDAO().getAllIngredients().get(finalI).getCalories());
                    String ingr_vita = Integer.toString(db.getIngredientDAO().getAllIngredients().get(finalI).getVitaminA());
                    String ingr_vitc = Integer.toString(db.getIngredientDAO().getAllIngredients().get(finalI).getVitaminC());
                    String ingr_time = db.getIngredientDAO().getAllIngredients().get(finalI).getTime();

                    loadInputMealForm.putExtra("ingr_name", ingr_name);
                    loadInputMealForm.putExtra("ingr_id", ingr_id);
                    loadInputMealForm.putExtra("ingr_calories", ingr_calories);
                    loadInputMealForm.putExtra("ingr_vita", ingr_vita);
                    loadInputMealForm.putExtra("ingr_vitc", ingr_vitc);
                    loadInputMealForm.putExtra("ingr_time", ingr_time);

                    startActivity(loadInputMealForm);

                }
            });
            newButton.setText(db.getIngredientDAO().getAllIngredients().get(finalI).getName()
            + ": " + db.getIngredientDAO().getAllIngredients().get(finalI).getTime());


            ll.addView(newButton);

        }






    }

}
