package com.example.nutritiontrackerguiv4.database;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Users", foreignKeys =
        {
         @ForeignKey(entity = Allergies.class,
         parentColumns = "Allergy_ID",
         childColumns = "Allergy_ID",
         onDelete = ForeignKey.CASCADE),
        })
public class User {
    /*Primary keys should be random and completely independent of the semantics of the entity.
    so I set it to auto-generate */
    @PrimaryKey(autoGenerate = true)
    private long User_ID;
    private long Allergy_ID;
    private String name;

    private Double totalFat = 78.0;
    private Double satFat = 20.0;
    private Double transFat = 0.0;
    private Double cholesterol = 300.0;
    private Double sodium = 2300.0;
    private Double totalCarbs = 275.0;
    private Double fiber = 28.0;
    private Double sugar = 50.0;
    private Double protein = 50.0;


    public User(){}

    public User(long A, String n) {
        this.Allergy_ID = A;
        this.name = n;
    }

    public long getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(long user_id) {
        User_ID = user_id;
    }

    public long getAllergy_ID() {
        return Allergy_ID;
    }

    public void setAllergy_ID(long allergy_ID) {
        Allergy_ID = allergy_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void resetDefaultGoals() {
        totalFat = 78.0;
        satFat = 20.0;
        transFat = 0.0;
        cholesterol = 300.0;
        sodium = 2300.0;
        totalCarbs = 275.0;
        fiber = 28.0;
        sugar = 50.0;
        protein = 50.0;
    }

    public Double getTotalFat() {
        return totalFat;
    }

    public void setTotalFat(Double totalFat) {
        this.totalFat = totalFat;
    }

    public Double getSatFat() {
        return satFat;
    }

    public void setSatFat(Double satFat) {
        this.satFat = satFat;
    }

    public Double getTransFat() {
        return transFat;
    }

    public void setTransFat(Double transFat) {
        this.transFat = transFat;
    }

    public Double getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(Double cholesterol) {
        this.cholesterol = cholesterol;
    }

    public Double getSodium() {
        return sodium;
    }

    public void setSodium(Double sodium) {
        this.sodium = sodium;
    }

    public Double getTotalCarbs() {
        return totalCarbs;
    }

    public void setTotalCarbs(Double totalCarbs) {
        this.totalCarbs = totalCarbs;
    }

    public Double getFiber() {
        return fiber;
    }

    public void setFiber(Double fiber) {
        this.fiber = fiber;
    }

    public Double getSugar() {
        return sugar;
    }

    public void setSugar(Double sugar) {
        this.sugar = sugar;
    }

    public Double getProtein() {
        return protein;
    }

    public void setProtein(Double protein) {
        this.protein = protein;
    }
}
