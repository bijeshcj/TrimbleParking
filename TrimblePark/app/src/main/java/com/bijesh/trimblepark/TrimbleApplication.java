package com.bijesh.trimblepark;

import android.app.Application;

import androidx.multidex.MultiDexApplication;

import com.bijesh.trimblepark.dependency_injection.DaggerTrimbleAppComponent;
import com.bijesh.trimblepark.dependency_injection.FirebaseModule;
import com.bijesh.trimblepark.dependency_injection.IAppComponent;
import com.bijesh.trimblepark.dependency_injection.SharedPreferencesModule;


public class TrimbleApplication extends MultiDexApplication {

    public static IAppComponent iAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferencesModule preferencesModule = new SharedPreferencesModule(getApplicationContext());
        FirebaseModule firebaseModule = new FirebaseModule(getApplicationContext());
        iAppComponent = DaggerTrimbleAppComponent.builder().sharedPreferencesModule(preferencesModule)
                .firebaseModule(firebaseModule).build();
        iAppComponent.inject(this);
    }
}
