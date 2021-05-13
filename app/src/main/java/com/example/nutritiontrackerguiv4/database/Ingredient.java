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
    private int totalFat;
    private int satFat;
    private int transFat;
    private int cholesterol;
    private int sodium;
    private int totalCarbs;
    private int fiber;
    private int sugar;
    private int protein;
    private int calcium;
    private int potassium;
    private String time;
    private String date;
    private boolean favorite;

    public Ingredient(){}

    public Ingredient(String n, int cal, int toF, int sF, int trF, int cho, int sod, int car, int fib, int sug, int pro, int calc, int pot, int B6, int C, String t, String d) {
        this.name = n;
        this.calories = cal;
        this.vitaminA = B6;
        this.vitaminC = C;
        this.totalFat = toF;
        this.satFat = sF;
        this.transFat = trF;
        this.cholesterol = cho;
        this.sodium = sod;
        this.totalCarbs = car;
        this.fiber = fib;
        this.sugar = sug;
        this.protein = pro;
        this.calcium = calc;
        this.potassium = pot;
        this.favorite = false;

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


    public int getTotalFat() {
        return totalFat;
    }

    public void setTotalFat(int totalFat) {
        this.totalFat = totalFat;
    }

    public int getSatFat() {
        return satFat;
    }

    public int getTransFat() {
        return transFat;
    }

    public int getCholesterol() {
        return cholesterol;
    }

    public int getSodium() {
        return sodium;
    }

    public int getTotalCarbs() {
        return totalCarbs;
    }

    public int getFiber() {
        return fiber;
    }

    public int getSugar() {
        return sugar;
    }

    public int getProtein() {
        return protein;
    }

    public int getCalcium() { return calcium; }

    public int getPotassium() {return potassium; }

    public void setSatFat(int satFat) {
        this.satFat = satFat;
    }

    public void setTransFat(int transFat) {
        this.transFat = transFat;
    }

    public void setCholesterol(int cholesterol) {
        this.cholesterol = cholesterol;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public void setTotalCarbs(int totalCarbs) {
        this.totalCarbs = totalCarbs;
    }

    public void setFiber(int fiber) {
        this.fiber = fiber;
    }

    public void setSugar(int sugar) {
        this.sugar = sugar;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public void setCalcium(int calcium) { this.calcium = calcium; }

    public void setPotassium(int potassium) { this.potassium = potassium; }

    public void setFavorite(boolean favorite) { this.favorite = favorite; }

    public boolean getFavorite() { return favorite; }

}
