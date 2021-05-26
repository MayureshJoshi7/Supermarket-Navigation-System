package com.example.supermarket;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.Demo.AstarPlatformPathfinding.BuildConfig.*;
import com.unity3d.player.UnityPlayerActivity;

public class unityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unity);

        Intent intent = new Intent(unityActivity.this, UnityPlayerActivity.class);
        startActivity(intent);

    }
}