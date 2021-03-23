package com.example.nutritiontrackerguiv4.database;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MealRepository {

    MealDAO dao;

    public MealRepository(MealDAO d){
        dao = d;
    }

    public void addMeal(Meal m){
        dao.insert(m);
    }

    public void updateMeal(Meal m){
        dao.update(m);
    }

    public void deleteMeal(Meal m){
        dao.delete(m);
    }

    public List<Meal> getAllMeals(){
        return dao.getAllMeals();
    }

    public List<Meal> findAllInfoForMeal(long id){
        return dao.findAllInfoForMeal(id);
    }

    public List<Meal> findMealsForDay (long id){
        return dao.findMealsForDay(id);
    }
}
