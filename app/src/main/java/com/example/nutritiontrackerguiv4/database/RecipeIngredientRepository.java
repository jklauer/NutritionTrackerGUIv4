package com.example.nutritiontrackerguiv4.database;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RecipeIngredientRepository {
    RecipeIngredientRepository dao;

    public RecipeIngredientRepository(RecipeIngredientRepository r){
        dao = r;
    }

    public List<Ingredient> getIngredientsForRecipe(long id){
        return dao.getIngredientsForRecipe(id);
    }
}
