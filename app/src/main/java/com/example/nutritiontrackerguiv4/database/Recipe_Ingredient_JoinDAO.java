package com.example.nutritiontrackerguiv4.database;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface Recipe_Ingredient_JoinDAO {
    @Insert
    void insert(Meal_Recipe_Join meal_recipe_join);

    @Query("SELECT * FROM Ingredients INNER JOIN Recipe_Ingredient_Join ON Ingredients.Ingredient_ID" +
            "=Recipe_Ingredient_Join.Ingredient_ID WHERE Recipe_Ingredient_Join.Recipe_ID=:recipeID")
    List<User> getIngredientsForRecipe(final long recipeID);

}
