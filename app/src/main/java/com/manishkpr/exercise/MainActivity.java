package com.manishkpr.exercise;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;

import com.manishkpr.exercise.util.FragmentStackManager;
import com.manishkpr.exercise.views.base.BaseActivity;
import com.manishkpr.exercise.views.fragments.article.ArticleFragment;



import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    public static FragmentStackManager fragmentStackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpView();
    }

    void setUpView() {
        activityComponent().inject(this);
        setContentView(R.layout.activity_main);
        fragmentStackManager = new FragmentStackManager(this);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.app_toolbar);
        setSupportActionBar(toolbar);
        getSupportFragmentManager().addOnBackStackChangedListener(mOnBackStackChangedListener);
        fragmentStackManager.addFragment(new ArticleFragment(), false, ArticleFragment.class.getSimpleName());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
