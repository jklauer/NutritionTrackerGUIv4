package com.example.nutritiontrackerguiv4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.nutritiontrackerguiv4.database.Ingredient;
import com.example.nutritiontrackerguiv4.database.NutritionDatabase;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class BarcodeScanner extends AppCompatActivity{
    private NutritionDatabase db;
    private Ingredient ingr;
    String barcode; //barcode == true; means updating a meal
                    //barcode == false; means adding a meal

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
                        String ingr_name = result.split("###")[0];
                        String ingr_id = Long.toString(ingr.getIngredient_ID());
                        String ingr_calories = result.split("###")[1];
                        String ingr_vita = Integer.toString(ingr.getVitaminA());
                        String ingr_vitc = Integer.toString(ingr.getVitaminC());
                        String ingr_time = ingr.getTime();

                        //load input meal form with the scanned information and old other information
                        //  and the barcode == true or false value loaded in on activity startup
                        Intent loadInputMealForm = new Intent(getApplicationContext(), InputMealForm.class);
                        loadInputMealForm.putExtra("ingr_name", ingr_name);
                        loadInputMealForm.putExtra("ingr_id", ingr_id);
                        loadInputMealForm.putExtra("ingr_calories", ingr_calories);
                        loadInputMealForm.putExtra("ingr_vita", ingr_vita);
                        loadInputMealForm.putExtra("ingr_vitc", ingr_vitc);
                        loadInputMealForm.putExtra("ingr_time", ingr_time);
                        loadInputMealForm.putExtra("barcode", barcode);
                        startActivity(loadInputMealForm);
                    }

                }
            }
        });
    }
}


