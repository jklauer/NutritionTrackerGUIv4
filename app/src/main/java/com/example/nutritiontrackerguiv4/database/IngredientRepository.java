package com.example.nutritiontrackerguiv4.database;

import androidx.lifecycle.LiveData;

import java.util.List;

public class IngredientRepository {

    IngredientDAO dao;

    public IngredientRepository(IngredientDAO d){
        dao = d;
    }


    public void addIngredient(Ingredient i){
        dao.insert(i);
    }

    public void updateIngredient(Ingredient i){
        dao.update(i);
    }

    public void deleteIngredient(Ingredient i){
        dao.delete(i);
    }

    public List<Ingredient> getAllIngredient(){
        return dao.getAllIngredients();
    }

    public List<Ingredient> findAllInfoForIngredient(long id){
        return dao.findAllInfoForIngredient(id);
    }
   public List<Integer> findCaloriesOnDay(String d){return dao.findCaloriesOnDay(d);}

   public List<Integer> findVitAOnDay(String d){return dao.findVitAOnDay(d);}

   public List<Integer> findVitCOnDay(String d){return dao.findVitCOnDay(d);}

}
