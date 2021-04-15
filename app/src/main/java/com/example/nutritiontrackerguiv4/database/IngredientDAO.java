package com.example.nutritiontrackerguiv4.database;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface IngredientDAO {

    @Insert
    void insert(Ingredient ingredient);

    @Update
    void update(Ingredient ingredient);

    @Delete
    void delete(Ingredient ingredient);

    @Query("SELECT * FROM Ingredients")
    List<Ingredient> getAllIngredients();

    @Query("SELECT * FROM Ingredients WHERE Ingredient_ID=:ingID")
    List<Ingredient> findAllInfoForIngredient(final long ingID);

    @Query("SELECT SUM(Calories) FROM Ingredients WHERE Date=:d")
    List<Integer> findCaloriesOnDay(final String d);

    @Query("SELECT SUM(vitaminA) FROM Ingredients WHERE Date=:d")
    List<Integer> findVitAOnDay(final String d);

    @Query("SELECT SUM(vitaminC) FROM Ingredients WHERE Date=:d")
    List<Integer> findVitCOnDay(final String d);

    @Query("SELECT * FROM Ingredients WHERE Date=:d")
    List<Ingredient> getNumOfMeals(final String d);
}
