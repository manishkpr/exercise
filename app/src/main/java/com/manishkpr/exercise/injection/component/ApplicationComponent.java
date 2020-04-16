package com.manishkpr.exercise.injection.component;

import android.app.Application;
import android.content.Context;

import com.manishkpr.exercise.data.local.PreferencesHelper;
import com.manishkpr.exercise.data.remote.BackendService;
import com.manishkpr.exercise.injection.ApplicationContext;
import com.manishkpr.exercise.injection.module.ApplicationModule;
import com.manishkpr.exercise.util.RxEventBus;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    @ApplicationContext
    Context context();
    Application application();
    BackendService backendService();
    RxEventBus eventBus();
    PreferencesHelper preferencesHelper();
}
