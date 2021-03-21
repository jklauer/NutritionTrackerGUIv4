package com.example.nutritiontrackerguiv4.database;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface RecipeDAO {

    @Insert
    void insert(Recipe recipe);

    @Update
    void update(Recipe recipe);

    @Delete
    void delete(Recipe recipe);

    @Query("SELECT * FROM Recipes")
    LiveData<List<Recipe>> getAllRecipes();

    @Query("SELECT name FROM Recipes")
    LiveData<List<String>> getAllnames();

    @Query("SELECT * FROM Recipes WHERE Recipe_ID=:recipeID")
    LiveData<List<Recipe>> findAllInfoForRecipes(final long recipeID);


}
