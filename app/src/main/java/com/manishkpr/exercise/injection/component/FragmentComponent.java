package com.manishkpr.exercise.injection.component;

import com.manishkpr.exercise.injection.PerFragment;
import com.manishkpr.exercise.injection.module.FragmentModule;
import com.manishkpr.exercise.views.fragments.article.ArticleFragment;
import com.manishkpr.exercise.views.fragments.article_detail.ArticleDetailFragment;


import dagger.Subcomponent;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerFragment
@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {
    void inject(ArticleDetailFragment testFragment);
    void inject(ArticleFragment articleFragment);
}
