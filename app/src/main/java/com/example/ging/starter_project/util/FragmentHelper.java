package com.example.ging.starter_project.util;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by ging-windows on 2/5/2018.
 */

public class FragmentHelper {

    FragmentManager fm;
    public static FragmentHelper self;

    public FragmentHelper(Context context) {
        fm = ((AppCompatActivity) context).getSupportFragmentManager();
        self = this;
    }

    public static void changeFragment(int resId, Fragment fragment){
        changeFragment(self.fm ,resId,fragment);
    }

    public static void changeFragment (FragmentManager fm, int resId, Fragment fragment) {
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(resId, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }


}


