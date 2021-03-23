package com.example.nutritiontrackerguiv4.database;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity(tableName = "Days", foreignKeys =
        {
         @ForeignKey(entity = User.class,
                 parentColumns = "User_ID",
                 childColumns = "User_ID",
                 onDelete = ForeignKey.CASCADE),
        }
        )
public class Day {
    /*Primary keys should be random and completely independent of the semantics of the entity.
    so I set it to auto-generate */
    @PrimaryKey(autoGenerate = true)
    private long Day_ID;
    private long User_ID;
    private String date;

    public Day(){}

    public Day(long u, String d)
    {
        User_ID = u;
        date = d.toString();
    }

    public long getDay_ID() {
        return Day_ID;
    }

    public long getUser_ID() {
        return User_ID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDay_ID(long day_ID) {
        Day_ID = day_ID;
    }

    public void setUser_ID(long user_ID) {
        User_ID = user_ID;
    }
}