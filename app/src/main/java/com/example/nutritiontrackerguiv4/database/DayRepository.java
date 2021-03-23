package com.example.nutritiontrackerguiv4.database;

import androidx.lifecycle.LiveData;

import java.util.List;

public class DayRepository {

    DayDAO dao;

    public DayRepository(DayDAO d){
        dao = d;
    }

    public void addDay(Day d){
        dao.insert(d);
    }

    public void updateDay(Day d){
        dao.update(d);
    }

    public void deleteDay(Day d){
        dao.delete(d);
    }

    public List<Day> getAllDays(){
        return dao.getAllDays();
    }

    public List<Day> findAllInfoForDays(int id){
        return dao.findAllInfoForDays(id);
    }
}
