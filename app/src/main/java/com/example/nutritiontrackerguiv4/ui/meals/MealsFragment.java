package com.example.nutritiontrackerguiv4.ui.meals;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.nutritiontrackerguiv4.GlobalMethods;
import com.example.nutritiontrackerguiv4.InputMealForm;
import com.example.nutritiontrackerguiv4.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

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

        String line = "";
        int numOfNames = GlobalMethods.getNumberOfMeals(this);
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
        br2.close();
        for(int i=0; i<buttonNames.size(); i++){
            //System.out.println(buttonNames.get(i));
            LinearLayout ll = (LinearLayout)root.findViewById(R.id.fragment_meals_linear_layout);
            Button btn = new Button(getContext());
            btn.setText(buttonNames.get(i) + " - " + timeNames.get(i));
            btn.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            btn.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {


                    return true;
                }
            });

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String info = btn.getText().toString();
                    String mealName = info.split(" - ")[0];
                    String mealTime = info.split(" - ")[1];
                    String mealDate = java.text.DateFormat.getDateInstance().format(Calendar.getInstance().getTime());
                    String mealCalories = "";
                    String mealVitaminA = "";
                    String mealVitaminC = "";




                    try {
                        BufferedReader br = new BufferedReader(new FileReader(new File(getActivity().getFilesDir(), "userMealData.txt")));
                        String line = br.readLine();
                        while(line != null && !line.isEmpty()){
                            if(line.equals(mealDate)){
                                line = br.readLine();
                                if(line.equals(mealTime)){
                                    line = br.readLine();
                                    if(line.equals(mealName)){
                                        mealCalories = br.readLine();
                                        mealVitaminA = br.readLine();
                                        mealVitaminC = br.readLine();
                                        break;
                                    }
                                }
                            }
                            line = br.readLine();
                        }
                        br.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    System.out.println("Meal name: "+mealName);
                    System.out.println("Meal time: "+mealTime);
                    System.out.println("Meal date: "+mealDate);
                    System.out.println("Meal calories: "+mealCalories);
                    System.out.println("Meal vit a: "+mealVitaminA);
                    System.out.println("Meal Vit c: "+mealVitaminC);

                    ArrayList<String> data = new ArrayList<String>();
                    data.add(mealDate);
                    data.add(mealTime);
                    data.add(mealName);
                    data.add(mealCalories);
                    data.add(mealVitaminA);
                    data.add(mealVitaminC);



                    Intent loadInputMealForm = new Intent(getActivity(), InputMealForm.class);
                    loadInputMealForm.putExtra("data", data);
                    startActivity(loadInputMealForm);
                }
            });
            ll.addView(btn);
        }

    }

}