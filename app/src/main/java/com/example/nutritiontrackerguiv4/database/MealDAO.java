package com.example.nutritiontrackerguiv4.database;
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
    List<User> getAllRepos();

    @Query("SELECT * FROM Meals WHERE Meal_ID=:mealId")
    List<User> findAllInfoForUser(final int mealId);


}
