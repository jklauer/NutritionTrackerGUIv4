package com.example.nutritiontrackerguiv4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nutritiontrackerguiv4.database.NutritionDatabase;
import com.example.nutritiontrackerguiv4.ui.meals.MealsFragment;

import java.util.Calendar;

public class FavoriteMeals extends Activity {


    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_meals);

        loadButtons();

        Button backButton = (Button)findViewById(R.id.favMealsBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mealsFragment = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mealsFragment);
            }
        });
    }

    //Puts a button on the screen for every Ingredient in the database
    public void loadButtons(){

        ll = (LinearLayout) findViewById(R.id.favoriteMealsLayout); //where the buttons are placed

        NutritionDatabase db = NutritionDatabase.getDatabase(getApplicationContext());

        //Get the number of buttons to be added
        int numOfButtons = db.getIngredientDAO().getFavoriteMeals(true).size();

        if (numOfButtons == 0) {
            TextView noFavs = new TextView(this);
            noFavs.setTextSize(24);
            noFavs.setText("No Meals have been favorited at this time!");
            ll.addView(noFavs);
        }
        else {

            //for each button to be added...
            for (int i = 0; i < numOfButtons; i++) {

                Button newButton = new Button(this); //create a new button

                int finalI = i;

                String ingr_id = Long.toString(db.getIngredientDAO().getFavoriteMeals(true).get(finalI).getIngredient_ID());


                newButton.setOnClickListener(new View.OnClickListener() {


                    //the action onclick listener for the current new button
                    @Override
                    public void onClick(View v) {

                        //create an intent to load the input meal form when the button is clicked
                        Intent loadInputMealForm = new Intent(getApplicationContext(), InputMealForm.class);

                        //load the information of the current button into variables
                        String ingr_name = db.getIngredientDAO().getFavoriteMeals(true).get(finalI).getName();
                        String ingr_id = Long.toString(db.getIngredientDAO().getFavoriteMeals(true).get(finalI).getIngredient_ID());
                        String ingr_calories = Integer.toString(db.getIngredientDAO().getFavoriteMeals(true).get(finalI).getCalories());
                        String ingr_vitb6 = Integer.toString(db.getIngredientDAO().getFavoriteMeals(true).get(finalI).getVitaminA());
                        String ingr_vitc = Integer.toString(db.getIngredientDAO().getFavoriteMeals(true).get(finalI).getVitaminC());
                        String ingr_tofat = Integer.toString(db.getIngredientDAO().getFavoriteMeals(true).get(finalI).getTotalFat());
                        String ingr_sfat = Integer.toString(db.getIngredientDAO().getFavoriteMeals(true).get(finalI).getSatFat());
                        String ingr_trfat = Integer.toString(db.getIngredientDAO().getFavoriteMeals(true).get(finalI).getTransFat());
                        String ingr_chol = Integer.toString(db.getIngredientDAO().getFavoriteMeals(true).get(finalI).getCholesterol());
                        String ingr_sod = Integer.toString(db.getIngredientDAO().getFavoriteMeals(true).get(finalI).getSodium());
                        String ingr_carb = Integer.toString(db.getIngredientDAO().getFavoriteMeals(true).get(finalI).getTotalCarbs());
                        String ingr_fiber = Integer.toString(db.getIngredientDAO().getFavoriteMeals(true).get(finalI).getFiber());
                        String ingr_sugar = Integer.toString(db.getIngredientDAO().getFavoriteMeals(true).get(finalI).getSugar());
                        String ingr_prot = Integer.toString(db.getIngredientDAO().getFavoriteMeals(true).get(finalI).getProtein());
                        String ingr_calc = Integer.toString(db.getIngredientDAO().getFavoriteMeals(true).get(finalI).getCalcium());
                        String ingr_potas = Integer.toString(db.getIngredientDAO().getFavoriteMeals(true).get(finalI).getPotassium());
                        boolean ingr_fav = db.getIngredientDAO().getFavoriteMeals(true).get(finalI).getFavorite();

                        String ingr_time = db.getIngredientDAO().getFavoriteMeals(true).get(finalI).getTime();

                        //pass in the information to the input meal form
                        loadInputMealForm.putExtra("ingr_name", ingr_name);
                        loadInputMealForm.putExtra("ingr_calories", ingr_calories);
                        loadInputMealForm.putExtra("ingr_vitb6", ingr_vitb6);
                        loadInputMealForm.putExtra("ingr_tofat", ingr_tofat);
                        loadInputMealForm.putExtra("ingr_sfat", ingr_sfat);
                        loadInputMealForm.putExtra("ingr_trfat", ingr_trfat);
                        loadInputMealForm.putExtra("ingr_chol", ingr_chol);
                        loadInputMealForm.putExtra("ingr_carb", ingr_carb);
                        loadInputMealForm.putExtra("ingr_sod", ingr_sod);
                        loadInputMealForm.putExtra("ingr_fiber", ingr_fiber);
                        loadInputMealForm.putExtra("ingr_sugar", ingr_sugar);
                        loadInputMealForm.putExtra("ingr_prot", ingr_prot);
                        loadInputMealForm.putExtra("ingr_calc", ingr_calc);
                        loadInputMealForm.putExtra("ingr_potas", ingr_potas);
                        loadInputMealForm.putExtra("ingr_vitb6", ingr_vitb6);
                        loadInputMealForm.putExtra("ingr_vitc", ingr_vitc);
                        loadInputMealForm.putExtra("ingr_fav", ingr_fav);

                        System.out.println(ingr_name);

                        //load the input meal form
                        startActivity(loadInputMealForm);

                    }
                });

                //set the text of the current new button to the name of meal followed by time
                newButton.setText(db.getIngredientDAO().getFavoriteMeals(true).get(finalI).getName());


                //add the button to the layout
                ll.addView(newButton);
            }
        }
    }
}