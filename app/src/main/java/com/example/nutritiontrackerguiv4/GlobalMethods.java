package com.example.nutritiontrackerguiv4;

import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GlobalMethods {
    public static int getNumberOfMeals(Fragment f) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File(f.getActivity().getFilesDir(), "userMealData.txt")));
        int numOfNames = 0;
        String line = "";
        while(line != null){
            for(int i=0; i<6; i++){
                line = br.readLine();
            }
            numOfNames++;
        }
        br.close();

        return numOfNames;
    }
}
