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


    public Recipe(int id, String n, int ing) {
        this.Recipe_ID = id;
        this.name = n;
    }
}
