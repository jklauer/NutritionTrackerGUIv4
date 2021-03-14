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

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class BarcodeScanner extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_scanner);
        initialiseDetectorsAndSources();
    }

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

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();
                if (barcodes.size() != 0) {

                    if (barcodes.valueAt(0).rawValue != null) {
                        System.out.println(barcodes.valueAt(0).rawValue);
                        ArrayList<String> data = new ArrayList<String>();
                        String result = SearchForFoodItemAPI.searchForFoodItemUPC(barcodes.valueAt(0).rawValue);
                        String name = result.split("###")[0];
                        String calories = result.split("###")[1];
                        String date = java.text.DateFormat.getDateInstance().format(Calendar.getInstance().getTime());
                        String time = java.text.DateFormat.getTimeInstance().format(Calendar.getInstance().getTime());
                        String vitA = "";
                        String vitC = "";

                        data.add(date);
                        data.add(time);
                        data.add(name);
                        data.add(calories);
                        data.add(vitA);
                        data.add(vitC);

                        Intent loadInputMealForm = new Intent(getApplicationContext(), InputMealForm.class);
                        loadInputMealForm.putExtra("barcode", data);
                        startActivity(loadInputMealForm);
                    }

                }
            }
        });
    }
}


