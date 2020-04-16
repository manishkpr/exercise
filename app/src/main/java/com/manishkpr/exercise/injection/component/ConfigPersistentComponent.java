package com.manishkpr.exercise.injection.component;


import com.manishkpr.exercise.injection.ConfigPersistent;
import com.manishkpr.exercise.injection.module.ActivityModule;
import com.manishkpr.exercise.injection.module.FragmentModule;

import dagger.Component;

/**
 * A dagger component that will live during the lifecycle of an Activity but it won't
 * be destroy during configuration changes. Check {@link com.manishkpr.exercise.views.base.BaseActivity} to see how this components
 * survives configuration changes.
 * Use the {@link ConfigPersistent} scope to annotate dependencies that need to survive
 * configuration changes (for example Presenters).
 */
@ConfigPersistent
@Component(dependencies = ApplicationComponent.class)
public interface ConfigPersistentComponent {

    ActivityComponent activityComponent(ActivityModule activityModule);
    FragmentComponent fragmentComponent(FragmentModule fragmentModule);

}
