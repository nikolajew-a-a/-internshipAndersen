package com.example.android.topic31;

import org.jetbrains.annotations.NotNull;

public class Song {

    private String songName;
    private String songBand;
    private String songStyle;
    private String songUri;

    public Song(@NotNull String songName, @NotNull String songBand, @NotNull String songStyle, @NotNull String songSource) {
        this.songName = songName;
        this.songBand = songBand;
        this.songStyle = songStyle;
        this.songUri = songSource;
    }

    public Song(){
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

    public String getSongUri() {
        return songUri;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public void setSongBand(String songBand) {
        this.songBand = songBand;
    }

    public void setSongStyle(String songStyle) {
        this.songStyle = songStyle;
    }

    public void setSongUri(String songUri) {
        this.songUri = songUri;
    }
}
