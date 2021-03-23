package com.example.nutritiontrackerguiv4.database;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface Recipe_Ingredient_JoinDAO {
    @Insert
    void insert(Recipe_Ingredient_Join recipe_ingredient_join);

    @Query("SELECT * FROM Ingredients INNER JOIN Recipe_Ingredient_Join ON Ingredients.Ingredient_ID" +
            "=Recipe_Ingredient_Join.Ingredient_ID WHERE Recipe_Ingredient_Join.Recipe_ID=:recipeID")
    List<Ingredient> getIngredientsForRecipe(final long recipeID);

}
