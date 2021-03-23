package com.example.nutritiontrackerguiv4.database;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Recipes")
public class Recipe {
    /*Primary keys should be random and completely independent of the semantics of the entity.
    so I set it to auto-generate */
    @PrimaryKey(autoGenerate = true)
    private long Recipe_ID;
    private String name;

    public Recipe(){

    }
    public Recipe(String n) {
        this.name = n;
    }

    public long getRecipe_ID() {
        return Recipe_ID;
    }

    public void setRecipe_ID(long recipe_ID) {
        Recipe_ID = recipe_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
