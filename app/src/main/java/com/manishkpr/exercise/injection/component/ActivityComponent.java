package com.manishkpr.exercise.injection.component;

import com.manishkpr.exercise.MainActivity;
import com.manishkpr.exercise.injection.PerActivity;
import com.manishkpr.exercise.injection.module.ActivityModule;

import dagger.Subcomponent;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity mainActivity);
}
