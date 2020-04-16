package com.manishkpr.exercise.injection.module;

import android.app.Activity;
import android.content.Context;

import com.manishkpr.exercise.injection.ActivityContext;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    Activity provideActivity() {
        return this.activity;
    }

    @Provides
    @ActivityContext
    Context providesContext() {
        return this.activity;
    }
}
