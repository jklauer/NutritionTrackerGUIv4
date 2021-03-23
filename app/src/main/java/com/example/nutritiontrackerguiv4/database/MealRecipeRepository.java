package com.example.nutritiontrackerguiv4.database;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MealRecipeRepository {

    Meal_Recipe_Join_DAO dao;

    public MealRecipeRepository(Meal_Recipe_Join_DAO d){
        dao = d;
    }

    public List<Recipe> getRecipesForMeal(int id){
        return dao.getRecipesForMeal(id);
    }
}
