package com.example.nutritiontrackerguiv4.database;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Users", foreignKeys =
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
    private long User_ID;
    private long Allergy_ID;
    private String name;

    public User(){}

    public User(long A, String n) {
        this.Allergy_ID = A;
        this.name = n;
    }

    public long getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(long user_id) {
        User_ID = user_id;
    }

    public long getAllergy_ID() {
        return Allergy_ID;
    }

    public void setAllergy_ID(long allergy_ID) {
        Allergy_ID = allergy_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
