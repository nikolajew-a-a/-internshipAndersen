package com.example.android.topic13;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = new Bundle();

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new Adapter( generateListOfFigures()));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private List<Figure> generateListOfFigures() {
        List<Figure> figures = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            if (i%2 == 0) {
                figures.add(new Figure(i + "element", (Drawable) getDrawable(R.drawable.circle)));
            } else if (i%3 == 0) {
                figures.add(new Figure(i + "element", (Drawable) getDrawable(R.drawable.triangle)));
            } else {
                figures.add(new Figure(i + "element", (Drawable) getDrawable(R.drawable.square)));
            }
        }
        return figures;
    }

}
