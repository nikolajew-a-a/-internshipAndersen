package com.example.android.topic31;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;

import java.net.URI;
import java.net.URISyntaxException;


public class MyMusicService extends Service {
    private MediaPlayer player;
    private int time;
    private boolean isStopped;
    private Thread threadSaveTime;
    private Boolean stopFlag = false;
    private URI currentSong;

    public static final String SHARED_PREFS_SERVICE = "sharedPrefsService";
    public static final String TIME = "time";
    public static final String IS_STOPPED = "isStopped";
    private static final String SONG_URI = "uri";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        onPlayMusic(intent);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        onStopMusic();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onPlayMusic(Intent intent) {
        Uri uriSong = Uri.parse(intent.getStringExtra(SONG_URI));
        if (player == null) {
            player = MediaPlayer.create(this, uriSong);
        }
        player.start();
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                player.seekTo(0);
                onDestroy();
            }
        });

        loadData(intent);
        update();

        threadSaveTime = new Thread() {
            @Override
            public void run() {
                while(!stopFlag && player.getCurrentPosition() < player.getDuration()) {
                    try {
                        sleep(200);
                        if(!stopFlag) {
                            saveData();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        threadSaveTime.start();
    }

    public void onStopMusic() {
        if (player != null) {
            saveData();
            stopFlag = true;
            player.pause();
            player.release();
            player = null;
        }
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_SERVICE, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        time = player.getCurrentPosition();
        editor.putInt(TIME, time);
        editor.apply();
    }

    public void loadData(Intent intent) {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_SERVICE, MODE_PRIVATE);
        time = sharedPreferences.getInt(TIME, 0);
        isStopped = intent.getBooleanExtra(IS_STOPPED, false);
    }

    public void update() {
        if (!isStopped) {
            player.seekTo(time);
        }
    }
}
