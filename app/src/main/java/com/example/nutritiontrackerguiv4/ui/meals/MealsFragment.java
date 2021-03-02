package com.example.nutritiontrackerguiv4.ui.meals;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.nutritiontrackerguiv4.InputMealForm;
import com.example.nutritiontrackerguiv4.R;

public class MealsFragment extends Fragment {

    private MealsViewModel mealsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mealsViewModel =
                new ViewModelProvider(this).get(MealsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_meals, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        mealsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        Button InputMealFormButton = (Button)root.findViewById(R.id.InputMealForm_button);
        InputMealFormButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loadInputMealForm = new Intent(getActivity(), InputMealForm.class);
                startActivity(loadInputMealForm);
            }
        });


        return root;
    }
}