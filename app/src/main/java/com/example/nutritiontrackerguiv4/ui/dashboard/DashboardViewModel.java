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

    public DashboardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

}