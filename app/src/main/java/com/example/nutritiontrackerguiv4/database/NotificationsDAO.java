package com.example.nutritiontrackerguiv4.database;
import android.app.Notification;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface NotificationsDAO {

    @Insert
    void insert(Notifications notifications);

    @Update
    void update(Notification notification);

    @Delete
    void delete(Notifications notifications);

    @Query("SELECT * FROM Notifications")
    List<Notifications> getAllNotifications();

    @Query("SELECT hours_one FROM Notifications WHERE Notifications_ID=:notificationID")
    List<Integer> getFirstHour(final long notificationID);

    @Query("UPDATE Notifications SET hours_one=:h1 WHERE Notifications_ID=:notificationID")
    void setFirstHour(final int h1, final long notificationID);

    @Query("SELECT hours_two FROM Notifications WHERE Notifications_ID=:notificationID")
    List<Integer> getSecondHour(final long notificationID);

    @Query("UPDATE Notifications SET hours_two=:h2 WHERE Notifications_ID=:notificationID")
    void setSecondHour(final int h2, final long notificationID);

    @Query("SELECT hours_three FROM Notifications WHERE Notifications_ID=:notificationID")
    List<Integer> getThirdHour(final long notificationID);

    @Query("UPDATE Notifications SET hours_three=:h3 WHERE Notifications_ID=:notificationID")
    void setThirdHour(final int h3, final long notificationID);

    @Query("SELECT minutes_one FROM Notifications WHERE Notifications_ID=:notificationID")
    List<Integer> getFirstMinute(final long notificationID);

    @Query("UPDATE Notifications SET minutes_one=:m1 WHERE Notifications_ID=:notificationID")
    void setFirstMinute(final int m1, final long notificationID);

    @Query("SELECT minutes_two FROM Notifications WHERE Notifications_ID=:notificationID")
    List<Integer> getSecondMinute(final long notificationID);

    @Query("UPDATE Notifications SET minutes_two=:m2 WHERE Notifications_ID=:notificationID")
    void setSecondMinute(final int m2, final long notificationID);

    @Query("SELECT minutes_three FROM Notifications WHERE Notifications_ID=:notificationID")
    List<Integer> getThirdMinute(final long notificationID);

    @Query("UPDATE Notifications SET minutes_three=:m3 WHERE Notifications_ID=:notificationID")
    void setThirdMinute(final int m3, final long notificationID);


}
