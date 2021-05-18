package com.example.nutritiontrackerguiv4.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Notifications")
public class Notifications {
    @PrimaryKey(autoGenerate = true)
    private long Notifications_ID;
    private int minutes_one;
    private int minutes_two;
    private int minutes_three;
    private int hours_one;
    private int hours_two;
    private int hours_three;

    public Notifications(){}

    public Notifications(int h1, int h2, int h3, int m1, int m2, int m3) {
        this.hours_one = h1;
        this.hours_two = h2;
        this.hours_three = h3;
        this.minutes_one = m1;
        this.minutes_two = m2;
        this.minutes_three = m3;
    }

    public long getNotifications_ID() {
        return this.Notifications_ID;
    }

    public int getMinutes_one(){return this.minutes_one;}

    public int getMinutes_two(){return this.minutes_two;}

    public int getMinutes_three(){return this.minutes_three;}

    public int getHours_one(){return this.hours_one;}

    public int getHours_two(){return this.hours_two;}

    public int getHours_three(){return this.hours_three;}




}
