package com.example.king.passworddemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.example.king.passworddemo.MainActivity;
import com.example.king.passworddemo.R;
import com.example.king.passworddemo.utils.LockModel;

import java.util.List;

import me.zhanghai.android.patternlock.ConfirmPatternActivity;
import me.zhanghai.android.patternlock.PatternUtils;
import me.zhanghai.android.patternlock.PatternView;
import me.zhanghai.android.patternlock.SetPatternActivity;

/**
 * Created by king on 12/27/17.
 * 手势密码解锁界面
 */

public class GestureUnLockActivity extends ConfirmPatternActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected boolean isStealthModeEnabled() {
        return false;
    }

    @Override
    protected boolean isPatternCorrect(List<PatternView.Cell> pattern) {
        //验证密码
        String patternSha1 = LockModel.getLockPasswd();
        return TextUtils.equals(PatternUtils.patternToSha1String(pattern), patternSha1);
    }

    @Override
    protected void onForgotPassword() {
        //忘记密码　重新登录
        LockModel.clearAll();
        startActivity(new Intent(this,MainActivity.class));
        super.onForgotPassword();
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
    }




}
