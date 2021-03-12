package com.example.nutritiontrackerguiv4.ui.meals;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.nutritiontrackerguiv4.InputMealForm;
import com.example.nutritiontrackerguiv4.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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

        try {
            loadButtons(root);
        } catch (IOException e) {
            e.printStackTrace();
        }


        Button InputMealFormButton = (Button)root.findViewById(R.id.InputMealForm_button);
        InputMealFormButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //loadButtons(root);

                Intent loadInputMealForm = new Intent(getActivity(), InputMealForm.class);
                startActivity(loadInputMealForm);


            }
        });







        return root;
    }

    public void loadButtons(View root) throws IOException {

        ArrayList<String> buttonNames = new ArrayList<String>();
        ArrayList<String> timeNames = new ArrayList<String>();

        BufferedReader br = new BufferedReader(new FileReader(new File(getActivity().getFilesDir(), "userMealData.txt")));
        int numOfNames = 0;
        String line = "";
        while(line != null){
            for(int i=0; i<6; i++){
                line = br.readLine();
            }
            numOfNames++;
        }
        br.close();
        System.out.println("Num of meals: " + numOfNames);
        BufferedReader br2 = new BufferedReader(new FileReader(new File(getActivity().getFilesDir(), "userMealData.txt")));
        for(int i=0; i<numOfNames-1; i++){
            for(int j=0; j<6; j++){
                line = br2.readLine();
                if(j==2){
                    buttonNames.add(line);
                }
                if(j==1){
                    timeNames.add(line);
                }
            }
        }
        for(int i=0; i<buttonNames.size(); i++){
            System.out.println(buttonNames.get(i));
            LinearLayout ll = (LinearLayout)root.findViewById(R.id.fragment_meals_linear_layout);
            Button btn = new Button(getContext());
            btn.setText(buttonNames.get(i) + " : " + timeNames.get(i));
            btn.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            ll.addView(btn);
        }





    }
}