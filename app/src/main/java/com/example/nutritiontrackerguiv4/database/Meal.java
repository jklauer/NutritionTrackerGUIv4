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
    private long Day_ID;
    private int mealnum;

    public Meal(){}

    public Meal(long d, int num)
    {
        Day_ID = d;
        mealnum = num;
    }

    public long getMeal_ID() {
        return Meal_ID;
    }

    public void setMeal_ID(long meal_ID) {
        Meal_ID = meal_ID;
    }

    public long getDay_ID() {
        return Day_ID;
    }

    public void setDay_ID(long day_ID) {
        this.Day_ID = day_ID;
    }

    public int getMealnum() {
        return mealnum;
    }

    public void setMealnum(int mealnum) {
        this.mealnum = mealnum;
    }
}