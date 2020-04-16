package com.manishkpr.exercise.util;

import android.os.Bundle;
import android.os.Parcelable;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.manishkpr.exercise.R;

import java.io.Serializable;


public class FragmentStackManager {
    FragmentActivity fragmentActivity;

    public FragmentStackManager(FragmentActivity fragmentActivity) {
        this.fragmentActivity = fragmentActivity;
    }

    FragmentTransaction ft;

    public void addFragment(Fragment fragment, boolean backStack, String backStackTag){
        addFragment(fragment, R.id.root_fragment, backStack, backStackTag, FragmentTransaction.TRANSIT_NONE,false);
    }

    public void addFragment(Fragment fragment, int id, boolean addToBackStack, String backStackTag, int transition, boolean want_animation) {
        ft = fragmentActivity.getSupportFragmentManager().beginTransaction();

        if(want_animation){
            //ft.setCustomAnimations( R.anim.enter_from_right, R.anim.exit_to_left,R.anim.enter_from_left, R.anim.exit_to_right);
        }

        ft.replace(id, fragment);
        ft.setTransition(transition);
        if (addToBackStack)
            ft.addToBackStack(backStackTag);
        ft.commit();
    }

    public static Bundle putBundle(Parcelable object, String name){
        Bundle bundle = new Bundle();
        bundle.putParcelable(name, object);
        return bundle;
    }
}
