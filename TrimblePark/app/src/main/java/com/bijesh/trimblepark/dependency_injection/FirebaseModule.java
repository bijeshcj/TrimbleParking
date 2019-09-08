package com.bijesh.trimblepark.dependency_injection;


import android.content.Context;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class FirebaseModule {


    private Context context;
    private FirebaseAuth mAuth;

    public FirebaseModule(Context context){
        this.context = context;
    }

    @Provides
    @Singleton
    public FirebaseAuth provideFirebaseAuth(){
        return getFirebaseAuth();
    }

    public FirebaseAuth getFirebaseAuth(){
        FirebaseApp.initializeApp(this.context);
        mAuth = FirebaseAuth.getInstance();
        return mAuth;
    }

}
