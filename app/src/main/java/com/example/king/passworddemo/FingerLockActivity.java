package com.example.king.passworddemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.king.passworddemo.utils.LockModel;
import com.wei.android.lib.fingerprintidentify.FingerprintIdentify;
import com.wei.android.lib.fingerprintidentify.base.BaseFingerprint;

/**
 * Created by king on 12/28/17.
 */

public class FingerLockActivity extends Activity {
    FingerprintIdentify mFingerprintIdentify;
    TextView tvTip;
    boolean isFist = true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finager_lock);
        tvTip = findViewById(R.id.tv_tip);

        tvTip.setText("请验证指纹");
        mFingerprintIdentify = new FingerprintIdentify(this);                       // 构造对象
     /*   mFingerprintIdentify = new FingerprintIdentify(this, new BaseFingerprint.FingerprintIdentifyExceptionListener() {
            @Override
            public void onCatchException(Throwable exception) {

            }
        });    // 构造对象，并监听错误回调（错误仅供开发使用）*/
      /*  if (!mFingerprintIdentify.isFingerprintEnable()) {// 指纹硬件可用并已经录入指纹
            tvTip.setText("指纹不可用，或未录入指纹，请录入指纹后再试");
        }
*/
        if (!mFingerprintIdentify.isHardwareEnable()) { // 指纹硬件是否可用
            tvTip.setText("指纹硬件不可用,您的系统可能不支持指纹验证");
            return;
        }
        if(!mFingerprintIdentify.isRegisteredFingerprint()){ // 是否已经录入指纹
            tvTip.setText("未录入指纹，请录入指纹后再试");
            return;
        }



        //mFingerprintIdentify.cancelIdentify();                                      // 关闭指纹识别
        onStartIdentify(null);

    }

    BaseFingerprint.FingerprintIdentifyListener listener = new BaseFingerprint.FingerprintIdentifyListener() {
        @Override
        public void onSucceed() {
            // 验证成功，自动结束指纹识别
            tvTip.setText("指纹验证成功");

            String type = getIntent().getExtras().getString("type");
            switch (type) {
                case "set"://录入指纹
                    LockModel.setLockType(LockModel.LockType.FINGER);
                    finish();
                    break;
                case "verify"://验证指纹
                    finish();
                    break;
            }
        }

        @Override
        public void onNotMatch(int availableTimes) {
            // 指纹不匹配，并返回可用剩余次数并自动继续验证
            tvTip.setText("指纹验证失败，剩余次数 " + availableTimes);
        }

        @Override
        public void onFailed(boolean isDeviceLocked) {
            // 错误次数达到上限或者API报错停止了验证，自动结束指纹识别
            // isDeviceLocked 表示指纹硬件是否被暂时锁定
            tvTip.setText("指纹验证失败，设备暂时锁定，请稍后再试");
        }

        @Override
        public void onStartFailedByDeviceLocked() {
            // 第一次调用startIdentify失败，因为设备被暂时锁定
            tvTip.setText("设备暂时锁定，请稍后再试");
        }
    };
    public void onStartIdentify(View view){
        tvTip.setText("请验证指纹");
        if(isFist){
            isFist = false;
            mFingerprintIdentify.startIdentify(3, listener);// 开始验证指纹识别
        }else{
           // mFingerprintIdentify.cancelIdentify();
            mFingerprintIdentify.resumeIdentify();                                      // 恢复指纹识别并保证错误次数不变
            mFingerprintIdentify.startIdentify(3, listener);// 开始验证指纹识别
        }


    }


    @Override
    public void onBackPressed() {
        // super.onBackPressed();
    }
}
