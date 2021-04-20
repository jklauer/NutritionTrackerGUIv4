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

    @Query("UPDATE Users SET totalFat=:tFat WHERE User_ID=:userID")
    void setTotalFatGoal(final Double tFat, final long userID);

    @Query("SELECT totalCarbs FROM Users WHERE User_id=:userId")
    List<Double> getTotalCarbsGoal(final long userId);

    @Query("UPDATE Users SET totalCarbs=:tCarbs WHERE User_ID=:userID")
    void setTotalCarbsGoal(final Double tCarbs, final long userID);

    @Query("SELECT satFat FROM Users WHERE User_id=:userId")
    List<Double> getSatFatGoal(final long userId);

    @Query("UPDATE Users SET satFat=:sFat WHERE User_ID=:userID")
    void setSatFatGoal(final Double sFat, final long userID);

    @Query("SELECT transFat FROM Users WHERE User_id=:userId")
    List<Double> getTransFatGoal(final long userId);

    @Query("UPDATE Users SET transFat=:tFat WHERE User_ID=:userID")
    void setTransFatGoal(final Double tFat, final long userID);

    @Query("SELECT cholesterol FROM Users WHERE User_id=:userId")
    List<Double> getCholesterolGoal(final long userId);

    @Query("UPDATE Users SET cholesterol=:chol WHERE User_ID=:userID")
    void setCholesterolGoal(final Double chol, final long userID);

    @Query("SELECT sodium FROM Users WHERE User_id=:userId")
    List<Double> getSodiumGoal(final long userId);

    @Query("UPDATE Users SET sodium=:sod WHERE User_ID=:userID")
    void setSodiumGoal(final Double sod, final long userID);

    @Query("SELECT fiber FROM Users WHERE User_id=:userId")
    List<Double> getFiberGoal(final long userId);

    @Query("UPDATE Users SET fiber=:fiber WHERE User_ID=:userID")
    void setFiberGoal(final Double fiber, final long userID);

    @Query("SELECT sugar FROM Users WHERE User_id=:userId")
    List<Double> getSugarGoal(final long userId);

    @Query("UPDATE Users SET sugar=:sugar WHERE User_ID=:userID")
    void setSugarGoal(final Double sugar, final long userID);

    @Query("SELECT protein FROM Users WHERE User_id=:userId")
    List<Double> getProteinGoal(final long userId);

    @Query("UPDATE Users SET protein=:pro WHERE User_ID=:userID")
    void setProteinGoal(final Double pro, final long userID);

    @Query("SELECT calories FROM Users WHERE User_id=:userId")
    List<Double> getCalorieGoal(final long userId);

    @Query("UPDATE Users SET calories=:cal WHERE User_ID=:userID")
    void setCalorieGoal(final Double cal, final long userID);

    @Query("SELECT vitA FROM Users WHERE User_id=:userId")
    List<Double> getVitAGoal(final long userId);

    @Query("UPDATE Users SET vitA=:A WHERE User_ID=:userID")
    void setVitAGoal(final Double A, final long userID);

    @Query("SELECT vitC FROM Users WHERE User_id=:userId")
    List<Double> getVitCGoal(final long userId);

    @Query("UPDATE Users SET vitC=:C WHERE User_ID=:userID")
    void setVitCGoal(final Double C, final long userID);
}
