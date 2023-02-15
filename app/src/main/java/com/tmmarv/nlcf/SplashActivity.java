package com.tmmarv.nlcf;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    ProgressBar mIndicator;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mIndicator = findViewById(R.id.progressCirc);
        mAuth = FirebaseAuth.getInstance();

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            FirebaseUser user = mAuth.getCurrentUser();
            if (user != null) {
                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
            } else {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            }
        }, 600);
    }
}