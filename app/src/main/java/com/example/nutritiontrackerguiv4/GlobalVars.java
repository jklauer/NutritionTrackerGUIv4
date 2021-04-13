package com.example.nutritiontrackerguiv4;

import android.app.Application;

public class GlobalVars extends Application {
    private String channelID = "0";

    public String getChannelID() {
        return channelID;
    }

    public void setChannelID(String in) {
        this.channelID = in;
    }

    public static int cID = 0;
}
