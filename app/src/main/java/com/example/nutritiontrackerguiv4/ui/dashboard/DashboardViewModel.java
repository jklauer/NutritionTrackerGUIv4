package com.example.nutritiontrackerguiv4.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<String> currCal;

    public DashboardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public MutableLiveData<String> getCal() {
        if (currCal == null) {
            currCal = new MutableLiveData<String>();
        }
        return currCal;
    }
}