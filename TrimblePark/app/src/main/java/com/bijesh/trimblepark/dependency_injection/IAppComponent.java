package com.bijesh.trimblepark.dependency_injection;


import com.bijesh.trimblepark.TrimbleApplication;
import com.bijesh.trimblepark.ui.BaseActivity;
import com.bijesh.trimblepark.ui.BaseFragment;

public interface IAppComponent {

    void inject(TrimbleApplication app);

    void inject(BaseActivity baseActivity);

    void inject(BaseFragment baseFragment);


}
