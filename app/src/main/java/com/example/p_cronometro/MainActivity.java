package com.example.p_cronometro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.time.LocalTime;

public class MainActivity extends AppCompatActivity {

    LocalTime time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        time = LocalTime.of(0,0,0);
    }
}
