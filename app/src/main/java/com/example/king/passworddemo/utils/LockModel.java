package com.example.king.passworddemo.utils;

import android.util.Log;

/**
 * Created by king on 12/27/17.
 */

public class LockModel {
    private final static String TAG = "UnlockUtils";
    public enum LockType {
        GESTURE("GESTURE"), FINGER("FINGER");//手势密码和指纹密码

        private final String vlaue;
        LockType(String value){
            this.vlaue = value;
        }

        public String getValue(){
            return vlaue;
        }

    }

    private final static String LOCKED = "LOCKED";
    private final static String LOCK_TYPE = "LOCK_TYPE";
    private final static String LOCK_PASSWD = "LOCK_PASSWD";

    private  LockModel() {

    }

    
    public static boolean isLocked() {
        return SharePrefUtil.getBoolean(null, LOCKED);
    }

    
    public static LockType getLockType() {
        String type = SharePrefUtil.get(null, LOCK_TYPE);
        LockType lockType = null;
        if (type.equals(LockType.FINGER.getValue())) {
            lockType = LockType.FINGER;
        } else if (type.equals(LockType.GESTURE.getValue())) {
            lockType = LockType.GESTURE;
        }
        return lockType;
    }

    
    public static String getLockPasswd() {
        return SharePrefUtil.get(null,LOCK_PASSWD);
    }

    
    public static void setLockType(LockType type) {
        SharePrefUtil.put(null,LOCK_TYPE,type.getValue());
        SharePrefUtil.put(null,LOCKED,true);
        Log.i(TAG, "设置锁类型"+type.getValue());
    }

 

    
    public static void setLockPasswd(String passwd) {
        SharePrefUtil.put(null,LOCK_PASSWD,passwd);
        Log.i(TAG, "设置锁密码");
    }

    
    public static void clearAll() {
        SharePrefUtil.put(null,LOCKED,false);
        SharePrefUtil.put(null,LOCK_PASSWD,"");
        SharePrefUtil.put(null,LOCK_TYPE,"");
        Log.i(TAG, "清除所有密码");
    }
}
