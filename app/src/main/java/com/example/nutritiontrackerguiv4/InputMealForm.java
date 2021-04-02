package com.example.nutritiontrackerguiv4;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.nutritiontrackerguiv4.R;
import com.example.nutritiontrackerguiv4.database.Ingredient;
import com.example.nutritiontrackerguiv4.database.NutritionDatabase;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

public class InputMealForm extends Activity {

    //Holds the variables of the Ingredient object
    private String date;
    private String time;
    private String name;
    private String calories;
    private String vitaminA;
    private String vitaminC;
    private String ingr_id;

    //Passed in from the BarcodeScanner activity...
    // barcode == true; means it came from BarcodeScanner and it's updating
    // barcode == false; means it came from BarcodeScanner and it's adding
    // barcode = null means it came from MealsFragment
    private String barcode;

    private NutritionDatabase db;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_meal_form);

        db = NutritionDatabase.getDatabase(getApplicationContext());

        //Get the passed in data if it came from another activity
        date = java.text.DateFormat.getDateInstance().format(Calendar.getInstance().getTime());
        time = getIntent().getStringExtra("ingr_time");
        name = getIntent().getStringExtra("ingr_name");
        calories = getIntent().getStringExtra("ingr_calories");
        vitaminA = getIntent().getStringExtra("ingr_vita");
        vitaminC = getIntent().getStringExtra("ingr_vitc");
        ingr_id = getIntent().getStringExtra("ingr_id");

        barcode = getIntent().getStringExtra("barcode");
        //System.out.println("Barcode boolean value: "+barcode);


        /*Search feature*/
        Button searchButton = (Button)findViewById(R.id.input_meal_form_search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    String input = ((EditText)findViewById(R.id.mealName)).getText().toString().toUpperCase();
                    AssetManager assetManager = getAssets();
                    InputStream fin = assetManager.open("FOOD.xls");
                    POIFSFileSystem myFileSystem = new POIFSFileSystem(fin);
                    HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);
//                    int upperBound = 'A' - 1;
//                    int inChar = input.charAt(0);
//                    int sheetNum = inChar-upperBound;
                    int sheetNum = 0;
                    //System.out.println("Input Char: " + input.charAt(0) + " = " + inChar + "\tUpper Bound: " + upperBound + "\tSheet Number: " + sheetNum);
                    Row item = searchSheet(myWorkBook.getSheetAt(sheetNum), input);
                    int[] eTexts = {R.id.mealName, R.id.caloriesEntry, R.id.vitaminC, R.id.vitaminA};
                    for(int i = 0; i < eTexts.length; ++i) {
                        if(i == 0) {
                            ((EditText)findViewById(eTexts[i])).setText("");
                        }
                        else ((EditText)findViewById(eTexts[i])).setText("");
                    }
                    if(item != null) {
                        System.out.println("found on first search");
                        //1:description 3:calories 4:protein 5:total fat 7:carbohydrates 15:sodium 20:vitamin c, 32:vitamin a 44:saturated fat 47:cholesterol
                        int[] colNums= {1,3, /*4,5,7,15,*/20, 32/*,44,47*/};
                        int[] editTexts = {R.id.mealName, R.id.caloriesEntry, R.id.vitaminC, R.id.vitaminA};
                        for(int i=0; i<colNums.length; ++i) {
                            ((EditText)findViewById(editTexts[i])).setText(item.getCell(colNums[i]).toString());
                        }
                    }
                    else {
//                        item = searchSheet(myWorkBook.getSheetAt(0), input);
//                        if(item != null) {
//                            System.out.println("found on second search");
//                            //1:description 3:calories 4:protein 5:total fat 7:carbohydrates 15:sodium 20:vitamin c, 32:vitamin a 44:saturated fat 47:cholesterol
//                            int[] colNums= {1,3, /*4,5,7,15,*/20, 32/*,44,47*/};
//                            int[] editTexts = {R.id.mealName, R.id.caloriesEntry, R.id.vitaminC, R.id.vitaminA};
//                            for(int i=0; i<colNums.length; ++i) {
//                                ((EditText)findViewById(editTexts[i])).setText(item.getCell(colNums[i]).toString());
//                            }
//                        }
//                        else {
                            String result = SearchForFoodItemAPI.searchForFoodItem(((EditText) findViewById(R.id.mealName)).getText().toString());
                            String name = result.split("###")[0];
                            String calories = result.split("###")[1];
                            if (calories.contains(".")) {
                                System.out.println("Calories: " + calories);
                                calories = calories.split("\\.")[0];
                            }
                            ((EditText) findViewById(R.id.mealName)).setText(name);
                            ((EditText) findViewById(R.id.caloriesEntry)).setText(calories);
                        }
//                    }
                    fin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        /*End of search feature*/

        //If it came in from the BarcodeScanner activity...
        if(barcode!=null){

            //And if it is updating a meal
            if(barcode.equals("true")){

                //Set up barcode scanner button
                Button barcode_button = (Button)findViewById(R.id.scan_barcode_button);
                barcode_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent loadBarcodeScanner = new Intent(getApplicationContext(), BarcodeScanner.class);

                        loadBarcodeScanner.putExtra("ingr_id", ingr_id);
                        loadBarcodeScanner.putExtra("barcode", "true");

                        startActivity(loadBarcodeScanner);
                    }
                });
                //End setting up barcode scanner button

                //System.out.println("Updating a meal...");


                //Set the input meal form UI to the current data
                ((EditText)findViewById(R.id.mealDate)).setText(date);
                ((EditText)findViewById(R.id.mealDate)).setEnabled(false);
                ((EditText)findViewById(R.id.mealTime)).setText(time);
                ((EditText)findViewById(R.id.mealName)).setText(name);
                ((EditText)findViewById(R.id.vitaminA)).setText(vitaminA);
                ((EditText)findViewById(R.id.vitaminC)).setText(vitaminC);
                ((EditText)findViewById(R.id.caloriesEntry)).setText(calories);

                update(); //handle if the meal is updated
                delete(); //handle if the meal is deleted
            }else
                //If it is adding a meal
                {

                //Setup the barcode scanner button
                Button barcode_button = (Button)findViewById(R.id.scan_barcode_button);
                barcode_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent loadBarcodeScanner = new Intent(getApplicationContext(), BarcodeScanner.class);

                        loadBarcodeScanner.putExtra("ingr_id", ingr_id);
                        loadBarcodeScanner.putExtra("barcode", "false");

                        startActivity(loadBarcodeScanner);
                    }
                });
                //end setting up the barcode scanner button

                //System.out.println("Adding a meal...");

                // Set the input meal form UI to the current data
                ((EditText)findViewById(R.id.mealDate)).setText(date);
                ((EditText)findViewById(R.id.mealDate)).setEnabled(false);
                ((EditText)findViewById(R.id.mealTime)).setText(time);
                ((EditText)findViewById(R.id.mealName)).setText(name);
                ((EditText)findViewById(R.id.vitaminA)).setText(vitaminA);
                ((EditText)findViewById(R.id.vitaminC)).setText(vitaminC);
                ((EditText)findViewById(R.id.caloriesEntry)).setText(calories);

                add(); //handle adding a meal
            }
        }else
            //If it came in from the meals fragment
            {

            // and if it is updating a meal
            if(
                    time!=null
                    &&name!=null
                    &&calories!=null
                    &&vitaminA!=null
                    &&vitaminC!=null
            ){

                //set up barcode scanner button
                Button barcode_button = (Button)findViewById(R.id.scan_barcode_button);
                barcode_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent loadBarcodeScanner = new Intent(getApplicationContext(), BarcodeScanner.class);

                        loadBarcodeScanner.putExtra("ingr_id", ingr_id);
                        loadBarcodeScanner.putExtra("barcode", "true");

                        startActivity(loadBarcodeScanner);
                    }
                });
                //end setting up barcode scanner button

                //System.out.println("Updating a meal...");

                //Set the input meal form UI to the current data
                ((EditText)findViewById(R.id.mealDate)).setText(date);
                ((EditText)findViewById(R.id.mealDate)).setEnabled(false);
                ((EditText)findViewById(R.id.mealTime)).setText(time);
                ((EditText)findViewById(R.id.mealName)).setText(name);
                ((EditText)findViewById(R.id.vitaminA)).setText(vitaminA);
                ((EditText)findViewById(R.id.vitaminC)).setText(vitaminC);
                ((EditText)findViewById(R.id.caloriesEntry)).setText(calories);

                update(); //handle updating meal
                delete(); //handle deleting meal
            }else
                //if it is adding a meal
                {

                //set up barcode scanner button
                Button barcode_button = (Button)findViewById(R.id.scan_barcode_button);
                barcode_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent loadBarcodeScanner = new Intent(getApplicationContext(), BarcodeScanner.class);

                        loadBarcodeScanner.putExtra("ingr_id", ingr_id);
                        loadBarcodeScanner.putExtra("barcode", "false");

                        startActivity(loadBarcodeScanner);
                    }
                });
                //end setting up barcode scanner button

                //System.out.println("Adding a meal...");

                //Set the input meal form UI to the current data
                ((EditText)findViewById(R.id.mealDate)).setText(date);
                ((EditText)findViewById(R.id.mealDate)).setEnabled(false);
                ((EditText)findViewById(R.id.mealTime)).setText(
                        java.text.DateFormat.getTimeInstance().format(Calendar.getInstance().getTime()));
                ((EditText)findViewById(R.id.mealName)).setText("");
                ((EditText)findViewById(R.id.vitaminA)).setText("");
                ((EditText)findViewById(R.id.vitaminC)).setText("");
                ((EditText)findViewById(R.id.caloriesEntry)).setText("");

                add(); //handle adding a meal
            }
        }


    }

    //updates a meal
    public void update(){

        //listener for "Enter meal Data" button
        ((Button)findViewById(R.id.enterMeal)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    //try to create a new ingredient with the same ingr_id, and update it in the database
                    Ingredient update_ingr = db.getIngredientDAO().findAllInfoForIngredient(Long.parseLong(ingr_id)).get(0);
                    update_ingr.setCalories(Integer.parseInt(((EditText)findViewById(R.id.caloriesEntry)).getText().toString()));
                    update_ingr.setName(((EditText)findViewById(R.id.mealName)).getText().toString());
                    update_ingr.setTime(((EditText)findViewById(R.id.mealTime)).getText().toString());
                    update_ingr.setVitaminA(Integer.parseInt(((EditText)findViewById(R.id.vitaminA)).getText().toString()));
                    update_ingr.setVitaminC(Integer.parseInt(((EditText)findViewById(R.id.vitaminC)).getText().toString()));
                    db.getIngredientDAO().update(update_ingr); //update in database

                    //load main activity
                    Intent loadApp = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(loadApp);
                }catch(NumberFormatException e){
                    //if there was a problem in the formatting on the input meal form
                    System.out.println("Invalid formatting...");
                }




            }
        });

    }

    //deletes a meal
    public void delete(){

        //listener for delete button
        Button deleteButton = new Button(this);
        deleteButton.setText("Delete");
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //delete the meal with the given ingr_id
                Ingredient ingr_delete = db.getIngredientDAO().findAllInfoForIngredient(Long.parseLong(ingr_id)).get(0);
                db.getIngredientDAO().delete(ingr_delete);
                Intent loadApp = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(loadApp);
            }
        });
        //dynamically add the delete button to the layout if it is updating a meal
        ConstraintLayout ll = (ConstraintLayout) findViewById(R.id.input_meal_form_delete_button_layout);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        ll.addView(deleteButton, lp);
    }

    //adds a meal
    public void add(){

        //on click listener for the add meal button
        ((Button)findViewById(R.id.enterMeal)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //System.out.println("Button clicked!");
                try{
                    //try to create a new ingredient and add it to the database
                    Ingredient add_ingr = new Ingredient(
                            ((EditText)findViewById(R.id.mealName)).getText().toString(),
                            Integer.parseInt(
                                    ((EditText)findViewById(R.id.caloriesEntry)).getText().toString()),
                            Integer.parseInt(
                                    ((EditText)findViewById(R.id.vitaminA)).getText().toString()),
                            Integer.parseInt(
                                    ((EditText)findViewById(R.id.vitaminC)).getText().toString()),
                            ((EditText)findViewById(R.id.mealTime)).getText().toString());
                    db.getIngredientDAO().insert(add_ingr); //add the ingredient to the database

                    //load the main activity
                    Intent loadApp = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(loadApp);
                }catch(NumberFormatException e){
                    //if there was invalid formatting on the input meal form
                    System.out.println("Invalid formatting...");
                }



            }
        });

    }

    public Row searchSheet(HSSFSheet mySheet, String input) throws IOException {
        Iterator<Row> rowIter = mySheet.rowIterator();
        String[] tokInput = input.split("[\\p{Punct}\\s]+");;
        boolean found = false;
        Row item = null;
        Row best = null;
        int bestCount = 0;
        int currentCount = 0;
        while (rowIter.hasNext() && !found) {
            currentCount = 0;
            item = rowIter.next();
            String[] currentDescription = item.getCell(1).toString().split("[\\p{Punct}\\s]+");
            String description = item.getCell(1).toString();
            for(String i:tokInput) {
                if(!description.contains(i)) {
                    found = false;
                    break;
                }
                else {
                    if(description.equals(i)) {
                        currentCount += 5;
                    }
                    found = true;
                    ++currentCount;
                }
            }
            if(currentCount > bestCount) {
                bestCount = currentCount;
                best = item;
            }
        }
        return best;
    }
}