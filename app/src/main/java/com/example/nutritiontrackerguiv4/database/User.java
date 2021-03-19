package com.example.nutritiontrackerguiv4.database;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Allergies", foreignKeys =
        {
         @ForeignKey(entity = Allergies.class,
         parentColumns = "Allergy_ID",
         childColumns = "Allergy_ID",
         onDelete = ForeignKey.CASCADE),
        })
public class User {
    /*Primary keys should be random and completely independent of the semantics of the entity.
    so I set it to auto-generate */
    @PrimaryKey(autoGenerate = true)
    private long User_id;
    private long Allergy_ID;
    private String name;

    public User(int id, long A, String n) {
        this.User_id = id;
        this.Allergy_ID = A;
        this.name = n;
    }
}
