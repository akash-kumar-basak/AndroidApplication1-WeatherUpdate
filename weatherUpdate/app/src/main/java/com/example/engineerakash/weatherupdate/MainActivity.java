package com.example.engineerakash.weatherupdate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    int splashScreenduration = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent informationActivity = new Intent(MainActivity.this, information.class);
                startActivity(informationActivity);
                finish();
            }
        }, splashScreenduration);
    }
}
