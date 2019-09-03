package com.example.footballscoreapps;

import android.app.Application;
import com.androidnetworking.AndroidNetworking;

public class JadwalApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
    }
}
