package com.example.nutritiontrackerguiv4;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.nutritiontrackerguiv4.database.Allergies;
import com.example.nutritiontrackerguiv4.database.NutritionDatabase;
import com.example.nutritiontrackerguiv4.database.User;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class Test_DAO {

    private NutritionDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = NutritionDatabase.getDatabase(context);

    }

    @Test
    public void Allergies_insert() throws Exception {

        db.clearAllTables();

        int previousSize = db.getAllergiesDAO().getAllAllergies().size();
        Allergies allergies = new Allergies(true, true);
        db.getAllergiesDAO().insert(allergies);
        Assert.assertEquals(previousSize+1, db.getAllergiesDAO().getAllAllergies().size());
    }

    @Test
    public void Allergies_update() throws Exception {

        db.clearAllTables();

        //add an allergy to the db
        Allergies newAllergy = new Allergies(true, true);
        db.getAllergiesDAO().insert(newAllergy);

        //update the allergy in db
        Allergies allergies = db.getAllergiesDAO().getAllAllergies().get(0);
        allergies.setNuts(false);
        db.getAllergiesDAO().update(allergies);

        Assert.assertEquals(false, db.getAllergiesDAO().getAllAllergies().get(0).getNuts());
    }

    @Test
    public void Allergies_delete() throws Exception {

        db.clearAllTables();

        //add an allergy to the db
        Allergies newAllergy = new Allergies(false, true);
        db.getAllergiesDAO().insert(newAllergy);

        Allergies allergyToDelete = db.getAllergiesDAO().getAllAllergies().get(0);
        db.getAllergiesDAO().delete(allergyToDelete);

        try{
            db.getAllergiesDAO().getAllAllergies().get(0);
            Assert.assertTrue(false);
        }catch(Exception e){
            Assert.assertTrue(true);
        }
    }

    @Test
    public void Allergies_getAllAllergies() throws Exception {

        db.clearAllTables();

        //add an allergy to the db
        Allergies newAllergy = new Allergies(false, false);
        db.getAllergiesDAO().insert(newAllergy);

        //add another allergy to the db
        Allergies newAllergy2 = new Allergies(true, true);
        db.getAllergiesDAO().insert(newAllergy2);

        Assert.assertEquals(2, db.getAllergiesDAO().getAllAllergies().size());

    }

    @Test
    public void Allergies_findAllInfoForAllergies() throws Exception {

        db.clearAllTables();

        //add an allergy to the db
        Allergies newAllergy = new Allergies(false, false);
        db.getAllergiesDAO().insert(newAllergy);

        //get the allergy id just added to the db
        long allergies_id = db.getAllergiesDAO().getAllAllergies().get(0).getAllergy_ID();

        //try to get the allergy corresponding to the allergy_id
        Allergies allergyToCheck = db.getAllergiesDAO().findAllInfoForAllergies(allergies_id).get(0);

        Assert.assertEquals(newAllergy.getNuts(), allergyToCheck.getNuts());
        Assert.assertEquals(newAllergy.getSeafood(), allergyToCheck.getSeafood());

    }
}