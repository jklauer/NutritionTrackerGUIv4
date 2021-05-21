package com.example.nutritiontrackerguiv4.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Notifications")
public class Notifications {
    @PrimaryKey(autoGenerate = true)
    private long Notifications_ID;
    private Integer minutes_one;
    private Integer minutes_two;
    private Integer minutes_three;
    private Integer hours_one;
    private Integer hours_two;
    private Integer hours_three;

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

    public void setNotifications_ID(long in){this.Notifications_ID = in;}

    public Integer getMinutes_one(){return this.minutes_one;}

    public void setMinutes_one(Integer m1){this.minutes_one = m1;}

    public Integer getMinutes_two(){return this.minutes_two;}

    public void setMinutes_two(Integer m2){this.minutes_two = m2;}

    public Integer getMinutes_three(){return this.minutes_three;}

    public void setMinutes_three(Integer m3){this.minutes_three = m3;}

    public Integer getHours_one(){return this.hours_one;}

    public void setHours_one(Integer h1){this.hours_one = h1;}

    public Integer getHours_two(){return this.hours_two;}

    public void setHours_two(Integer h2){this.hours_two = h2;}

    public Integer getHours_three(){return this.hours_three;}

    public void setHours_three(Integer h3){this.hours_three = h3;}


}
