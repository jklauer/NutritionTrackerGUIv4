package com.example.nutritiontrackerguiv4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nutritiontrackerguiv4.database.Ingredient;
import com.example.nutritiontrackerguiv4.database.NutritionDatabase;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

public class BarcodeScanner extends AppCompatActivity{
    private NutritionDatabase db;
    private Ingredient ingr;
    String barcode; //barcode == true; means updating a meal
                    //barcode == false; means adding a meal

    String ingr_time, ingr_name, ingr_calories, ingr_tofat, ingr_sfat, ingr_trfat, ingr_chol, ingr_sod, ingr_carb, ingr_fiber, ingr_sugar, ingr_prot, ingr_calc, ingr_potas, ingr_vitb6, ingr_vitc, ingr_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_scanner);

        String ingr_id = getIntent().getStringExtra("ingr_id"); //pass in an ingredient
        barcode = getIntent().getStringExtra("barcode"); //pass in barcode == true or false

        db = NutritionDatabase.getDatabase(getApplicationContext());

        //try to get the ingredient object from the given ingr_id
        try{
            ingr = db.getIngredientDAO().findAllInfoForIngredient(Long.parseLong(ingr_id)).get(0);
        }catch(NumberFormatException e){
            ingr = new Ingredient("", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,  java.text.DateFormat.getTimeInstance().format(Calendar.getInstance().getTime()),java.text.DateFormat.getDateInstance().format(Calendar.getInstance().getTime()));
        }

        //set up barcode scanner
        initialiseDetectorsAndSources();
    }

    //sets up the barcod scanner
    private void initialiseDetectorsAndSources() {

        //Toast.makeText(getApplicationContext(), "Barcode scanner started", Toast.LENGTH_SHORT).show();

        BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.ALL_FORMATS)
                .build();

        CameraSource cameraSource = new CameraSource.Builder(this, barcodeDetector)
                .setRequestedPreviewSize(1920, 1080)
                .setAutoFocusEnabled(true) //you should add this feature
                .build();
        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
                surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        cameraSource.start(surfaceView.getHolder());
                    } else {
                        ActivityCompat.requestPermissions(BarcodeScanner.this, new
                                String[]{Manifest.permission.CAMERA}, 201);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
                // Toast.makeText(getApplicationContext(), "To prevent memory leaks barcode scanner has been stopped", Toast.LENGTH_SHORT).show();
            }

            //when a barcode is read, this function is ran
            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();

                //if there is a barcode...
                if (barcodes.size() != 0) {
                    //and the barcode is not null
                    if (barcodes.valueAt(0).rawValue != null) {
                        System.out.println(barcodes.valueAt(0).rawValue);

                        //get the string result from the scanned upc
                        String result = SearchForFoodItemAPI.searchForFoodItemUPC(barcodes.valueAt(0).rawValue);

                        //get the name and calories into variables of the string result
                        ingr_name = result.split("###")[0];
                        ingr_id = Long.toString(ingr.getIngredient_ID());
                        ingr_calories = result.split("###")[1];
                        if (ingr_calories.contains(".")) {
                            System.out.println("Calories: " + ingr_calories);
                            ingr_calories = ingr_calories.split("\\.")[0];
                        }
                        ingr_vitb6 = Integer.toString(ingr.getVitaminA());
                        ingr_vitc = Integer.toString(ingr.getVitaminC());
                        ingr_time = ingr.getTime();

                        barcodeToXML();

                        //load input meal form with the scanned information and old other information
                        //  and the barcode == true or false value loaded in on activity startup
                        Intent loadInputMealForm = new Intent(getApplicationContext(), InputMealForm.class);
                        loadInputMealForm.putExtra("ingr_time", ingr_time);
                        loadInputMealForm.putExtra("ingr_name", ingr_name);
                        loadInputMealForm.putExtra("ingr_calories", ingr_calories);
                        loadInputMealForm.putExtra("ingr_tofat", ingr_tofat);
                        loadInputMealForm.putExtra("ingr_sfat", ingr_sfat);
                        loadInputMealForm.putExtra("ingr_trfat", ingr_trfat);
                        loadInputMealForm.putExtra("ingr_chol", ingr_trfat);
                        loadInputMealForm.putExtra("ingr_sod", ingr_trfat);
                        loadInputMealForm.putExtra("ingr_carb", ingr_trfat);
                        loadInputMealForm.putExtra("ingr_fiber", ingr_fiber);
                        loadInputMealForm.putExtra("ingr_sugar", ingr_sugar);
                        loadInputMealForm.putExtra("ingr_prot", ingr_prot);
                        loadInputMealForm.putExtra("ingr_calc", ingr_calc);
                        loadInputMealForm.putExtra("ingr_potas", ingr_potas);
                        loadInputMealForm.putExtra("ingr_vitb6", ingr_vitb6);
                        loadInputMealForm.putExtra("ingr_vitc", ingr_vitc);
                        loadInputMealForm.putExtra("barcode", barcode);
                        ingr_id = getIntent().getStringExtra("ingr_id");

                        startActivity(loadInputMealForm);
                    }

                }
            }
        });
    }

    public void barcodeToXML() {
        try {
            AssetManager assetManager = getAssets();
            InputStream fin = assetManager.open("FOODLarge.xls");
            POIFSFileSystem myFileSystem = new POIFSFileSystem(fin);
            HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);
//                    int upperBound = 'A' - 1;
//                    int inChar = input.charAt(0);
//                    int sheetNum = inChar-upperBound;
            int sheetNum = 0;
            //System.out.println("Input Char: " + input.charAt(0) + " = " + inChar + "\tUpper Bound: " + upperBound + "\tSheet Number: " + sheetNum);
            Row item = searchSheet(myWorkBook.getSheetAt(sheetNum), ingr_name.toString().toUpperCase());
            if(item != null) {
                System.out.println("found on first search");
                //1:description 3:calories 4:protein 5:total fat 7:carbohydrates 8:fiber, 9:sugar, 10:calcium, 14:potassium, 15:sodium 20:vitamin c, 25:vitamin b6, 32:vitamin a 44:saturated fat 47:cholesterol
                int[] colNums= {1,3,5,44,47,7,8,9,4,10,14,25,20,15};
                String[] editTexts = {ingr_name,ingr_calories,ingr_tofat,ingr_sfat,ingr_chol,ingr_carb,ingr_fiber,ingr_sugar,ingr_prot,ingr_calc,ingr_potas,ingr_vitb6,ingr_vitc,ingr_sod};
                for(int i=0; i<colNums.length; ++i) {

                    String result = (item.getCell(colNums[i]).toString()).split("\\.")[0];
                    if(result.isEmpty() || result == null){
                        editTexts[i]="0";
                        if(colNums[i] == 51){
                            editTexts[i]=(item.getCell(49).toString()).split("\\.")[0];
                        }
                    }else{
                        editTexts[i]=(result);
                    }



                }
                ingr_trfat=("0");
            }
            fin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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


