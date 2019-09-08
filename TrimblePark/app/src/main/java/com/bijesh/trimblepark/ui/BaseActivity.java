package com.bijesh.trimblepark.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bijesh.trimblepark.TrimbleApplication;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

public class BaseActivity extends AppCompatActivity {

    @Inject
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize Firebase Auth
//        FirebaseApp.initializeApp(this);
//        mAuth = FirebaseAuth.getInstance();
        TrimbleApplication.iAppComponent.inject(this);
    }

    protected String getUid() {
        return mAuth.getCurrentUser().getUid();
    }
}
