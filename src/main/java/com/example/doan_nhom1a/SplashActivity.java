package com.example.doan_nhom1a;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        //Activity đầu tiên khi vào app,ầu tiên sau đó mới vào log hiện tên app, logo đin hoặc main activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //truy cập firedatabase, xét user
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                //nếu chưa truy cập thì chuyển qua màn hình login, nếu có thì vào main activity
                if(currentUser==null) {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }else{
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                }
                finish();
            }
        },1000);

    }
}