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

    public Allergies(){

    }

    public Allergies(int id, Boolean nuts, Boolean Seafood) {
        this.Allergy_ID = id;
        this.nuts = nuts;
        this.Seafood = Seafood;
    }

    public long getAllergy_ID() {
        return this.Allergy_ID;
    }

    public Boolean getNuts() {
        return this.nuts;
    }

    public Boolean getSeafood() {
        return this.Seafood;
    }

    public void setAllergy_ID(long allergy_ID) {
        Allergy_ID = allergy_ID;
    }

    public void setNuts(Boolean nuts) {
        this.nuts = nuts;
    }

    public void setSeafood(Boolean seafood) {
        Seafood = seafood;
    }
}