package com.example.nutritiontrackerguiv4;

import android.content.Context;
import android.os.Build;
import android.os.StrictMode;

import androidx.annotation.RequiresApi;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;

public class SearchForFoodItemAPI {


    //This function takes in a string query for a food (ex: "Big mac")
    //  and returns a string with the item name and number of calories
    //  in the food item searched for.
    //  returned string format: NAME###CALORIES
    public static String searchForFoodItem(String query){

        query.replace(" ", "%20");

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        URL url = null;
        try {
            url = new URL("https://api.nutritionix.com/v1_1/search/"+query+"?results=0:1&fields=item_name,brand_name,item_id,nf_calories&appId=e3c64a2d&appKey=8247cbcc6f4470b6b5aea7bb2b8e9a38");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            url.openStream()));


            String output = "";

            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                output += inputLine;
            }
            in.close();
            System.out.println(output);
            String name = "";
            String calories = "";
            output = output.split("item_name\":\"")[1];
            name = output.split("\",\"brand_name")[0];
            output = output.split("nf_calories\":")[1];
            calories = output.split(",")[0];
            return (name+"###"+calories);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Error";
    }
    public static String searchForFoodItemUPC(String upc_code){

        String app_id = "9e484caf";
        String app_key = "1fb6d2a874717ab34359b81e965cc4ad";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        URL url = null;
        try {
            url = new URL("https://api.edamam.com/api/food-database/v2/parser?upc="+upc_code+"&app_id="+app_id+"&app_key="+app_key);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            url.openStream()));


            String output = "";

            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                output += inputLine;
            }
            in.close();
            System.out.println(output);
            String name = "";
            String calories = "";
            output = output.split("label\" : \"")[1];
            name = output.split("\",")[0];
            output = output.split("ENERC_KCAL\" : ")[1];
            calories = output.split(",")[0];
            return (name+"###"+calories);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Error";
    }



}
