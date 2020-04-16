package com.manishkpr.exercise.injection.module;

import android.app.Application;
import android.content.Context;

import com.manishkpr.exercise.data.remote.BackendService;
import com.manishkpr.exercise.injection.ApplicationContext;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Provide application-level dependencies.
 */
@Module
public class ApplicationModule {
    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    BackendService provideRibotsService() {
        return BackendService.Creator.newBackendService();
    }
}
