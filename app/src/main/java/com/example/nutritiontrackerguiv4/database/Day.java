package com.example.nutritiontrackerguiv4.database;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity(tableName = "Days", foreignKeys =
        {
         @ForeignKey(entity = User.class,
                 parentColumns = "User_ID",
                 childColumns = "User_ID",
                 onDelete = ForeignKey.CASCADE),
        }
        )
public class Day {
    /*Primary keys should be random and completely independent of the semantics of the entity.
    so I set it to auto-generate */
    @PrimaryKey(autoGenerate = true)
    private long Day_ID;
    private long User_ID;
    private Date date;

    public Day(long id, long u, Date d)
    {
        Day_ID = id;
        User_ID = u;
        date = d;
    }


}