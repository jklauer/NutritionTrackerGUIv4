package com.example.nutritiontrackerguiv4.ui.dashboard;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.nutritiontrackerguiv4.InputMealForm;
import com.example.nutritiontrackerguiv4.R;

import java.util.LinkedList;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    private class Tuple {
        public String name = "";
        public int value = 0;
        public Tuple(String name, int value) {
            this.name = name;
            this.value = value;
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
//        final TextView textView = root.findViewById(R.id.text_dashboard);
//        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        LinkedList<Tuple> arr = new LinkedList<Tuple>();

        //test items
        arr.add(new Tuple("Calories", 1980));
        arr.add(new Tuple("Vitamin A", 456));
        arr.add(new Tuple("Protein", 154));

        LinearLayout l = (LinearLayout) root.findViewById(R.id.dashboardLinearLayout);

        for(Tuple i : arr) {
            String str = i.name + ": " + i.value;
            TextView text = new TextView(getContext());
            text.setText(str);
            text.setGravity(Gravity.CENTER_HORIZONTAL);
            text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24f);
            text.setTextColor(Color.BLACK);
            l.addView(text);
        }

        return root;
    }
}