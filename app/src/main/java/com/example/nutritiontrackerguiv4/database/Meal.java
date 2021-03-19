package com.example.nutritiontrackerguiv4.database;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity(tableName = "Meals", foreignKeys =
        {
                @ForeignKey(entity = Day.class,
                        parentColumns = "Day_ID",
                        childColumns = "Day_ID",
                        onDelete = ForeignKey.CASCADE),
        }
)
public class Meal {
    /*Primary keys should be random and completely independent of the semantics of the entity.
    so I set it to auto-generate */
    @PrimaryKey(autoGenerate = true)
    private long Meal_ID;
    private long day_ID;
    private int mealnum;

    public Meal(long id, long d, int num)
    {
        Meal_ID = id;
        day_ID = d;
        mealnum = num;
    }


}