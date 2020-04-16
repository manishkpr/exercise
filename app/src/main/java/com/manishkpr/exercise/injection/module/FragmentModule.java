package com.manishkpr.exercise.injection.module;

import androidx.fragment.app.Fragment;

import com.manishkpr.exercise.injection.ActivityContext;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {

    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    Fragment provideActivity() {
        return fragment;
    }

    @Provides
    @ActivityContext
    Fragment providesContext() {
        return fragment;
    }
}
