package com.example.king.passworddemo.utils;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import com.example.king.passworddemo.FingerLockActivity;
import com.example.king.passworddemo.GestureUnLockActivity;
import com.takwolf.android.repause.Repause;

import static android.content.ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN;

/**
 * Created by king on 12/27/17.
 * 解锁工具类
 */

public class LockUtils implements Repause.Listener{
    private final static String TAG = "UnlockUtils";
    private Application context;
    public LockUtils(Application context){
        this.context = context;
        Repause.init(context);
        Repause.registerListener(this);
    }



    /**
     * 检查锁
     * */
    private void checkLock(){
        //检查是否设置了锁
        if(LockModel.isLocked()){
            Log.i(TAG, "应用设置了锁");
            switch (LockModel.getLockType()){
                case FINGER:
                    Log.i(TAG, "启动指纹密码解锁界面");
                    Intent intent = new Intent(context,FingerLockActivity.class);
                    intent.putExtra("type","verify");
                    context.startActivity(intent);
                    break;
                case GESTURE:
                    Log.i(TAG, "启动手势密码解锁界面");
                    context.startActivity(new Intent(context,GestureUnLockActivity.class));
                    break;
            }
        }else{
            Log.i(TAG, "应用没有设置锁");
        }


    }


    @Override
    public void onApplicationResumed() {
        //应用已经恢复
        Log.e(TAG,"应用已经恢复");
        checkLock();
    }

    @Override
    public void onApplicationPaused() {
        //应用已暂停＋
        Log.e(TAG,"应用已经暂停");
    }
}
