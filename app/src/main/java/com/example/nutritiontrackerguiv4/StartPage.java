package com.example.nutritiontrackerguiv4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.Buffer;

public class StartPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        loadInformation();
        handleContinueButton();

    }

    public void loadInformation(){

        File fileStartPage = new File(getFilesDir(), "fileStartPage.txt");
        if(fileStartPage.exists()){
            try {
                BufferedReader br = new BufferedReader(new FileReader(fileStartPage));
                String line;
                line = br.readLine();
                ((EditText)findViewById(R.id.username_input_Start_Page)).setText(line);
                line = br.readLine();
                if(line.equals("T")){
                    ((CheckBox)findViewById(R.id.allergy1_start_page)).setChecked(true);
                }else{
                    ((CheckBox)findViewById(R.id.allergy1_start_page)).setChecked(false);
                }
                line = br.readLine();
                if(line.equals("T")){
                    ((CheckBox)findViewById(R.id.allergy2_start_page)).setChecked(true);
                }else{
                    ((CheckBox)findViewById(R.id.allergy2_start_page)).setChecked(false);
                }
                line = br.readLine();
                if(line.equals("T")){
                    ((CheckBox)findViewById(R.id.allergy3_start_page)).setChecked(true);
                }else{
                    ((CheckBox)findViewById(R.id.allergy3_start_page)).setChecked(false);
                }
                br.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void handleContinueButton(){
        Button ContinueButton = (Button) findViewById(R.id.continue_button);
        ContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File fileStartPage = new File(getFilesDir(), "fileStartPage.txt");
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(fileStartPage));
                    if( ((EditText)findViewById(R.id.username_input_Start_Page)).getText().toString().isEmpty()){
                        bw.write("Name");
                    }else{
                        bw.write(((EditText)findViewById(R.id.username_input_Start_Page)).getText().toString());
                    }
                    bw.newLine();
                    if( ((CheckBox)findViewById(R.id.allergy1_start_page)).isChecked()){
                        bw.write("T");
                    }else{
                        bw.write("F");
                    }
                    bw.newLine();
                    if( ((CheckBox)findViewById(R.id.allergy2_start_page)).isChecked()){
                        bw.write("T");
                    }else{
                        bw.write("F");
                    }
                    bw.newLine();
                    if( ((CheckBox)findViewById(R.id.allergy3_start_page)).isChecked()){
                        bw.write("T");
                    }else{
                        bw.write("F");
                    }
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Intent loadApp = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(loadApp);
            }
        });
    }


}