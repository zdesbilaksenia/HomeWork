package com.example.hw1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
        if (getSupportFragmentManager().findFragmentByTag("Fr1") == null) {
            Fragment1 window = new Fragment1();
            if (savedInstanceState != null) {
                window.setArguments(savedInstanceState);
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment1Container, window, "Fr1").commit();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("size", DataSource.getInstance().getData().size());
    }
}
