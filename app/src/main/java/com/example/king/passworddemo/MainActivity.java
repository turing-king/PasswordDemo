package com.example.king.passworddemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.king.passworddemo.utils.LockModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //手势密码锁
    public void onSetGestureLock(View view){
        startActivity(new Intent(this,GestureLockActivity.class));
    }

    //指纹锁
    public void onSetFingerLock(View view){
        Intent intent = new Intent(this,FingerLockActivity.class);
        intent.putExtra("type","set");
        startActivity(intent);
    }

    public void onClearPasswd(View view){
        LockModel.clearAll();
        Toast.makeText(this,"已清理密码",Toast.LENGTH_SHORT).show();
    }

}
