package com.example.nutritiontrackerguiv4.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.nutritiontrackerguiv4.database.Ingredient;
import com.example.nutritiontrackerguiv4.database.IngredientDAO;

import java.util.Date;
import java.text.SimpleDateFormat;

import java.util.List;
import java.util.Locale;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<String> mText;
//    private IngredientDAO ingDao;

    public DashboardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

//    public Integer loadCalories() {
//        Date today = new Date();
//        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
//        String todayFormatted = format.format(today).toString();
//        return ingDao.findCaloriesOnDay(todayFormatted).get(0);
//    }
//
//    public Integer loadVitA() {
//        Date today = new Date();
//        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
//        String todayFormatted = format.format(today).toString();
//        return ingDao.findVitAOnDay(todayFormatted).get(0);
//    }
//
//    public Integer loadVitC() {
//        Date today = new Date();
//        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
//        String todayFormatted = format.format(today).toString();
//        return ingDao.findVitCOnDay(todayFormatted).get(0);
//    }
//
//    public LiveData<String> getText() {
//        return mText;
//    }
}