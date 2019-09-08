package com.bijesh.trimblepark.ui;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bijesh.trimblepark.TrimbleApplication;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

public class BaseFragment extends Fragment {

    @Inject
    FirebaseAuth mAuth;

    @Inject
    SharedPreferences sharedPreferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TrimbleApplication.iAppComponent.inject(this);
    }
}
