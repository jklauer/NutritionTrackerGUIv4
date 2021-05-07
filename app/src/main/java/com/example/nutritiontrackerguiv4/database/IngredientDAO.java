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

    @Query("SELECT SUM(totalFat) FROM Ingredients WHERE Date=:d")
    List<Integer> findTotalFatOnDay(final String d);

    @Query("SELECT SUM(satFat) FROM Ingredients WHERE Date=:d")
    List<Integer> findSatFatOnDay(final String d);

    @Query("SELECT SUM(transFat) FROM Ingredients WHERE Date=:d")
    List<Integer> findTransFatOnDay(final String d);

    @Query("SELECT SUM(totalFat) FROM Ingredients WHERE Date=:d")
    List<Integer> findTotalFatOnDay(final String d);

    @Query("SELECT SUM(cholesterol) FROM Ingredients WHERE Date=:d")
    List<Integer> findCholesterolOnDay(final String d);

    @Query("SELECT SUM(sodium) FROM Ingredients WHERE Date=:d")
    List<Integer> findSodiumOnDay(final String d);

    @Query("SELECT SUM(totalCarbs) FROM Ingredients WHERE Date=:d")
    List<Integer> findTotalCarbsOnDay(final String d);

    @Query("SELECT SUM(fiber) FROM Ingredients WHERE Date=:d")
    List<Integer> findFiberOnDay(final String d);

    @Query("SELECT SUM(sugar) FROM Ingredients WHERE Date=:d")
    List<Integer> findTotalSugarOnDay(final String d);

    @Query("SELECT SUM(protein) FROM Ingredients WHERE Date=:d")
    List<Integer> findTotalProteinOnDay(final String d);

    @Query("SELECT SUM(calcium) FROM Ingredients WHERE Date=:d")
    List<Integer> findTotalCalciumOnDay(final String d);

    @Query("SELECT SUM(potassium) FROM Ingredients WHERE Date=:d")
    List<Integer> findTotalPotassiumOnDay(final String d);

    @Query("SELECT * FROM Ingredients WHERE Date=:d")
    List<Ingredient> getNumOfMeals(final String d);
}
