package com.example.nutritiontrackerguiv4.database;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserRepository {
    UserDAO dao;

    public UserRepository(UserDAO d){
        dao = d;
    }

    public void addRecipe(User u){
        dao.insert(u);
    }

    public void updateRecipe(User u){
        dao.update(u);
    }

    public void deleteRecipe(User u){
        dao.delete(u);
    }

    public List<User> getAllUsers(){
        return dao.getAllUsers();
    }

    public List<User> findAllInfoForUser(long id){
        return dao.findAllInfoForUser(id);
    }
}
