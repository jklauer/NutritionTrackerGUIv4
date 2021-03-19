package com.example.nutritiontrackerguiv4.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Allergies")
public class Allergies {
    @PrimaryKey(autoGenerate = true)
    private long Allergy_ID;
    private Boolean nuts;
    private Boolean Seafood;
    //I'll add more as we finalize what we want to include
    //Still not sure how we're going to handle "custom" allergies

    public Allergies(int id, Boolean nuts, Boolean Seafood) {
        this.Allergy_ID = id;
        this.nuts = nuts;
        this.Seafood = Seafood;
    }
}