package com.bijesh.trimblepark.dependency_injection;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        SharedPreferencesModule.class,
        FirebaseModule.class
        })
public interface TrimbleAppComponent extends IAppComponent {
}
