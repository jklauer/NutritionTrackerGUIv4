package com.example.nutritiontrackerguiv4.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Allergies")
public class Allergies {
    @PrimaryKey(autoGenerate = true)
    private long Allergy_ID;
    private Boolean nuts;
    private Boolean Seafood;
    private Boolean shellfish;
    private Boolean milk;
    private Boolean eggs;
    private Boolean peanuts;
    private Boolean wheat;
    private Boolean soybeans;

    //I'll add more as we finalize what we want to include
    //Still not sure how we're going to handle "custom" allergies

    public Allergies(){

    }


    //milk,eggs,fish,shellfish,tree_nuts,peanuts,wheat,soybeans
    public Allergies(Boolean nuts, Boolean Seafood, Boolean shellfish, Boolean milk, Boolean eggs, Boolean peanuts, Boolean wheat, Boolean soybeans) {
        this.nuts = nuts;
        this.Seafood = Seafood;
        this.shellfish = shellfish;
        this.milk = milk;
        this.eggs = eggs;
        this.peanuts = peanuts;
        this.wheat = wheat;
        this.soybeans = soybeans;
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

    public Boolean getShellfish() { return shellfish; }

    public Boolean getMilk() { return milk; }

    public Boolean getEggs() { return eggs; }

    public Boolean getPeanuts() { return peanuts; }

    public Boolean getWheat() { return wheat; }

    public Boolean getSoybeans() { return soybeans; }

    public void setAllergy_ID(long allergy_ID) {
        Allergy_ID = allergy_ID;
    }

    public void setNuts(Boolean nuts) {
        this.nuts = nuts;
    }

    public void setSeafood(Boolean seafood) {
        Seafood = seafood;
    }

    public void setShellfish(Boolean shellfish) { this.shellfish = shellfish; }

    public void setMilk(Boolean milk) { this.milk = milk; }

    public void setEggs(Boolean eggs) { this.eggs = eggs; }

    public void setPeanuts(Boolean peanuts) { this.peanuts = peanuts; }

    public void setWheat(Boolean wheat) { this.wheat = wheat; }

    public void setSoybeans(Boolean soybeans) { this.soybeans = soybeans; }
}