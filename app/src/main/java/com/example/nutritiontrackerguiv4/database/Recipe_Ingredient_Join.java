package com.example.nutritiontrackerguiv4.database;

import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "Recipe_Ingredient_Join",
        primaryKeys = { "Ingredient_ID", "Recipe_ID" },
        foreignKeys = {
                @ForeignKey(entity = Ingredient.class,
                        parentColumns = "Ingredient_ID",
                        childColumns = "Ingredient_ID"),
                @ForeignKey(entity = Recipe.class,
                        parentColumns = "Recipe_ID",
                        childColumns = "Recipe_ID")
        })
public class Recipe_Ingredient_Join {
    public final int Ingredient_ID;
    public final int Recipe_ID;

    public Recipe_Ingredient_Join(final int Ingredient_ID, final int Recipe_ID) {
        this.Ingredient_ID = Ingredient_ID;
        this.Recipe_ID = Recipe_ID;
    }
}