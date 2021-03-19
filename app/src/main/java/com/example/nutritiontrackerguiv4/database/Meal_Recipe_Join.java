package com.example.nutritiontrackerguiv4.database;

import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "Meal_Recipe_Join",
        primaryKeys = { "Meal_ID", "Recipe_ID" },
        foreignKeys = {
                @ForeignKey(entity = Meal.class,
                        parentColumns = "Meal_ID",
                        childColumns = "Meal_ID"),
                @ForeignKey(entity = Recipe.class,
                        parentColumns = "Recipe_ID",
                        childColumns = "Recipe_ID")
        })
public class Meal_Recipe_Join {
    public final int Meal_ID;
    public final int Recipe_ID;

    public Meal_Recipe_Join(final int Meal_ID, final int Recipe_ID) {
        this.Meal_ID = Meal_ID;
        this.Recipe_ID = Recipe_ID;
    }
}