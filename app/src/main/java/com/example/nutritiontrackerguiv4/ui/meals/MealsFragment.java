package com.example.nutritiontrackerguiv4.ui.meals;

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

import com.example.nutritiontrackerguiv4.InputMealForm;
import com.example.nutritiontrackerguiv4.R;
import com.example.nutritiontrackerguiv4.database.NutritionDatabase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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

        //gets the user id from the User_ID.txt file
        loadUserID();

        //load the buttons for each Ingredient in the database
        loadButtons(root);

        //the action listener for the add a meal button
        ((Button)(root.findViewById(R.id.InputMealForm_button))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loadInputMealForm = new Intent(getContext(), InputMealForm.class);
                startActivity(loadInputMealForm);
            }
        });


        return root;
    }

    //loads the user_id variable using the User_ID.txt file
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

    //Puts a button on the screen for every Ingredient in the database
    public void loadButtons(View root){

        LinearLayout ll = (LinearLayout)root.findViewById(R.id.fragment_meals_linear_layout); //where the buttons are placed

        //Get the number of buttons to be added
        int numOfButtons = db.getIngredientDAO().getAllIngredients().size();

        //for each button to be added...
        for(int i=0; i<numOfButtons; i++){

            Button newButton = new Button(getContext()); //create a new button

            int finalI = i;
            newButton.setOnClickListener(new View.OnClickListener() {


                //the action onclick listener for the current new button
                @Override
                public void onClick(View v) {

                    //create an intent to load the input meal form when the button is clicked
                    Intent loadInputMealForm = new Intent(getContext(), InputMealForm.class);

                    //load the information of the current button into variables
                    String ingr_name = db.getIngredientDAO().getAllIngredients().get(finalI).getName();
                    String ingr_id = Long.toString(db.getIngredientDAO().getAllIngredients().get(finalI).getIngredient_ID());
                    String ingr_calories = Integer.toString(db.getIngredientDAO().getAllIngredients().get(finalI).getCalories());
                    String ingr_vita = Integer.toString(db.getIngredientDAO().getAllIngredients().get(finalI).getVitaminA());
                    String ingr_vitc = Integer.toString(db.getIngredientDAO().getAllIngredients().get(finalI).getVitaminC());
                    String ingr_time = db.getIngredientDAO().getAllIngredients().get(finalI).getTime();

                    //pass in the information to the input meal form
                    loadInputMealForm.putExtra("ingr_name", ingr_name);
                    loadInputMealForm.putExtra("ingr_id", ingr_id);
                    loadInputMealForm.putExtra("ingr_calories", ingr_calories);
                    loadInputMealForm.putExtra("ingr_vita", ingr_vita);
                    loadInputMealForm.putExtra("ingr_vitc", ingr_vitc);
                    loadInputMealForm.putExtra("ingr_time", ingr_time);

                    //load the input meal form
                    startActivity(loadInputMealForm);

                }
            });

            //set the text of the current new button to the name of meal followed by time
            newButton.setText(db.getIngredientDAO().getAllIngredients().get(finalI).getName()
            + ": " + db.getIngredientDAO().getAllIngredients().get(finalI).getTime());


            //add the button to the layout
            ll.addView(newButton);

        }






    }

}
