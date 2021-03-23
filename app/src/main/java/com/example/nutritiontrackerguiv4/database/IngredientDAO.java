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
    List<Ingredient> findAllInfoForIngredient(final int ingID);


}
