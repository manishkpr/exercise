package com.manishkpr.exercise.views.base;

import android.app.Activity;
import android.content.Context;


import androidx.fragment.app.Fragment;

import com.manishkpr.exercise.MainApplication;
import com.manishkpr.exercise.R;
import com.manishkpr.exercise.injection.component.ConfigPersistentComponent;

import com.manishkpr.exercise.injection.component.DaggerConfigPersistentComponent;
import com.manishkpr.exercise.injection.component.FragmentComponent;
import com.manishkpr.exercise.injection.module.FragmentModule;


import android.os.Build;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;


/**
 * Abstract activity that every other Activity in this application must implement. It handles
 * creation of Dagger components and makes sure that instances of ConfigPersistentComponent survive
 * across configuration changes.
 */
public class BaseFragment extends Fragment {

    private FragmentComponent fragmentComponent;

    public @BindView(R.id.layout_progress)
    View layoutProgress;

    public @BindView (R.id.layout_error)
    View layout_error;



    public @BindView (R.id.article_error_msg)
    TextView article_error_msg;

    public void onAttach(Context context) {
        super.onAttach(context);
        onAttachToContext();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            onAttachToContext();
    }

    private void initDagger() {
        ConfigPersistentComponent configPersistentComponent;

        configPersistentComponent = DaggerConfigPersistentComponent.builder()
                .applicationComponent(MainApplication.get(getActivity()).getComponent())
                .build();

        fragmentComponent = configPersistentComponent.fragmentComponent(new FragmentModule(this));
    }


    public FragmentComponent getComponent() {
        return fragmentComponent;
    }

    protected void onAttachToContext() {
        initDagger();
    }

}
