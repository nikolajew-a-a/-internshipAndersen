package com.example.android.topic31;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MainActivity extends AppCompatActivity  {
    private TextView nameSong;
    private TextView bandSong;
    private TextView styleSong;

    private Song song = new Song();

    private Boolean isStopped = false;

    private static final String SELECT_TRACK_ACTION = "com.example.android.topic31.SelectTrack";

    private static final String SHARED_PREFS = "sharedPref";
    private static final String IS_STOPPED = "isStopped";

    private static final String SONG_NAME = "name";
    private static final String SONG_BAND = "band";
    private static final String SONG_STYLE = "style";
    private static final String SONG_URI = "uri";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button playButton = findViewById(R.id.play_button);
        Button pauseButton = findViewById(R.id.pause_button);
        Button stopButton = findViewById(R.id.stop_button);
        Button selectTrackButton = findViewById(R.id.select_song);
        nameSong = findViewById(R.id.name_song);
        bandSong = findViewById(R.id.band_song);
        styleSong = findViewById(R.id.style_song);

        playButton.setOnClickListener(v -> playMusic());
        pauseButton.setOnClickListener(v -> pauseMusic());
        stopButton.setOnClickListener(v -> stopMusic());
        selectTrackButton.setOnClickListener((v) -> selectTrack(v));

        if (isNewTrack()) {
            parseIntent();
            saveData();
        }

        loadData();
        setSong();
    }

    private boolean isNewTrack() {
        return getIntent().getStringExtra(SONG_NAME) != null
                && getIntent().getStringExtra(SONG_BAND) != null
                && getIntent().getStringExtra(SONG_STYLE) != null
                && getIntent().getStringExtra(SONG_URI) != null;
    }

    private void selectTrack(@NotNull View v) {
        Intent intent = new Intent();
        intent.setAction(SELECT_TRACK_ACTION);
        sendBroadcastExplicit(this, intent);
        Log.d("mLog", "Отправлен 3.1");
    }

    private void playMusic() {
        loadData();
        if (song.getSongUri() != null) {
            Intent intent = new Intent(this, MyMusicService.class);
            intent.putExtra(IS_STOPPED, isStopped);
            intent.putExtra(SONG_URI, song.getSongUri());
            startService(intent);
        }
        isStopped = false;
        saveData();
    }

    private void pauseMusic() {
        stopService(new Intent(this, MyMusicService.class));
        isStopped = false;
        saveData();
    }

    private void stopMusic() {
        stopService(new Intent(this, MyMusicService.class));
        isStopped = true;
        saveData();
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(IS_STOPPED, isStopped);
        editor.putString(SONG_NAME, song.getSongName());
        editor.putString(SONG_BAND, song.getSongBand());
        editor.putString(SONG_STYLE, song.getSongStyle());
        editor.putString(SONG_URI, song.getSongUri());
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        isStopped = sharedPreferences.getBoolean(IS_STOPPED, false);
        song.setSongName(sharedPreferences.getString(SONG_NAME, "-"));
        song.setSongBand(sharedPreferences.getString(SONG_BAND, "-"));
        song.setSongStyle(sharedPreferences.getString(SONG_STYLE, "-"));
        song.setSongUri(sharedPreferences.getString(SONG_URI, null));
    }

    private void parseIntent() {
        song.setSongName(getIntent().getStringExtra(SONG_NAME));
        song.setSongBand(getIntent().getStringExtra(SONG_BAND));
        song.setSongStyle(getIntent().getStringExtra(SONG_STYLE));
        song.setSongUri(getIntent().getStringExtra(SONG_URI));
    }

    private void setSong() {
        if (song.getSongName() != null && song.getSongBand() != null && song.getSongStyle() != null) {
            nameSong.setText(song.getSongName());
            bandSong.setText(song.getSongBand());
            styleSong.setText(song.getSongStyle());
        }
    }

    private void sendBroadcastExplicit(@NotNull Context context, @NotNull Intent intent) {
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> matches = pm.queryBroadcastReceivers(intent, 0);
        for (ResolveInfo resolveInfo : matches) {
            Intent explicit = new Intent(intent);
            ComponentName cn =
                    new ComponentName(resolveInfo.activityInfo.applicationInfo.packageName,
                            resolveInfo.activityInfo.name);
            explicit.setComponent(cn);
            context.sendBroadcast(explicit);
        }
    }

}
