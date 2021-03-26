package com.example.nutritiontrackerguiv4.database;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import java.util.Calendar;

@Entity(tableName = "Ingredients")
public class Ingredient {
    /*Primary keys should be random and completely independent of the semantics of the entity.
    so I set it to auto-generate */
    @PrimaryKey(autoGenerate = true)
    private long Ingredient_ID;
    private String name;
    private int calories;
    private int vitaminA;
    private int vitaminC;
    private String time;
    private String date;

    public Ingredient(){}

    public Ingredient(String n, int cal, int A, int C, String t) {
        this.name = n;
        this.calories = cal;
        this.vitaminA = A;
        this.vitaminC = C;
        this.time = t;
        this.date = java.text.DateFormat.getDateInstance().format(Calendar.getInstance().getTime());
    }

    public long getIngredient_ID() {
        return Ingredient_ID;
    }

    public void setIngredient_ID(long ingredient_ID) {
        Ingredient_ID = ingredient_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getVitaminA() {
        return vitaminA;
    }

    public void setVitaminA(int vitaminA) {
        this.vitaminA = vitaminA;
    }

    public int getVitaminC() {
        return vitaminC;
    }

    public void setVitaminC(int vitaminC) {
        this.vitaminC = vitaminC;
    }

    public String getTime(){
        return time;
    }

    public void setTime(String t){
        this.time = t;
    }

    public String getDate() {return date;}
    
    public void setDate(String d) {this.date = d;}
}
