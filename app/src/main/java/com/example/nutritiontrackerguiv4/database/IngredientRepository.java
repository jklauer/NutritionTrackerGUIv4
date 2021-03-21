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

    public LiveData<List<Ingredient>> getAllIngredient(){
        return dao.getAllIngredients();
    }

    public LiveData<List<Ingredient>> findAllInfoForIngredient(int id){
        return dao.findAllInfoForIngredient(id);
    }
}
