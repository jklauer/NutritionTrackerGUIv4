package com.example.nutritiontrackerguiv4.database;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface Meal_Recipe_Join_DAO {
    @Insert
    void insert(Meal_Recipe_Join meal_recipe_join);

    @Query("SELECT * FROM Recipes INNER JOIN Meal_Recipe_Join ON Recipes.Recipe_ID" +
            "=Meal_Recipe_Join.Recipe_ID WHERE Meal_Recipe_Join.Meal_ID=:mealId")
            LiveData<List<Recipe>> getRecipesForMeal(final long mealId);

}
