package com.example.android.topic31;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {
    private Button playButton;
    private Button pauseButton;
    private Button stopButton;
    private Boolean isStopped = false;
    private static final String SHARED_PREFS = "sharedPrefs";
    private static final String IS_STOPPED = "isStopped";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = findViewById(R.id.play_button);
        pauseButton = findViewById(R.id.pause_button);
        stopButton = findViewById(R.id.stop_button);

        playButton.setOnClickListener(v -> playMusic());
        pauseButton.setOnClickListener(v -> pauseMusic());
        stopButton.setOnClickListener(v -> stopMusic());
    }

    public void playMusic() {
        loadData();
        startService(new Intent(this, MyMusicService.class).putExtra(IS_STOPPED, isStopped));
        isStopped = false;
        saveData();
    }

    public void pauseMusic() {
        stopService(new Intent(this, MyMusicService.class));
        isStopped = false;
        saveData();
    }

    public void stopMusic() {
        stopService(new Intent(this, MyMusicService.class));
        isStopped = true;
        saveData();
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(IS_STOPPED, isStopped);
        editor.apply();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        isStopped = sharedPreferences.getBoolean(IS_STOPPED, false);
    }

}
