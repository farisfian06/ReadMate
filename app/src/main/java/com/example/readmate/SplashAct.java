package com.example.readmate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Delay 1.5 detik sebelum memulai MainActivity
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashAct.this, MainActivity.class));
            finish(); // Tutup SplashAct
        }, 1500);
    }
}
