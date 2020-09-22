package com.example.android.topic32;

import android.content.Context;
import android.net.Uri;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SongsForDB {
    private List<Song> listOfSongs;



    SongsForDB(@NotNull String packageName) {
        String uriSong1 = Uri.parse("android.resource://" + packageName + "/" + R.raw.song1).toString();
        String uriSong2 = Uri.parse("android.resource://" + packageName + "/" + R.raw.song2).toString();
        String uriSong3 = Uri.parse("android.resource://" + packageName + "/" + R.raw.song3).toString();
        String uriSong4 = Uri.parse("android.resource://" + packageName + "/" + R.raw.song4).toString();
        String uriSong5 = Uri.parse("android.resource://" + packageName + "/" + R.raw.song5).toString();
        String uriSong6 = Uri.parse("android.resource://" + packageName + "/" + R.raw.song6).toString();
        String uriSong7 = Uri.parse("android.resource://" + packageName + "/" + R.raw.song7).toString();
        String uriSong8 = Uri.parse("android.resource://" + packageName + "/" + R.raw.song8).toString();
        String uriSong9 = Uri.parse("android.resource://" + packageName + "/" + R.raw.song9).toString();
        String uriSong10 = Uri.parse("android.resource://" +packageName + "/" + R.raw.song10).toString();

        Song song1 = new Song("All The Same", "The Bachelor", "new wave", uriSong1);
        Song song2 = new Song("Parties With Tina", "The Bachelor", "new wave", uriSong2);
        Song song3 = new Song("Cherry", "Chromatics", "electronic", uriSong3);
        Song song4 = new Song("Shadow", "Chromatics", "electronic", uriSong4);
        Song song5 = new Song("Waste A Moment", "Kings Of Leon", "indie", uriSong5);

        Song song6 = new Song("My Friends Never Die", "ODESZA", "electronic", uriSong6);
        Song song7 = new Song("Youth", "Parks, Squares and Alleys", "indie", uriSong7);
        Song song8 = new Song("Friday I'm In Love", "The Cure", "new wave", uriSong8);
        Song song9 = new Song("On hold", "The XX", "indie", uriSong9);
        Song song10 = new Song("Replica", "The XX", "indie", uriSong10);

        listOfSongs = new ArrayList<>();
        listOfSongs.add(song1);
        listOfSongs.add(song2);
        listOfSongs.add(song3);
        listOfSongs.add(song4);
        listOfSongs.add(song5);
        listOfSongs.add(song6);
        listOfSongs.add(song7);
        listOfSongs.add(song8);
        listOfSongs.add(song9);
        listOfSongs.add(song10);
    }

    public List<Song> getListOfSongs() {
        return listOfSongs;
    }
}
