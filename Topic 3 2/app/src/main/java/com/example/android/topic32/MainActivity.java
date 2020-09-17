package com.example.android.topic32;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Spinner spinnerBand;
    private Spinner spinnerStyle;
    private Adapter adapterForRecyclerView;
    private List<Song> listOfSongs;
    private List<Song> filteredListOfSongs;
    private String filterByBand;
    private String filterByStyle;
    private final String DEFAULT_FILTER = "all";
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);
        listOfSongs = getAllSongsFromDb(dbHelper);

        filteredListOfSongs = new ArrayList<>();
        filterByBand = DEFAULT_FILTER;
        filterByStyle = DEFAULT_FILTER;
        filter(listOfSongs, filterByBand, filterByStyle);


        adapterForRecyclerView = new Adapter(filteredListOfSongs);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterForRecyclerView);


        spinnerBand = findViewById(R.id.spinner_band);
        spinnerStyle = findViewById(R.id.spinner_style);


        ArrayAdapter<String> adapterForBandSpinner = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, getBands(listOfSongs));
        adapterForBandSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBand.setAdapter(adapterForBandSpinner);
        spinnerBand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filterByBand = parent.getSelectedItem().toString();
                filter(listOfSongs, filterByBand, filterByStyle);
                adapterForRecyclerView.notifyDataSetChanged();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ArrayAdapter<String> adapterForStyleSpinner = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, getStyles(listOfSongs));
        adapterForStyleSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStyle.setAdapter(adapterForStyleSpinner);
        spinnerStyle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filterByStyle = parent.getSelectedItem().toString();
                filter(listOfSongs, filterByBand, filterByStyle);
                adapterForRecyclerView.notifyDataSetChanged();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private List<String> getBands(List<Song> songs) {
        List<String> listOfBands = new ArrayList<>();
        listOfBands.add(DEFAULT_FILTER);
        for (Song song : songs) {
            if(!listOfBands.contains(song.getSongBand())) {
                listOfBands.add(song.getSongBand());
            }
        }
        return listOfBands;
    }

    private List<String> getStyles(List<Song> songs) {
        List<String> listOfStyles = new ArrayList<>();
        listOfStyles.add(DEFAULT_FILTER);
        for (Song song :
                songs) {
            if(!listOfStyles.contains(song.getSongStyle())) {
                listOfStyles.add(song.getSongStyle());
            }
        }
        return listOfStyles;
    }

    private List<Song> getAllSongsFromDb(DBHelper dbHelper) {
        List<Song> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(DBHelper.TABLE_SONGS, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
            int bandIndex = cursor.getColumnIndex(DBHelper.KEY_BAND);
            int styleIndex = cursor.getColumnIndex(DBHelper.KEY_STYLE);
            int uriIndex = cursor.getColumnIndex(DBHelper.KEY_URI);
            do {
                list.add(new Song(cursor.getString(nameIndex), cursor.getString(bandIndex), cursor.getString(styleIndex), cursor.getString(uriIndex)));
            } while (cursor.moveToNext());
        }

        cursor.close();
        dbHelper.close();
        return list;
    }

    private void filter(List<Song> listOfSongs, String filterByBand, String filterByStyle) {
       filteredListOfSongs.clear();
        for (Song song : listOfSongs) {
            if((filterByBand.equals(DEFAULT_FILTER) || filterByBand.equals(song.getSongBand()))
            && (filterByStyle.equals(DEFAULT_FILTER) || filterByStyle.equals(song.getSongStyle()))){
                filteredListOfSongs.add(song);
            }
        }
    }
}