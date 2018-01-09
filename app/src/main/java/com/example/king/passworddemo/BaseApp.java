package com.example.king.passworddemo;

import android.app.Application;
import android.util.Log;

import com.example.king.passworddemo.utils.LockUtils;
import com.takwolf.android.repause.Repause;

/**
 * Created by king on 12/26/17.
 */

public class BaseApp extends Application {
    private final static String TAG = "testDemo";
    private LockUtils unlockUtils;
    private static BaseApp baseApp;

    @Override
    public void onCreate() {
        super.onCreate();
        baseApp = this;
        unlockUtils = new LockUtils(this);

    }

    public static BaseApp getInstance() {
        return baseApp;
    }


}
