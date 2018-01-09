package com.example.king.passworddemo;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.example.king.passworddemo.utils.LockModel;

import java.util.List;

import me.zhanghai.android.patternlock.PatternUtils;
import me.zhanghai.android.patternlock.PatternView;
import me.zhanghai.android.patternlock.SetPatternActivity;

/**
 * Created by king on 12/28/17.
 * 手势密码上锁界面
 */

public class GestureLockActivity extends SetPatternActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onSetPattern(List<PatternView.Cell> pattern) {
        super.onSetPattern(pattern);
        String passwd = PatternUtils.patternToSha1String(pattern);
        LockModel.setLockType(LockModel.LockType.GESTURE);
        LockModel.setLockPasswd(passwd);
    }
}
