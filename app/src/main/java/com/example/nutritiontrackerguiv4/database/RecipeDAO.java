package com.example.nutritiontrackerguiv4.database;
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
    List<User> getAllRecipes();

    @Query("SELECT name FROM Recipes")
    List<String> getAllnames();

    @Query("SELECT * FROM Recipes WHERE Recipe_ID=:recipeID")
    List<User> findAllInfoForRecipes(final int recipeID);


}
