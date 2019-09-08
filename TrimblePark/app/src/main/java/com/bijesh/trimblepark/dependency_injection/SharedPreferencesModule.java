package com.bijesh.trimblepark.dependency_injection;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

@Module
public class SharedPreferencesModule {
    private Context context;

    public SharedPreferencesModule(Context context) {
        this.context = context;
    }

    public SharedPreferences getSharedPreferences() {
        return getDefaultSharedPreferences(context);
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences() {
        return getSharedPreferences();
    }
}
