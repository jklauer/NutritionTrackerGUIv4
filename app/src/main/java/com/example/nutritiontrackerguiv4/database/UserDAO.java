package com.example.nutritiontrackerguiv4.database;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface UserDAO {

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM Users")
    List<User> getAllUsers();

    @Query("SELECT * FROM Users WHERE User_id=:userId")
    List<User> findAllInfoForUser(final long userId);

    @Query("SELECT totalFat FROM Users WHERE User_id=:userId")
    List<Double> getTotalFatGoal(final long userId);

    @Query("SELECT totalCarbs FROM Users WHERE User_id=:userId")
    List<Double> getTotalCarbsGoal(final long userId);

    @Query("SELECT satFat FROM Users WHERE User_id=:userId")
    List<Double> getSatFatGoal(final long userId);

    @Query("SELECT transFat FROM Users WHERE User_id=:userId")
    List<Double> getTransFatGoal(final long userId);

    @Query("SELECT cholesterol FROM Users WHERE User_id=:userId")
    List<Double> getCholesterolGoal(final long userId);

    @Query("SELECT sodium FROM Users WHERE User_id=:userId")
    List<Double> getSodiumGoal(final long userId);

    @Query("SELECT fiber FROM Users WHERE User_id=:userId")
    List<Double> getFiberGoal(final long userId);

    @Query("SELECT sugar FROM Users WHERE User_id=:userId")
    List<Double> getSugarGoal(final long userId);

    @Query("SELECT protein FROM Users WHERE User_id=:userId")
    List<Double> getProteinGoal(final long userId);


}
