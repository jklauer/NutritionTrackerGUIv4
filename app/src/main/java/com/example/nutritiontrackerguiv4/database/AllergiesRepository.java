package com.example.nutritiontrackerguiv4.database;

import androidx.lifecycle.LiveData;

import java.util.List;

class AllergiesRepository {
    AllergiesDAO dao;
    public AllergiesRepository(AllergiesDAO d){
        dao = d;
    }

    public void addAllergies(Allergies a) {
        dao.insert(a);
    }

    public void updateAllergies(Allergies a){
        dao.update(a);
    }

    public void deleteAllergies(Allergies a){
        dao.delete(a);
    }

    public LiveData<List<Allergies>> getAllAllergies(){
        return dao.getAllAllergies();
    }

    //If you're looking for info on one user's allergies, use this method
    public LiveData<List<Allergies>> findAllInfoForAllergies(int id){
        return dao.findAllInfoForAllergies(id);
    }

}
