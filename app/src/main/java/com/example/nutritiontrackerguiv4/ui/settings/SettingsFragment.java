package com.example.nutritiontrackerguiv4.ui.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.nutritiontrackerguiv4.InputMealForm;
import com.example.nutritiontrackerguiv4.NotificationSettingsActivity;
import com.example.nutritiontrackerguiv4.Notifications.CreateNotification;
import com.example.nutritiontrackerguiv4.R;
import com.example.nutritiontrackerguiv4.StartPage;

public class SettingsFragment extends Fragment {

    private SettingsViewModel settingsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingsViewModel =
                new ViewModelProvider(this).get(SettingsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);
//        final TextView textView = root.findViewById(R.id.text_notifications);
//        settingsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        Button GoToStartPageButton = (Button)root.findViewById(R.id.goToStartPage_button);
        GoToStartPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loadStartPage = new Intent(getActivity(), StartPage.class);
                startActivity(loadStartPage);
            }
        });

        Button notifications_button = (Button)root.findViewById(R.id.notifications_button);
        notifications_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent notificationSettings = new Intent(getActivity(), NotificationSettingsActivity.class);
                startActivity(notificationSettings);
                //CreateNotification.CreateNotificationAtTime(getContext(), "the title", "the body", 13,2,30);
                //CreateNotification.CreateNotificationWithDelay(getContext(), "the title", "the body", 5000);
            }
        });




        return root;
    }




}