package com.example.projetofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.projetofinal.LoginScreen;
import com.example.projetofinal.R;

public class MainActivity extends AppCompatActivity {

    private static int WELCOME_SCREEN_TIME = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(MainActivity.this, LoginScreen.class);
                startActivity(homeIntent);
                finish();
            }
        }, WELCOME_SCREEN_TIME);
    }
}
