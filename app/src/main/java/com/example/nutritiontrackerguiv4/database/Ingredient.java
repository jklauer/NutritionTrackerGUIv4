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
    private long satFat;
    private long transFat;
    private long cholesterol;
    private long sodium;
    private long totalCarbs;
    private long fiber;
    private long sugar;
    private long protein;
    private long calcium;
    private long potassium;
    private String time;
    private String date;

    public Ingredient(){}

    public Ingredient(String n, int cal, int toF, int sF, int trF, int cho, int car, int fib, int sug, int pro, int calc, int pot, int B6, int C, String t, String d) {
        this.name = n;
        this.calories = cal;
        this.vitaminA = B6;
        this.vitaminC = C;
        this.totalFat = toF;
        this.satFat = sF;
        this.transFat = trF;
        this.cholesterol = cho;
        this.totalCarbs = car;
        this.fiber = fib;
        this.sugar = sug;
        this.protein = pro;
        this.calcium = calc;
        this.potassium = pot;

        this.time = t;
        this.date = d;
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


    public long getTotalFat() {
        return totalFat;
    }

    public void setTotalFat(long totalFat) {
        this.totalFat = totalFat;
    }

    private long totalFat;

    public long getSatFat() {
        return satFat;
    }

    public long getTransFat() {
        return transFat;
    }

    public long getCholesterol() {
        return cholesterol;
    }

    public long getSodium() {
        return sodium;
    }

    public long getTotalCarbs() {
        return totalCarbs;
    }

    public long getFiber() {
        return fiber;
    }

    public long getSugar() {
        return sugar;
    }

    public long getProtein() {
        return protein;
    }

    public long getCalcium() { return calcium; }

    public long getPotassium() {return potassium; }

    public void setSatFat(long satFat) {
        this.satFat = satFat;
    }

    public void setTransFat(long transFat) {
        this.transFat = transFat;
    }

    public void setCholesterol(long cholesterol) {
        this.cholesterol = cholesterol;
    }

    public void setSodium(long sodium) {
        this.sodium = sodium;
    }

    public void setTotalCarbs(long totalCarbs) {
        this.totalCarbs = totalCarbs;
    }

    public void setFiber(long fiber) {
        this.fiber = fiber;
    }

    public void setSugar(long sugar) {
        this.sugar = sugar;
    }

    public void setProtein(long protein) {
        this.protein = protein;
    }

    public void setCalcium(long calcium) { this.calcium = calcium; }

    public void setPotassium(long potassium) { this.potassium = potassium; }

}
