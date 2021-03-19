package com.example.nutritiontrackerguiv4.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = { Allergies.class, Day.class, Ingredient.class, Meal.class,
        Meal_Recipe_Join.class, Recipe.class, Recipe_Ingredient_Join.class, User.class },
        version = 1)
public abstract class NutritionDatabase extends RoomDatabase {
    public abstract AllergiesDAO getAllergiesDAO();
    public abstract DayDAO getDayDAO();
    public abstract IngredientDAO getIngredientDAO();
    public abstract Meal_Recipe_Join_DAO getMealRecipeJoinDAO();
    public abstract MealDAO getMealDAO();
    public abstract Recipe_Ingredient_JoinDAO getRecipeIngredientJoinDAO();
    public abstract RecipeDAO getRecipeDAO();
    public abstract UserDAO getUserDAO();

}
