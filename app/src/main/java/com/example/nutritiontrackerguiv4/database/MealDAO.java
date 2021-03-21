package com.example.nutritiontrackerguiv4.database;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface MealDAO {

    @Insert
    void insert(Meal meal);

    @Update
    void update(Meal meal);

    @Delete
    void delete(Meal meal);

    @Query("SELECT * FROM Meals")
    LiveData<List<Meal>> getAllMeals();

    @Query("SELECT * FROM Meals WHERE Meal_ID=:mealId")
    LiveData<List<Meal>> findAllInfoForMeal(final long mealId);

    @Query("SELECT * FROM Meals WHERE Day_ID=:dayId")
    LiveData<List<Meal>> findMealsForDay(final long dayId);


}
