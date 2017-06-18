package com.cabbage.scaffold;

import android.app.Application;

import timber.log.Timber;

public final class ScaffoldApplicaton extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new ForestFire());
    }
}
