package com.example.android.topic32;

import org.jetbrains.annotations.NotNull;

public class Song {

    private String songName;
    private String songBand;
    private String songStyle;
    private String songId;

    public Song(@NotNull String songName, @NotNull String songBand, @NotNull String songStyle, @NotNull String songSource) {
        this.songName = songName;
        this.songBand = songBand;
        this.songStyle = songStyle;
        this.songId = songSource;
    }

    public String getSongName() {
        return songName;
    }

    public String getSongBand() {
        return songBand;
    }

    public String getSongStyle() {
        return songStyle;
    }

    public String getSongId() {
        return songId;
    }
}
