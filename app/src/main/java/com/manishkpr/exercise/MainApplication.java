package com.manishkpr.exercise;

import android.app.Application;
import android.content.Context;


import com.manishkpr.exercise.injection.component.ApplicationComponent;

import com.manishkpr.exercise.injection.component.DaggerApplicationComponent;
import com.manishkpr.exercise.injection.module.ApplicationModule;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;


public class MainApplication extends Application {

    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Logger.addLogAdapter(new AndroidLogAdapter());
        }
    }

    public ApplicationComponent getComponent() {
        if (applicationComponent == null) {
            applicationComponent = DaggerApplicationComponent.builder()
               .applicationModule(new ApplicationModule(this))
                 .build();
        }
        return applicationComponent;
    }

    public static MainApplication get(Context context) {
        return (MainApplication) context.getApplicationContext();
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        applicationComponent = applicationComponent;
    }
}
